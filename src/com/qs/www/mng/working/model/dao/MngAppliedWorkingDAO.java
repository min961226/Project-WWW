package com.qs.www.mng.working.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.schedule.model.dto.ReportDTO;

public class MngAppliedWorkingDAO {
	
	/* 근무 신청목록 조회시, totalCount 세기 */
	public int selectAllMemberScheduleReportCount(SqlSession session, HashMap<String, Object> countMap) {
		
		return session.selectOne("MngAppliedWorkingDAO.selectAllMemberScheduleReportCount", countMap);
	}

	/* 근무 신청목록 조회시, 조건에 맞는 모든 workReport 가져오기 */
	public List<ReportDTO> selectAllMemberWorkReport(SqlSession session, HashMap<String, Object> selectedInfoMap) {
		
		return session.selectList("MngAppliedWorkingDAO.selectAllMemberWorkReport", selectedInfoMap);
	}

	public int deleteWorkLog(SqlSession session, int reportNo) {
		
		return session.delete("MngAppliedWorkingDAO.deleteWorkLog", reportNo);
	}

	public int selectCustomWorkNo(SqlSession session, int reportNo) {
		
		return session.selectOne("MngAppliedWorkingDAO.selectCustomWorkNo", reportNo);
	}
	
	public int deleteCustomWorktime(SqlSession session, int customWorkNo) {
		
		return session.delete("MngAppliedWorkingDAO.deleteCustomWorktime", customWorkNo);
	}
	
	public int deleteCustomWork(SqlSession session, int reportNo) {
		
		return session.delete("MngAppliedWorkingDAO.deleteCustomWork", reportNo);
	}



	

}
