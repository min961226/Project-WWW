package com.qs.www.schedule.model.service;

import com.qs.www.schedule.model.dao.ScheduleDAO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

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

}
