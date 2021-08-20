package com.qs.www.mng.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.board.model.dto.MngNoticeDTO;

public class MngNoticeDAO {

	public List<MngNoticeDTO> selectAllMngNoticeList(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("MngNoticeDAO.selectBoardList", selectCriteria);
	}

	public int selectAllCount(SqlSession session, Map<String, String> searchMap) {
		
		return session.selectOne("MngNoticeDAO.selectAllCount", searchMap);
	}

	public int insertMngNotice(SqlSession session, MngNoticeDTO newMngNotice) {
		return session.insert("MngNoticeDAO.insertMngNotice", newMngNotice);
	}

	public int updateMngNotice(SqlSession session, MngNoticeDTO mngnotice) {
		return session.update("MngNoticeDAO.updateMngNotice", mngnotice);
	}

	public int incrementMngNoticeCount(SqlSession session, int no) {
		return session.update("MngNoticeDAO.incrementMngNoticeCount", no);	
	}

	public MngNoticeDTO selectMngNoticeDetail(SqlSession session, int no) {
		return session.selectOne("MngNoticeDAO.selectMngNoticeDetail", no);
	}

	public int deleteMngNotice(SqlSession session, int no) {
		return session.delete("MngNoticeDAO.deleteMngNotice",no);
	}

	public int selectBoardNum(SqlSession session) {
		return session.selectOne("MngNoticeDAO.selectBoardNum");
	}

}
