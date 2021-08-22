package com.qs.www.mng.working.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.working.model.dao.MngCommuteDAO;
import com.qs.www.mng.working.model.dao.MngWorkingSystemDAO;
import com.qs.www.schedule.model.dto.MemberCommuteLogDTO;

public class MngCommuteService {
	
	private final MngCommuteDAO mngCommuteDAO;
	
	public MngCommuteService() {
		mngCommuteDAO = new MngCommuteDAO();
	}

	/* 페이징에서 사용할 totalCount를 세오는 메소드 */
	public int selectAllMemberCount(HashMap<String, Object> countMap) {
		SqlSession session = getSqlSession();
		
		int totalCount = mngCommuteDAO.selectAllMemberCount(session, countMap);
		
		session.close();
		
		return totalCount;
	}

	/* 페이징으로 한 페이지에서 보여줄  member를 select*/
	public List<MemberCommuteLogDTO> selectCriteriaMemberList(HashMap<String, Object> selectedInfoMap) {
		SqlSession session = getSqlSession();
		
		List<MemberCommuteLogDTO> memberList = mngCommuteDAO.selectCriteriaMemberList(session, selectedInfoMap);
		
		session.close();
		
		return memberList;
	}
	
	
	

}
