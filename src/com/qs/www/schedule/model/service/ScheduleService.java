package com.qs.www.schedule.model.service;

import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.schedule.model.dao.ScheduleDAO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.CustomWorkDTO;
import com.qs.www.schedule.model.dto.CustomWorkTimeDTO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.OvertimeLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.StandardWorkDTO;
import com.qs.www.schedule.model.dto.TeamWorkingHourDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<ReportDTO> selectMyWorkReport(HashMap<String, Object> selectedInfoMap) {
		
		SqlSession session = getSqlSession();
		
		List<ReportDTO> workReportList = scheduleDAO.selectMyWorkReport(session, selectedInfoMap);
				
		session.close();
		
		return workReportList;
	}

	public List<OvertimeLogDTO> selectOverTimeLog(OvertimeLogDTO overtimeLogDTO) {
		
		SqlSession session = getSqlSession();
		
		List<OvertimeLogDTO> overTimeLogList = scheduleDAO.selectOverTimeLog(session, overtimeLogDTO);
		
		session.close();
		
		return overTimeLogList;
	}
	
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

	public List<MemberInfoDTO> selectAllTeamMember(HashMap<String, Object> deptAndDay) {
		
		SqlSession session = getSqlSession();
		
		List<MemberInfoDTO> teamMemberInfoList = scheduleDAO.selectAllTeamMember(session, deptAndDay);
		
		session.close();
		
		return teamMemberInfoList;
	}
	
	public List<StandardWorkDTO> selectAllWorkType() {
		
		SqlSession session = getSqlSession();
		
		List<StandardWorkDTO> workTypeList = scheduleDAO.selectAllWorkType(session);
		
		session.close();
		
		return workTypeList;
	}
	
	public int selectAllScheduleReportCount(Map<String, Object> countMap) {
		
		SqlSession session = getSqlSession();
		
		int totalCount = scheduleDAO.selectAllScheduleReportCount(session, countMap);
		
		session.close();
		
		return totalCount;
	}

	
	

}
