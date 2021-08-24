package com.qs.www.schedule.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.member.model.dto.DepartmentDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.CustomWorkDTO;
import com.qs.www.schedule.model.dto.CustomWorkTimeDTO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.OvertimeLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.StandardWorkDTO;
import com.qs.www.schedule.model.dto.TeamWorkingHourDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

public class ScheduleDAO {
	
	public List<StandardWorkDTO> selectAllWorkType(SqlSession session) {
		
		return session.selectList("ScheduleDAO.selectAllWorkType");
	}
	
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

	public List<ReportDTO> selectMyWorkReport(SqlSession session, HashMap<String, Object> selectedInfoMap) {
		
		return session.selectList("ScheduleDAO.selectMyWorkReport", selectedInfoMap);
	}
	
	public List<OvertimeLogDTO> selectOverTimeLog(SqlSession session, OvertimeLogDTO overtimeLogDTO) {
		
		return session.selectList("ScheduleDAO.selectOverTimeLog", overtimeLogDTO);
	}
	
	public List<TeamWorkingHourDTO> selectteamWorkingHourList(SqlSession session, HashMap<String, Object> deptAndDay) {
		
		return session.selectList("ScheduleDAO.selectteamWorkingHourList", deptAndDay);
	}
	
	public List<HolidayLogDTO> selectteamHolidayLogList(SqlSession session, HashMap<String, Object> deptAndDay) {
		
		return session.selectList("ScheduleDAO.selectteamHolidayLogList", deptAndDay);
	}

	public int selectCustomWorkNum(SqlSession session) {
		
		return session.selectOne("ScheduleDAO.selectCustomWorkNum");
	}

	public int insertCustomWork(SqlSession session, CustomWorkDTO customWorkDTO) {
		
		return session.insert("ScheduleDAO.insertCustomWork", customWorkDTO);
	}

	public int insertCustomWorktime(SqlSession session, CustomWorkTimeDTO customWorkTimeDTO) {
		
		return session.insert("ScheduleDAO.insertCustomWorktime", customWorkTimeDTO);
	}

	public int insertOvertimeLog(SqlSession session, OvertimeLogDTO overtimeLogDTO) {
		
		return session.insert("ScheduleDAO.insertOvertimeLog", overtimeLogDTO);
	}

	public int selectAllScheduleReportCount(SqlSession session, Map<String, Object> countMap) {
		
		return session.selectOne("ScheduleDAO.selectAllScheduleReportCount", countMap);
	}

	public List<MemberInfoDTO> selectAllTeamMember(SqlSession session, HashMap<String, Object> deptAndDay) {
		
		return session.selectList("ScheduleDAO.selectAllTeamMember", deptAndDay);
	}
	

}
