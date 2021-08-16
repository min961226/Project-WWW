package com.qs.www.schedule.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.MonthlyWorkLogDTO;
import com.qs.www.schedule.model.dto.OvertimeLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.TeamWorkingHourDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

public class ScheduleDAO {

	public int applyWorkingSystem(SqlSession session, ReportDTO reportDTO) {
		
		return session.insert("ScheduleDAO.applyWorkingSystem", reportDTO);
	}

	public int applyWorkingSystemItemContent(SqlSession session, WorkingDocumentItemDTO workingDocumentItemDTO) {
		
		return session.insert("ScheduleDAO.applyWorkingSystemItemContent", workingDocumentItemDTO);
	}

	public int applyWorkingSystemApprover(SqlSession session, ApproverPerReportDTO approverPerReportDTO) {
		
		return session.insert("ScheduleDAO.applyWorkingSystemApprover", approverPerReportDTO);
	}
	
	public int applyWorkingSystemReferer(SqlSession session, ApproverPerReportDTO approverPerReportDTO) {
		
		return session.insert("ScheduleDAO.applyWorkingSystemReferer", approverPerReportDTO);
	}

	public int applyWorkingSystemMemberWorkLog(SqlSession session, MemberWorkLogDTO memberWorkLogDTO) {
		
		return session.insert("ScheduleDAO.applyWorkingSystemMemberWorkLog", memberWorkLogDTO);
	}


	public int setFirstWorkingSystemApprover(SqlSession session, ApproverPerReportDTO approverPerReportDTO) {
		
		return session.update("ScheduleDAO.setFirstWorkingSystemApprover", approverPerReportDTO);
	}

	public List<ReportDTO> selectMyWorkReport(SqlSession session, int no) {
		
		return session.selectList("ScheduleDAO.selectMyWorkReport", no);
	}
	
	/* 해당월의 지각횟수 count */
	public int countLateTimeNum(SqlSession session, MonthlyWorkLogDTO monthlyWorkLogDTO) {
		
		return session.selectOne("ScheduleDAO.countLateTimeNum", monthlyWorkLogDTO);
	}
	
	/* 해당월의 출근 미체크 횟수 count */
	public int countNoCheckInTimeNum(SqlSession session, MonthlyWorkLogDTO monthlyWorkLogDTO) {
		
		return session.selectOne("ScheduleDAO.countNoCheckInTimeNum", monthlyWorkLogDTO);
	}
	
	/* 해당월의 퇴근 미체크 횟수 count */
	public int countNoCheckOutTimeNum(SqlSession session, MonthlyWorkLogDTO monthlyWorkLogDTO) {
		
		return session.selectOne("ScheduleDAO.countNoCheckOutTimeNum", monthlyWorkLogDTO);
	}
	
	/* 오늘 퇴근체크 여부 확인 (count 사용) */
	public int checkedOutToday(SqlSession session, MonthlyWorkLogDTO monthlyWorkLogDTO) {
		
		return session.selectOne("ScheduleDAO.checkedOutToday", monthlyWorkLogDTO);
	}
	
	/* 초과근무시간 검색 */
	public List<OvertimeLogDTO> selectOverTimeLog(SqlSession session, OvertimeLogDTO overtimeLogDTO) {
		
		return session.selectOne("ScheduleDAO.selectOverTimeLog", overtimeLogDTO);
	}
	
	/* 같은 팀 사람들의 근무정보를 검색 */
	//<if test="appWorkType == '표준근무제'">로 하는게 맞을지는 모르겠네..
	public List<TeamWorkingHourDTO> selectteamWorkingHourList(SqlSession session, HashMap<String, Object> deptAndDay) {
		
		return session.selectList("ScheduleDAO.selectteamWorkingHourList", deptAndDay);
	}

	

	

}
