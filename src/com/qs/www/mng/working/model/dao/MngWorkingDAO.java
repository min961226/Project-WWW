package com.qs.www.mng.working.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.working.model.dto.WorkingDTO;

public class MngWorkingDAO {
	
	/* 근무제 추가하기 */
	public int InsertStandardMngWorkingSystem(SqlSession session, WorkingDTO workingDTO) {
		
		return session.insert("MngWorkingDAO.InsertStandardMngWorkingSystem", workingDTO);
	}

}
