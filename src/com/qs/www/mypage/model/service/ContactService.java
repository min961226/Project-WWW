package com.qs.www.mypage.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dao.FreeDAO;
import com.qs.www.board.model.dto.FreeDTO;
import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mypage.model.dao.ContactDAO;
import com.qs.www.mypage.model.dto.ContactDTO;

public class ContactService {
	
private final ContactDAO contactDAO;
	
	public ContactService() {
		
		contactDAO = new ContactDAO();
		
	}

	public int selectAllCount(Map<String, String> searchMap) {
		SqlSession session = getSqlSession();
		
		int count = contactDAO.selectAllCount(session,searchMap);
		
		session.close();
		
		return count;
	}

	public List<ContactDTO> selectAllContactList(SelectCriteria selectCriteria) {
		SqlSession session = getSqlSession();
		
		List<ContactDTO> contactList = contactDAO.selectAllContactList(session, selectCriteria);
		
		session.close();
		
		return contactList;
	}

}
