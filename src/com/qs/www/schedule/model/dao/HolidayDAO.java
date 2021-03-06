package com.qs.www.schedule.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.HolidayUseInfoDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

public class HolidayDAO {
	
	public List<HolidayTypeDTO> selectAllHolidayType(SqlSession session) {
		
		return session.selectList("HolidayDAO.selectAllHolidayType");
	}

	public int insertHolidayLog(SqlSession session, HolidayLogDTO holidayLogDTO) {
		
		return session.insert("HolidayDAO.insertHolidayLog", holidayLogDTO);
	}
	
	public int insertHolidayUseInfo(SqlSession session, HolidayUseInfoDTO holidayUseInfoDTO) {
		
		return session.insert("HolidayDAO.insertHolidayUseInfo", holidayUseInfoDTO);
	}

	public List<ReportDTO> selectMyholidayReport(SqlSession session, Map<String, Object> selectedInfoMap) {
		
		return session.selectList("HolidayDAO.selectMyholidayReport", selectedInfoMap);
	}
	
	/* 휴가결재 승인을 한 뒤, 휴가부여사용내역에 insert 하기 전 미리 lastNumber를 가지고 옴 */
	public int selectHolidayLogNum(SqlSession session) {
		
		return session.selectOne("HolidayDAO.selectHolidayLogNum");
	}

	public int selectAllHolidayReportCount(SqlSession session, Map<String, Object> countMap) {
		
		return session.selectOne("HolidayDAO.selectAllHolidayReportCount", countMap);
	}	

	public int selectHavingHoliday(SqlSession session, int memberNo) {
		
		return session.selectOne("HolidayDAO.selectHavingHoliday", memberNo);
	}

	public int updateHavingHoliday(SqlSession session, MemberInfoDTO memberInfoDTO) {
		
		return session.update("HolidayDAO.updateHavingHoliday",  memberInfoDTO);
	}


}
