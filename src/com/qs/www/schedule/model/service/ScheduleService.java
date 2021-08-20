package com.qs.www.schedule.model.service;

import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dao.ScheduleDAO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.CustomWorkDTO;
import com.qs.www.schedule.model.dto.CustomWorkTimeDTO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.MonthlyWorkLogDTO;
import com.qs.www.schedule.model.dto.OvertimeLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.StandardWorkDTO;
import com.qs.www.schedule.model.dto.TeamWorkingHourDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class ScheduleService {
	
	private final ScheduleDAO scheduleDAO;
	
	public ScheduleService() {
		scheduleDAO = new ScheduleDAO();
	}
	
	public int applyWorkingSystem(ReportDTO reportDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = scheduleDAO.applyWorkingSystem(session, reportDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

	public int applyWorkingSystemItemContent(WorkingDocumentItemDTO workingDocumentItemDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = scheduleDAO.applyWorkingSystemItemContent(session, workingDocumentItemDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
				
		session.close();
		
		return result;
	}

	public int applyWorkingSystemApprover(ApproverPerReportDTO approverPerReportDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = scheduleDAO.applyWorkingSystemApprover(session, approverPerReportDTO);
		int result2 = scheduleDAO.setFirstWorkingSystemApprover(session, approverPerReportDTO);
		if(result > 0 && result2 > 0) {
			session.commit();
		} else {
			session.rollback();
		}
				
		session.close();
		
		return result;
	}
	
	public int applyWorkingSystemReferer(ApproverPerReportDTO approverPerReportDTO) {
		
        SqlSession session = getSqlSession();
		
		int result = scheduleDAO.applyWorkingSystemReferer(session, approverPerReportDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
				
		session.close();
		
		return result;
	}

	public int applyWorkingSystemMemberWorkLog(MemberWorkLogDTO memberWorkLogDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = scheduleDAO.applyWorkingSystemMemberWorkLog(session, memberWorkLogDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
				
		session.close();
		
		return result;
	}

	public List<ReportDTO> selectMyWorkReport(int no) {
		
		SqlSession session = getSqlSession();
		
		List<ReportDTO> workReportList = scheduleDAO.selectMyWorkReport(session, no);
				
		session.close();
		
		return workReportList;
	}
	
	/* 해당월의 지각횟수 count */
//	public int countLateTimeNum(MonthlyWorkLogDTO monthlyWorkLogDTO) {
//		
//		SqlSession session = getSqlSession();
//		
//		int lateNum = scheduleDAO.countLateTimeNum(session, monthlyWorkLogDTO);
//		
//		session.close();
//		
//		return lateNum;
//	}
//	
//	/* 해당월 출근 미체크 횟수 COUNT */
//	public int countNoCheckInTimeNum(MonthlyWorkLogDTO monthlyWorkLogDTO) {
//		
//		SqlSession session = getSqlSession();
//		
//		int noCheckInTimeNum = scheduleDAO.countNoCheckInTimeNum(session, monthlyWorkLogDTO);
//		
//		session.close();
//		
//		return noCheckInTimeNum;
//	}
//	
//	/* 해당월 퇴근 미체크 횟수 COUNT */
//	public int countNoCheckOutTimeNum(MonthlyWorkLogDTO monthlyWorkLogDTO) {
//		
//		SqlSession session = getSqlSession();
//		
//		int noCheckOutTimeNum = scheduleDAO.countNoCheckOutTimeNum(session, monthlyWorkLogDTO);
//		
//		session.close();
//		
//		return noCheckOutTimeNum;
//	}
//	
//	/* 오늘 퇴근체크 여부 확인 (count 사용) */
//	public int checkedOutToday(MonthlyWorkLogDTO monthlyWorkLogDTO) {
//		
//		SqlSession session = getSqlSession();
//		
//		int isCheckedOut = scheduleDAO.checkedOutToday(session, monthlyWorkLogDTO);
//		
//		session.close();
//		
//		return isCheckedOut;
//	}

	/* 해당 기간동안 overtime 기록을 가져오기 */
	public List<OvertimeLogDTO> selectOverTimeLog(OvertimeLogDTO overtimeLogDTO) {
		
		SqlSession session = getSqlSession();
		
		List<OvertimeLogDTO> overTimeLogList = scheduleDAO.selectOverTimeLog(session, overtimeLogDTO);
		
		session.close();
		
		return overTimeLogList;
	}
	
	/* 팀근무조회를 위해, 같은부서 사람들의 정보를 DTO로 담는다.*/
	public List<TeamWorkingHourDTO> selectteamWorkingHourList(HashMap<String, Object> deptAndDay) {
		
		SqlSession session = getSqlSession();
		
		List<TeamWorkingHourDTO> teamWorkingHourList = scheduleDAO.selectteamWorkingHourList(session, deptAndDay);
		
		session.close();
		
		return teamWorkingHourList;
	}

	public List<HolidayLogDTO> selectteamHolidayLogList(HashMap<String, Object> deptAndDay) {
		
		SqlSession session = getSqlSession();
		
		List<HolidayLogDTO> teamHolidayLogList = scheduleDAO.selectteamHolidayLogList(session, deptAndDay);
		
		session.close();
		
		return teamHolidayLogList;
	}
	
	/* 근무결재 승인을 한 뒤, 휴가부여사용내역에 insert 하기 전 미리 lastNumber를 가지고 옴 */
	public int selectCustomWorkNum() {
		
		SqlSession session = getSqlSession();
		
		int customWorkNum = scheduleDAO.selectCustomWorkNum(session);
		
		session.close();
		
		return customWorkNum;
	}

	public int insertCustomWork(CustomWorkDTO customWorkDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = scheduleDAO.insertCustomWork(session, customWorkDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

	public int insertCustomWorktime(CustomWorkTimeDTO customWorkTimeDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = scheduleDAO.insertCustomWorktime(session, customWorkTimeDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

	public int insertOvertimeLog(OvertimeLogDTO overtimeLogDTO) {
		SqlSession session = getSqlSession();
		
		int result = scheduleDAO.insertOvertimeLog(session, overtimeLogDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

	public List<StandardWorkDTO> selectAllWorkType() {
		
		SqlSession session = getSqlSession();
		
		List<StandardWorkDTO> workTypeList = scheduleDAO.selectAllWorkType(session);
		
		session.close();
		
		return workTypeList;
	}
	
	

}
