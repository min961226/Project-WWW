package com.qs.www.schedule.model.service;

import com.qs.www.schedule.model.dao.ScheduleDAO;
import com.qs.www.schedule.model.dto.MemberWorkLogDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

public class ScheduleService {
	
	private final ScheduleDAO scheduleDAO;
	
	public ScheduleService() {
		scheduleDAO = new ScheduleDAO();
	}

	public int applyWorkingSystem(MemberWorkLogDTO memberWorkLogDTO) {
		
		SqlSession session = getSqlSession();
		
		int result = scheduleDAO.applyWorkingSystem(session, memberWorkLogDTO);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

}
