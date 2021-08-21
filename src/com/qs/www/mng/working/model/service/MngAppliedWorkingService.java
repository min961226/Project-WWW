package com.qs.www.mng.working.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.working.model.dao.MngAppliedWorkingDAO;
import com.qs.www.schedule.model.dto.ReportDTO;

public class MngAppliedWorkingService {
	
	private final MngAppliedWorkingDAO mngAppliedWorkingDAO;
	
	public MngAppliedWorkingService() {
		mngAppliedWorkingDAO = new MngAppliedWorkingDAO();
	}

	/* 근무 신청목록 조회시, totalCount 세기 */
	public int selectAllMemberScheduleReportCount(HashMap<String, Object> countMap) {
		
		SqlSession session = getSqlSession();
		
		int totalCount = mngAppliedWorkingDAO.selectAllMemberScheduleReportCount(session, countMap);
		
		session.close();
		
		return totalCount;
	}

	/* 근무 신청목록 조회시, 조건에 맞는 근무신청목록 가져오기 */
	public List<ReportDTO> selectAllMemberWorkReport(HashMap<String, Object> selectedInfoMap) {

		SqlSession session = getSqlSession();

		List<ReportDTO> workReportList = mngAppliedWorkingDAO.selectAllMemberWorkReport(session, selectedInfoMap);
		
		session.close();
		
		return workReportList;
	}

	public int deleteWorkLog(int reportNo) {
		
		SqlSession session = getSqlSession();
		
		int result = mngAppliedWorkingDAO.deleteWorkLog(session, reportNo);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		
		return result;
	}

	public int selectCustomWorkNo(int reportNo) {
		
		SqlSession session = getSqlSession();
		
		int customWorkNo = mngAppliedWorkingDAO.selectCustomWorkNo(session, reportNo);
		
		session.close();
		
		return customWorkNo;
	}
	
	public int deleteCustomWorktime(int customWorkNo) {
		
		SqlSession session = getSqlSession();
		
		int result = mngAppliedWorkingDAO.deleteCustomWorktime(session, customWorkNo);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		
		return result;
	}
	
	public int deleteCustomWork(int reportNo) {
		
		SqlSession session = getSqlSession();
		
		int result = mngAppliedWorkingDAO.deleteCustomWork(session, reportNo);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		
		return result;
	}



	

}
