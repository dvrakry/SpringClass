package com.king.ctrl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.king.domain.MemberVO;
import com.king.service.MemberService;

/*1. 컨트롤러 내 메서드들이 void 이면 리퀘스트 매핑 값과 같은 jsp 파일을 찾음.
2. 만약 return 이 redirect: + uri 이면 uri값을 갖은 컨트롤러를 찾음.
3. 1,2 번사항이 아닌 return "uri" 이면 uri 위치에 같은 이름의 jsp 파일을 찾음.*/

@Controller
@RequestMapping("/member/*")
public class MemberController {
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService msv;
	
	@Inject
	private BCryptPasswordEncoder bcpEncoder;
	
	@GetMapping("/resign") //회원정보 수정에서 회원탈퇴
	public String resign(@RequestParam("email")String email, HttpSession ses) {
		int isRm = msv.resign(email);
		if(isRm > 0 ) ses.invalidate();
		return "redirect:/";
	}
	@PostMapping("/resign")// Admin 에서 회원탈퇴
	public String resign(@RequestParam("email")String email) {
		msv.resign(email);
		return "redirect:/member/list";
	}
	
	
	@GetMapping("/list")
	public void list(Model model) {
		List<MemberVO> list = msv.getList();
		model.addAttribute("list", list);
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, HttpSession ses) {
		int isUp = msv.modify(mvo);
		if(isUp > 0) {
			ses.setAttribute("ses", mvo);
		}
		return "redirect:/";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("em")String email, Model model) {
		model.addAttribute("mvo", msv.getMember(email));
		return "/member/modify";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession ses) {
		logger.info(">>> /memeber/logout -GET");
		ses.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public void login() {
		logger.info(">>> /memeber/login -GET");
	}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, HttpSession ses) {
		logger.info(">>> /memeber/login -POST");
		
		MemberVO dbinfo = msv.getMember(mvo.getEmail());
		String dbPwd = dbinfo.getPwd();//암호화된 비번
		boolean isEqual = bcpEncoder.matches(mvo.getPwd(),dbPwd); 
	
		if(isEqual) {
			ses.setAttribute("ses", dbinfo);
			ses.setMaxInactiveInterval(60*30);
			return "redirect:/";
		}else {
			return "member/login";
		}
		
	}
	
	@PostMapping("/join")
	public String join(MemberVO mvo) {
		logger.info(">>> /memeber/join -POST");
		String encPwd = bcpEncoder.encode(mvo.getPwd());
		mvo.setPwd(encPwd);
		msv.join(mvo);
		return "redirect:/";
	}
	
	@GetMapping("/join")
	public void join() {
		logger.info(">>> /memeber/join -GET");
	}
	
	@ResponseBody //ajax (데이터를 실어서 view로보냄)
	@PostMapping("/check")
	public String check(@RequestParam("email") String email) {
		logger.info(">>>>> email = " + email);
		int isExist = msv.check(email);
		return isExist == 1 ? "1" : "0";
	}
	
}
	/*
	 * @RequestMapping(value = "/join", method = RequestMethod.GET) public void
	 * join() { logger.info(">>> /memeber/join -GET"); }
	 */
	
