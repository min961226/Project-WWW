package com.qs.www.mng.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.board.model.dto.MngFormDTO;

public class MngFormDAO {

	public List<MngFormDTO> selectAllMngFormList(SqlSession session, SelectCriteria selectCriteria) {
		return session.selectList("MngFormDAO.selectBoardList", selectCriteria);
	}

	public int incrementMngFormCount(SqlSession session, int no) {
		return session.update("MngFormDAO.incrementMngFormCount", no);
	}

	public MngFormDTO selectMngFormDetail(SqlSession session, int no) {
		return session.selectOne("MngFormDAO.selectMngFormDetail", no);
	}

}
