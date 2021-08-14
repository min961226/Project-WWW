package com.qs.www.schedule.model.service;

import com.qs.www.schedule.model.dao.ScheduleDAO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.MonthlyWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

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
	public int countLateTimeNum(MonthlyWorkLogDTO monthlyWorkLogDTO) {
		
		SqlSession session = getSqlSession();
		
		int lateNum = scheduleDAO.countLateTimeNum(session, monthlyWorkLogDTO);
		
		session.close();
		
		return lateNum;
	}
	
	/* 해당월 출근 미체크 횟수 COUNT */
	public int countNoCheckInTimeNum(MonthlyWorkLogDTO monthlyWorkLogDTO) {
		
		SqlSession session = getSqlSession();
		
		int noCheckInTimeNum = scheduleDAO.countNoCheckInTimeNum(session, monthlyWorkLogDTO);
		
		session.close();
		
		return noCheckInTimeNum;
	}
	
	/* 해당월 퇴근 미체크 횟수 COUNT */
	public int countNoCheckOutTimeNum(MonthlyWorkLogDTO monthlyWorkLogDTO) {
		
		SqlSession session = getSqlSession();
		
		int noCheckOutTimeNum = scheduleDAO.countNoCheckOutTimeNum(session, monthlyWorkLogDTO);
		
		session.close();
		
		return noCheckOutTimeNum;
	}
	
	/* 오늘 퇴근체크 여부 확인 (count 사용) */
	public int checkedOutToday(MonthlyWorkLogDTO monthlyWorkLogDTO) {
		
		SqlSession session = getSqlSession();
		
		int isCheckedOut = scheduleDAO.checkedOutToday(session, monthlyWorkLogDTO);
		
		session.close();
		
		return isCheckedOut;
	}

	
	

}
