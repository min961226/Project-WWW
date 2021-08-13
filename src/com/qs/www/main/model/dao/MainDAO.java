package com.qs.www.main.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dto.CommutingLogDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.dto.WorkingTypeDTO;

public class MainDAO {

	public List<CommutingLogDTO> selectCommutingLog(SqlSession sqlSession, WorkInfoDTO workInfo) {
		return sqlSession.selectList("MainDAO.selectCommutingLog", workInfo);
	}

	public WorkingLogDTO selectWorkingLog(SqlSession sqlSession, WorkInfoDTO workInfo) {
		return sqlSession.selectOne("MainDAO.selectWorkingLog", workInfo);
	}

	public WorkingTypeDTO selectWorkingType(SqlSession sqlSession, WorkingLogDTO workingLog) {
		return sqlSession.selectOne("MainDAO.selectWorkingType", workingLog);
	}
}
