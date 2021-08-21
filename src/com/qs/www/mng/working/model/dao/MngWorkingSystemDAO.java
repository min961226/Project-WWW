package com.qs.www.mng.working.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.working.model.dto.WorkingDTO;

public class MngWorkingSystemDAO {
	
	/* 근무제 추가하기 */
	public int InsertStandardMngWorkingSystem(SqlSession session, WorkingDTO workingDTO) {
		
		return session.insert("MngWorkingSystemDAO.InsertStandardMngWorkingSystem", workingDTO);
	}
	
	/* 근무제 삭제하기 */
	public int updateStandardMngWorkingSystem(SqlSession session, int deleteWorkCode) {
		
		return session.update("MngWorkingSystemDAO.updateStandardMngWorkingSystem", deleteWorkCode);
	}
	
	
	
}
