package com.qs.www.mng.working.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.working.model.dao.MngWorkingSystemDAO;
import com.qs.www.mng.working.model.dto.WorkingDTO;

public class MngWorkingSystemService {
	
	private final MngWorkingSystemDAO mngWorkingSystemDAO;
	
	public MngWorkingSystemService() {
		mngWorkingSystemDAO = new MngWorkingSystemDAO();
	}
	
	/* 근무제 추가하기 */
	public int InsertStandardMngWorkingSystem(WorkingDTO workingDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = mngWorkingSystemDAO.InsertStandardMngWorkingSystem(session, workingDTO);
		
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
		
		int result = mngWorkingSystemDAO.updateStandardMngWorkingSystem(session, deleteWorkCode);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}


}
