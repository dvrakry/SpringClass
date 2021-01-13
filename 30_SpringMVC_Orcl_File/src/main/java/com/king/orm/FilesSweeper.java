package com.king.orm;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.king.domain.FilesVO;
import com.king.persistence.FilesDAO;

@Component
public class FilesSweeper {
	private static Logger logger = LoggerFactory.getLogger(FilesSweeper.class);
	
	@Inject
	private FilesDAO fdao;
	
	
	// cron = 초 분 시 일 월 요일 [년도(옵션)] 
	@Scheduled(cron = "0 0 14 * *")
	public void fileSweep() throws Exception{
		List<FilesVO> list_db = fdao.selectList();
		String base = "C:\\_java\\_spring\\workspace\\uploads\\"; //저장경로
		
		ArrayList<String> savingList = new ArrayList<String>(); //DB에 있는 쪼개진 정보를 모아서 어레이 리스트에 저장(지켜줘야할 리스트)
		for (FilesVO fvo : list_db) {
			String front = fvo.getSavedir() + "\\" + fvo.getUuid() + "_";
			String fname = fvo.getFname();
			savingList.add(base+front+fname);
			
			Path path = Paths.get(base+front+"th_"+fname); //nio.Path
			logger.info(">>>>> path : " + path.toString());
			boolean isFile = Files.isRegularFile(path); //nio.Files, 
			if(isFile) savingList.add(base+front+"th_"+fname);
		}
		for(String str : savingList) {
			logger.info(">>>> 지켜줘야할 fullPath :" + str);//지켜줘야할 파일목록
		}
		//이제 c드라이브에 저장된거랑 비교해야함
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		
		File target_dir = Paths.get(base,today).toFile(); 
		File[] allFiles = target_dir.listFiles();//listFiles 하면 다 가지고 나옴
		
		for (File file : allFiles) {
			String fileName = file.toPath().toString();
			logger.info(">>> fileName : " + fileName);
			if(!savingList.contains(fileName)) {//둘을 비교하는것 savingList를 기준으로 fileName을 던짐, ! 포함하고있지 않으니깐 지움
				file.delete();
			}
		}
	}
}








