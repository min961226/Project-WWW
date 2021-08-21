package com.qs.www.schedule.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.member.model.dto.MemberInfoDTO;
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

	public List<ReportDTO> selectMyholidayReport(Map<String, Object> selectedInfoMap) {
		
		SqlSession session = getSqlSession();
		
		List<ReportDTO> holidayReportList = holidayDAO.selectMyholidayReport(session, selectedInfoMap);

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

	/* 페이징에서 사용할 totalCount를 세오는 메소드 */
	public int selectAllHolidayReportCount(Map<String, Object> countMap) {
		SqlSession session = getSqlSession();
		
		int totalCount = holidayDAO.selectAllHolidayReportCount(session, countMap);
		
		session.close();
		
		return totalCount;
	}
	

	public int selectHavingHoliday(int memberNo) {

		SqlSession session = getSqlSession();

		int havingHoliday = holidayDAO.selectHavingHoliday(session, memberNo);

		session.close();

		return havingHoliday;
	}

	public int updateHavingHoliday(MemberInfoDTO memberInfoDTO) {

		SqlSession session = getSqlSession();

		int result = holidayDAO.updateHavingHoliday(session, memberInfoDTO);

		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return result;
	}





}
