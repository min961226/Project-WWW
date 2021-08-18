package com.qs.www.mng.holiday.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.schedule.model.dto.ReportDTO;

public class MngHolidayDAO {
	
	public int selectHolidayAPPCount(SqlSession session, Map<String, String> searchMap) {
		
		return session.selectOne("MngHolidayDAO.selectHolidayAPPCount", searchMap);
	}

	public List<ReportDTO> selectHolidayAPP(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("MngHolidayDAO.selectHolidayAPP", selectCriteria);
	}
	
	public int selectHolidayLogNum(SqlSession session, int lineNo) {
		
		return session.selectOne("MngHolidayDAO.selectHolidayLogNum", lineNo);
	}

	public int cancleSelectedReport(SqlSession session, int lineNo) {
		
		return session.update("MngHolidayDAO.cancleSelectedReport", lineNo);
	}

	public int deleteHolidayUseInfo(SqlSession session, int logNo) {
		
		return session.delete("MngHolidayDAO.deleteHolidayUseInfo", logNo);
	}
	public int deleteHolidayLog(SqlSession session, int logNo) {
		
		return session.delete("MngHolidayDAO.deleteHolidayLog", logNo);
	}

	
}
