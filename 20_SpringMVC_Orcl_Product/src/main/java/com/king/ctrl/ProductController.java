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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.king.domain.ProductVO;
import com.king.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	private ProductService psv;
	
	@PostMapping("/remove")
	public String remove(@RequestParam("pno")int pno, RedirectAttributes reAttr ) {
		int isUp = psv.remove(pno);
		String msg = isUp>0 ? "상품삭제 완료" : "상품삭제 오류";
		reAttr.addFlashAttribute("result", msg);
		return "redirect:/product/list";
	}
	
	@PostMapping("/modify")
	public String modify(ProductVO pvo, RedirectAttributes reAttr) {
		int isUp = psv.modify(pvo);
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
	public String register(ProductVO pvo, RedirectAttributes reAttr) {
		int isUp = psv.register(pvo);
		String msg = isUp>0 ? "상품 등록 완료!" : "상품 등록 오류!";
		reAttr.addFlashAttribute("result", msg); // 새로고침이나 백할때 무한으로 등록 안되게 일시적으로 바인딩 끊음
		// 일시적으로 데이터 보낼 수 있음
		return "redirect:/product/list";
	}
	
	@GetMapping("/register")
	public void register() {}
	
}
