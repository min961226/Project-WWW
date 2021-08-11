package com.qs.www.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.schedule.model.dto.HolidayTypeDTO;

public class HolidayDAO {
	
	public List<HolidayTypeDTO> selectAllHolidayType(SqlSession session) {
		
		return session.selectList("HolidayDAO.selectAllHolidayType");
	}

}
