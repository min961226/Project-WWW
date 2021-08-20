package com.qs.www.mng.working.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.working.model.dao.MngWorkingDAO;
import com.qs.www.mng.working.model.dto.WorkingDTO;

public class MngWorkingService {
	
	private final MngWorkingDAO mngWorkingDAO;
	
	public MngWorkingService() {
		mngWorkingDAO = new MngWorkingDAO();
	}
	
	/* 근무제 추가하기 */
	public int InsertStandardMngWorkingSystem(WorkingDTO workingDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = mngWorkingDAO.InsertStandardMngWorkingSystem(session, workingDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}
	
	/* 근무제 삭제하기 */
	public int updateStandardMngWorkingSystem(int deleteWorkCode) {
		
		SqlSession session = getSqlSession();
		
		int result = mngWorkingDAO.updateStandardMngWorkingSystem(session, deleteWorkCode);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

}
