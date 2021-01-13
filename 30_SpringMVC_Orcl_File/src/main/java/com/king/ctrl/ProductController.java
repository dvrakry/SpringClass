package com.king.ctrl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.king.domain.ProductVO;
import com.king.orm.FileProcessor;
import com.king.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	private ProductService psv;
	
	@Inject
	private FileProcessor fp;
	
	@PostMapping("/remove")
	public String remove(@RequestParam("pno")int pno, RedirectAttributes reAttr ) {
		fp.deleteOldFiles(pno);//포린키가 pno를 잡고있기때문에 file부터 지워야됨, pno부터 지우면 안지워짐 포린키때문에
		int isUp = psv.remove(pno);
		String msg = isUp>0 ? "상품삭제 완료" : "상품삭제 오류";
		reAttr.addFlashAttribute("result", msg);
		return "redirect:/product/list";
	}
	
	@PostMapping("/modify")
	public String modify(ProductVO pvo, RedirectAttributes reAttr,
			@RequestParam(name="files", required = false)MultipartFile[] files) {
		int isUp = psv.modify(pvo);//여기는 파일말고 상품에대한 정보만 수정, 파일의 정보는 fvo에 있음
		if(isUp>0 && files[0].getSize()>0) {//상품정보가 수정안되도 같은값으로 db가 올라가서 isUP = 1 이됨
			fp.deleteOldFiles(pvo.getPno());//123:예전파일pno
			fp.uploadFiles(files, pvo.getPno());//456:새파일pno
			
		}
		String msg = isUp>0 ? "상품수정 완료" : "상품수정 오류";
		reAttr.addFlashAttribute("result", msg);
		return "redirect:/product/info?pno="+pvo.getPno();
	}
	
	@GetMapping({"/info","/modify"})
	public void info(@RequestParam("pno")int pno, Model model) {
		model.addAttribute("pvo", psv.getInfo(pno));
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", psv.getList());
	}
	
	@PostMapping("/register")
	public String register(ProductVO pvo, RedirectAttributes reAttr,
			@RequestParam(name="files", required = false)MultipartFile[] files) {
		logger.info("files size : " + files.length);
		int isUp = psv.register(pvo);
		if(isUp>0) {
			int pno = psv.getCurrPno();
			if(files[0].getSize() > 0) {//이러면 파일이 존재하는것
				fp.uploadFiles(files, pno);
			}
		}
		String msg = isUp>0 ? "상품 등록 완료!" : "상품 등록 오류!";
		reAttr.addFlashAttribute("result", msg); // 새로고침이나 백할때 무한으로 등록 안되게 일시적으로 바인딩 끊음
		// 일시적으로 데이터 보낼 수 있음
		return "redirect:/product/list"; //리다이렉트로 불러서 주소값이 바뀌기 때문에 새로고침해도 다시 등록이 안되는거
	}
	
	@GetMapping("/register")
	public void register() {}
	
}
