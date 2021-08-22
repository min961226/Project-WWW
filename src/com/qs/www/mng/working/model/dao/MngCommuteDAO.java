package com.qs.www.mng.working.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.schedule.model.dto.MemberCommuteLogDTO;

public class MngCommuteDAO {

	public int selectAllMemberCount(SqlSession session, HashMap<String, Object> countMap) {
		
		return session.selectOne("MngCommuteDAO.selectAllMemberCount", countMap);
	}

	public List<MemberCommuteLogDTO> selectCriteriaMemberList(SqlSession session, HashMap<String, Object> selectedInfoMap) {

		return session.selectList("MngCommuteDAO.selectCriteriaMemberList", selectedInfoMap);
	}

}
