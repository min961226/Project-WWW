package com.qs.www.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.HolidayUseInfoDTO;

public class HolidayDAO {
	
	public List<HolidayTypeDTO> selectAllHolidayType(SqlSession session) {
		
		return session.selectList("HolidayDAO.selectAllHolidayType");
	}

	public int insertHolidayLog(SqlSession session, HolidayLogDTO holidayLogDTO) {
		
		return session.insert("HolidayDAO.insertHolidayLog", holidayLogDTO);
	}
	
	public int selectHolidayLogNum(SqlSession session) {
		
		return session.selectOne("HolidayDAO.selectHolidayLogNum");
	}

	public int insertHolidayUseInfo(SqlSession session, HolidayUseInfoDTO holidayUseInfoDTO) {
		
		return session.insert("HolidayDAO.insertHolidayUseInfo", holidayUseInfoDTO);
	}


}
