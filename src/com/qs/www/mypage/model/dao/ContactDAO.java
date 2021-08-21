package com.qs.www.mypage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mypage.model.dto.ContactDTO;

public class ContactDAO {

	public int selectAllCount(SqlSession session, Map<String, String> searchMap) {
		return session.selectOne("ContactDAO.selectAllCount", searchMap);
	}

	public List<ContactDTO> selectAllContactList(SqlSession session, SelectCriteria selectCriteria) {
		return session.selectList("ContactDAO.selectBoardList", selectCriteria);
	}

}
