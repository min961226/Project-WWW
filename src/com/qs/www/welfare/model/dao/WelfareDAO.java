package com.qs.www.welfare.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;


public class WelfareDAO {

	public List<String> checkWelfareList(SqlSession session) {
		return session.selectList("WelfareDAO.selectWelfareList");
	}

	public List<String> selectApproverLine(SqlSession session, int memberNo) {
		return session.selectList("WelfareDAO.selectApproverLine", memberNo);
	}
	
	public List<String> checkSelfDevList(SqlSession session) {
		return session.selectList("WelfareDAO.checkSelfDevList");
	}

	public int selectReportNum(SqlSession session) {
		return session.selectOne("WelfareDAO.selectReportNum");
	}
	
	public int selectDevNo(SqlSession session, String lineName) {
		return session.selectOne("WelfareDAO.selectDevNo",lineName);
	}
	
	public int selectLimitCost(SqlSession session, int developmentNo) {
		return session.selectOne("WelfareDAO.selectLimitCost",developmentNo);
	}
	
	public int insertSelfDevelopment(SqlSession session, WelfareListDTO welfareListDTO) {
		return session.insert("WelfareDAO.insertSelfDevelopment", welfareListDTO);
	}

	public int insertSelfDevelopmentItemContent(SqlSession session, WorkingDocumentItemDTO documentItemDTO) {
		return session.insert("ScheduleDAO.applyWorkingSystemItemContent", documentItemDTO);
	}





}
