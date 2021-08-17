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

}
