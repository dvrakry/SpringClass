package com.king.orm;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.king.domain.FilesVO;
import com.king.service.FilesService;

import net.coobird.thumbnailator.Thumbnails;
		//1. uploads/yyyy/MM/dd/uuid_filename.확장자
		// 2. DB에는 분리되서 저장됨
		// 3. 이미지 파일이면 썸네일도 만들어서 저장 > uuid_th_filename.확장자
		// 4. 썸네일은 DB에 저장하지 않는다
		// 5. (나중에) 저장된 실제 파일객체와 DB의 정보를 비교하는 스케쥴러를 활용하여 정리함
@Component
public class FileProcessor {
	
	private static Logger logger = LoggerFactory.getLogger(FileProcessor.class);
	
	@Inject
	private FilesService fsv;
	
	public void uploadFiles(MultipartFile[] files, int pno) {
		final String uploadDir = "c:\\_java\\_spring\\workspace\\uploads";
		
		LocalDate date = LocalDate.now(); // 2021-01-12
		String today = date.toString();
		today = today.replace("-", File.separator);	// 2021\01\12 for window10
		
		File folders = new File(uploadDir,today);
		if(!folders.exists()) {//존재 안해야 만들어야됨
			folders.mkdirs();
		}
		
		for (MultipartFile file : files) {
			FilesVO fvo = new FilesVO();
			fvo.setSavedir(today);
			String orgFileName = file.getOriginalFilename();
			logger.info("orgFileName :" + orgFileName);
			logger.info("getName :" + file.getName());
			
			String fileName = orgFileName.substring(orgFileName.lastIndexOf("\\")+1);
			fvo.setFname(fileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			String fullName = uuid.toString()+"_"+fileName; //uuid_fileName.확장자
			
			File storeFile = new File(folders,fullName);
			try {
				file.transferTo(storeFile); //이파일은 포문에 있는 파일임
				
				if(isImageType(storeFile)) {
					fvo.setFtype(1);
					File thumb = new File(folders, uuid.toString()+"_th_"+fileName);
					Thumbnails.of(storeFile).size(100, 100).toFile(thumb);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			fvo.setPno(pno);
			fsv.attach(fvo);
		}//여기가 for 문 끝
		
	}
	
	public void deleteOldFiles(int pno) {
			fsv.remove(pno);
		
		}

	private boolean isImageType(File storeFile) {
		try {
			String mimeType = new Tika().detect(storeFile);//detect 가 mime 타입으로 줌
			return mimeType.startsWith("image") ? true : false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
