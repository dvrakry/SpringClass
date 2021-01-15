package com.king.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
	public List<CommentVO> getList(Integer pno) {
		return cdao.selectList(pno);
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
