package com.qs.www.schedule.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.schedule.model.dao.HolidayDAO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;

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


}
