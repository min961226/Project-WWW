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

	public int insertMngForm(SqlSession session, MngFormDTO newMngForm) {
		return session.insert("MngFormDAO.insertMngForm", newMngForm);
	}

	public int updateMngForm(SqlSession session, MngFormDTO mngform) {
		return session.update("MngFormDAO.updateMngForm", mngform);
	}

	public int deleteMngForm(SqlSession session, int no) {
		return session.delete("MngFormDAO.deleteMngForm",no);
	}

}
