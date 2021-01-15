package com.king.ctrl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.king.domain.CommentVO;
import com.king.service.CommentService;

@RestController //전부 데이터전송만 하므로 restcontroller하면 @responseBody 따로 안해줘도됨 혼용해서 쓸때(다른컨트롤러에서)는 바디써줘야됨
@RequestMapping("/comment/*")
public class CommentController {
	private static Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Inject
	CommentService csv;
	
	@DeleteMapping(value = "/1")
	public String remove(int cno) {
		return ""; 
	}
	
	@RequestMapping(method = {RequestMethod.PATCH,RequestMethod.PUT}, value = "/1")
	public String modify(CommentVO cvo) {
		return "";
	}
	
	@GetMapping(value = "/pno/1/1/admin") // /comment/pno/1 : pno 1 의 코멘트리스트를 가져옴 value = "/pno/1/1/admin" == /comment/list?pno=1&pageIdx=1&keyword=admin
	public String list(int pno) {
		return "";
	}
	
	@PostMapping(value = "/write", consumes = "application/json", produces = "application/text; charset=utf-8")
	public String write(@RequestBody CommentVO cvo) { //@RequestBody CommentVO cvo => html 에서 날라온 json 데이터를 java 객체로 만들어줌 <-> responseBOdy 는 자바에서 html로 html형식의 데이터로 날려주는거
		int isUp = csv.write(cvo); // 이 cvo가 html에서 날라온 데이터를 @RequestBody CommentVO cvo 이걸통해서 java객체로 만든것
		
		return "";
	}
	
	
}
