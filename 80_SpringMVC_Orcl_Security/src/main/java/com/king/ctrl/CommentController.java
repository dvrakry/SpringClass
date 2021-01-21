package com.king.ctrl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.king.domain.CommentDTO;
import com.king.domain.CommentVO;
import com.king.domain.Paging;
import com.king.service.CommentService;

@RestController //전부 데이터전송만 하므로 restcontroller하면 @responseBody 따로 안해줘도됨 혼용해서 쓸때(다른컨트롤러에서)는 바디써줘야됨
@RequestMapping("/comment/*")
public class CommentController {
	private static Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Inject
	CommentService csv;
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "/{cno}", produces = MediaType.TEXT_PLAIN_VALUE )
	public ResponseEntity<String> remove(@PathVariable("cno")int cno) {
		int isRm = csv.remove(cno);
		return isRm> 0 ? new ResponseEntity<String>("1", HttpStatus.OK): new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = {RequestMethod.PATCH,RequestMethod.PUT}, value = "/{cno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE) //application/text; charset=utf-8 ===  MediaType.TEXT_PLAIN_VALUE
	public ResponseEntity<String> modify(@RequestBody CommentVO cvo, @PathVariable("cno")int cno) { //@PathVariable("cno")int cno 여기선 굳이 안써도됨 (안쓰임여기선)
		int isUp = csv.modify(cvo);
		return isUp>0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping(value = "/pno/{pno}/{pgIdx}", produces = {MediaType.APPLICATION_ATOM_XML_VALUE, //.getJSON 으로 보내기위해 타입지정하는것 xml로하고 UTF-8
			MediaType.APPLICATION_JSON_UTF8_VALUE}) // /comment/pno/1 : pno 1 의 코멘트리스트를 가져옴 value = "/pno/1/1/admin" == /comment/list?pno=1&pageIdx=1&keyword=admin
	public ResponseEntity<CommentDTO> list(@PathVariable("pno") int pno, @PathVariable("pgIdx")int pgIdx) { //(@PathVariable("pno") 이건 바로위 {pno}이거임
		// List<CommentVO> list = csv.getList(pno, pg);	
		Paging pg = new Paging(pgIdx, 10);
		return new ResponseEntity<CommentDTO>(csv.getList(pno, pg),HttpStatus.OK); //ResponseEntity 는 자바에서 HTML 로 보내주는거 : ResponseEntity<List<CommentVO>> 리스트 객체로 보내주는거(응답해주는거임 자바가 HTML에서 읽을수있도록)
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/write", consumes = "application/json", produces = "application/text; charset=utf-8")
	public ResponseEntity<String> write(@RequestBody CommentVO cvo) { //@RequestBody CommentVO cvo => html 에서 날라온 json 데이터를 java 객체로 만들어줌 <-> responseBOdy 는 자바에서 html로 html형식의 데이터로 날려주는거
		int isUp = csv.write(cvo); // 이 cvo가 html에서 날라온 데이터를 @RequestBody CommentVO cvo 이걸통해서 java객체로 만든것
		return isUp>0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
