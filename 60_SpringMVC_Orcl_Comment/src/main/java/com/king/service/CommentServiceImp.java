package com.king.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.king.domain.CommentDTO;
import com.king.domain.CommentVO;
import com.king.domain.Paging;
import com.king.persistence.CommentDAO;

@Service
public class CommentServiceImp implements CommentService {
	private static Logger logger = LoggerFactory.getLogger(CommentServiceImp.class);
	
	@Inject
	CommentDAO cdao;

	@Override
	public int write(CommentVO cvo) {
		return cdao.insert(cvo);
	}

	@Override
	public CommentDTO getList(Integer pno, Paging pg) { //목적이 같은 DAO 두개를 서비스에서 부름 , 여기선 DAO로 연결하는게 아닌 CommentDTO에 두개의 값을 담는 로직임(total 과 clist를 cdto에 담는것)
		int total = cdao.selectOne(pno);
		List<CommentVO> clist = cdao.selectList(pno, pg);
		CommentDTO cdto = new CommentDTO(total, clist);
		return cdto; // cdto 에 total 과 clist값을 담음
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public int remove(Integer cno) {
		return cdao.delete(cno);
	}
}
