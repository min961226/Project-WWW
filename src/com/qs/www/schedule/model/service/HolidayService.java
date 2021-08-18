package com.qs.www.schedule.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.schedule.model.dao.HolidayDAO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.HolidayUseInfoDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

public class HolidayService {
	
	private final HolidayDAO holidayDAO;
	
	public HolidayService() {
		holidayDAO = new HolidayDAO();
	}
	
	public List<HolidayTypeDTO> selectAllHolidayType() {
		
		SqlSession session = getSqlSession();
		
		List<HolidayTypeDTO> holidayList = holidayDAO.selectAllHolidayType(session);
		
		if(holidayList != null) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return holidayList;
	}

	public int insertHolidayLog(HolidayLogDTO holidayLogDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = holidayDAO.insertHolidayLog(session, holidayLogDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
		
	}

	public int insertHolidayUseInfo(HolidayUseInfoDTO holidayUseInfoDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = holidayDAO.insertHolidayUseInfo(session, holidayUseInfoDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

	public List<ReportDTO> selectMyholidayReport(int no) {
		
		SqlSession session = getSqlSession();
		
		List<ReportDTO> holidayReportList = holidayDAO.selectMyholidayReport(session, no);
				
		session.close();
		
		return holidayReportList;
	}
	
	/* 휴가결재 승인을 한 뒤, 휴가부여사용내역에 insert 하기 전 미리 lastNumber를 가지고 옴 */
	public int selectHolidayLogNum() {
		
		SqlSession session = getSqlSession();
		
		int holidayLogNo = holidayDAO.selectHolidayLogNum(session);
		
		session.close();
		
		return holidayLogNo;
	}
	

	


}
