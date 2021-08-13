package com.qs.www.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.approval.model.dto.ApproverDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
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

	

	

}
