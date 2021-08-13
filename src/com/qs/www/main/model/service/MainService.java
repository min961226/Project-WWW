package com.qs.www.main.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dao.MainDAO;
import com.qs.www.main.model.dto.CommutingLogDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;

public class MainService {
	
	private final MainDAO mainDAO;
	
	public MainService() {
		mainDAO = new MainDAO();
	}

	public List<CommutingLogDTO> selectCommutingLog(WorkInfoDTO workInfo) {
		
		SqlSession sqlSession = getSqlSession();
		
		List<CommutingLogDTO> commutingLog = mainDAO.selectCommutingLog(sqlSession, workInfo);
		
		sqlSession.close();
		
		return commutingLog;
	}

	public List<WorkingLogDTO> selectWorkingLog(WorkInfoDTO workInfo) {
		
		SqlSession sqlSession = getSqlSession();

		List<WorkingLogDTO> workingLog = mainDAO.selectWorkingLog(sqlSession, workInfo);

		sqlSession.close();
		
		return workingLog;
	}
}
