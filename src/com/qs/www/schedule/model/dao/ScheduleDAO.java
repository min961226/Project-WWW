package com.qs.www.schedule.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.schedule.model.dto.MemberWorkLogDTO;

public class ScheduleDAO {

	public int applyWorkingSystem(SqlSession session, MemberWorkLogDTO memberWorkLogDTO) {
		
		return session.insert("ScheduleDAO.applyWorkingSystem");
	}

}
