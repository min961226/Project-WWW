package com.qs.www.mng.holiday.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.holiday.model.dto.HolidayRuleDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

public class MngHolidayDAO {
	
	public int selectHolidayAPPCount(SqlSession session, Map<String, String> searchMap) {
		
		return session.selectOne("MngHolidayDAO.selectHolidayAPPCount", searchMap);
	}

	public List<ReportDTO> selectHolidayAPP(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("MngHolidayDAO.selectHolidayAPP", selectCriteria);
	}
	
	public int selectHolidayLogNum(SqlSession session, int reportNo) {
		
		return session.selectOne("MngHolidayDAO.selectHolidayLogNum", reportNo);
	}

	public int cancleSelectedReport(SqlSession session, int reportNo) {
		
		return session.update("MngHolidayDAO.cancleSelectedReport", reportNo);
	}

	public int deleteHolidayUseInfo(SqlSession session, int logNo) {
		
		return session.delete("MngHolidayDAO.deleteHolidayUseInfo", logNo);
	}
	public int deleteHolidayLog(SqlSession session, int logNo) {
		
		return session.delete("MngHolidayDAO.deleteHolidayLog", logNo);
	}

	public String selectDuringDate(SqlSession session, int logNo) {
		
		return session.selectOne("MngHolidayDAO.selectDuringDate", logNo);
	}

	public List<HolidayRuleDTO> selectHolidayRule(SqlSession session) {
		
		return session.selectList("MngHolidayDAO.selectHolidayRule");
	}

	public int updateholidayRuleNumber(SqlSession session, Map<String, Object> ruleMap) {
		
		return session.update("MngHolidayDAO.updateholidayRuleNumber", ruleMap);
	}

	
}
