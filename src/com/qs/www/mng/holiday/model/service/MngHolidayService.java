package com.qs.www.mng.holiday.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.holiday.model.dao.MngHolidayDAO;
import com.qs.www.schedule.model.dto.ReportDTO;

public class MngHolidayService {
	
	private final MngHolidayDAO mngHolidayDAO;

	public MngHolidayService() {
		mngHolidayDAO = new MngHolidayDAO();
	}

	public int selectHolidayAPPCount(Map<String, String> searchMap) {
		SqlSession session = getSqlSession();

		int count = mngHolidayDAO.selectHolidayAPPCount(session, searchMap);

		session.close();

		return count;
	}

	public List<ReportDTO> selectHolidayAPP(SelectCriteria selectCriteria) {
		SqlSession session = getSqlSession();

		List<ReportDTO> reportList = mngHolidayDAO.selectHolidayAPP(session, selectCriteria);

		session.close();
		
		return reportList;
	}
	
	public int selectHolidayLogNum(int lineNo) {
		SqlSession session = getSqlSession();

		int logNo = mngHolidayDAO.selectHolidayLogNum(session, lineNo);

		session.close();

		return logNo;
	}
	

	public int cancleSelectedReport(int lineNo) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.cancleSelectedReport(session, lineNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public int deleteHolidayUseInfo(int logNo) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.deleteHolidayUseInfo(session, logNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}
	public int deleteHolidayLog(int logNo) {
		SqlSession session = getSqlSession();
		
		int result = mngHolidayDAO.deleteHolidayLog(session, logNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		
		return result;
	}

	
	

}
