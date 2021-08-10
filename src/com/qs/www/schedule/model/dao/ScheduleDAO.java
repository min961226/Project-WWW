package com.qs.www.schedule.model.dao;

import org.apache.ibatis.session.SqlSession;

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

}
