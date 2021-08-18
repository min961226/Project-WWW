package com.qs.www.mng.welfare.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

public class MngWelfareDAO {

	
	public List<WelfareYnDTO> selectWelfareYn(SqlSession session) {
		return session.selectList("WelfareDAO.selectWelfareYn");
	}

	public int updateWelfare(SqlSession session, WelfareYnDTO dto) {
		return session.update("WelfareDAO.updateWelfare",dto);
	}

	public List<ReportDTO> selectAllAppliedWelfareList(SqlSession session) {
		return session.selectList("WelfareDAO.selectAllAppliedWelfareList");
	}
}