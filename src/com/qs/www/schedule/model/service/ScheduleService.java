package com.qs.www.schedule.model.service;

import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.schedule.model.dao.ScheduleDAO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.Iterator;
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

}
