package com.qs.www.welfare.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.NoticeDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.welfare.model.dao.WelfareDAO;
import com.qs.www.welfare.model.dto.DomitoryListDTO;
import com.qs.www.welfare.model.dto.FamilyEventDTO;
import com.qs.www.welfare.model.dto.MemberOverTimeLogDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;

import static com.qs.www.common.mybatis.Template.getSqlSession;

public class WelfareService {

	private final WelfareDAO welfareDAO;

	public WelfareService() {
		welfareDAO = new WelfareDAO();
	}

	public List<String> checkWelfareList() {

		SqlSession session = getSqlSession();

		List<String> welfareList = welfareDAO.checkWelfareList(session);

		session.close();

		return welfareList;
	}

	public List<String> checkSelfDevList() {
		SqlSession session = getSqlSession();

		List<String> selfDevList = welfareDAO.checkSelfDevList(session);

		session.close();

		return selfDevList;
	}

	public List<String> selectApproverLine(int memberNo) {
		SqlSession session = getSqlSession();

		List<String> approverLine = welfareDAO.selectApproverLine(session, memberNo);

		session.close();

		return approverLine;
	}

	public int selectReportNum() {
		SqlSession session = getSqlSession();

		int reportNum = welfareDAO.selectReportNum(session);

		session.close();

		return reportNum;
	}

	public int insertWelfareReport(WelfareListDTO welfareListDTO) {

		SqlSession session = getSqlSession();

		int result = welfareDAO.insertWelfareReport(session, welfareListDTO);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return result;
	}

	public int selectDevNo(String lineName) {
		SqlSession session = getSqlSession();

		int devNo = welfareDAO.selectDevNo(session, lineName);

		session.close();

		return devNo;
	}

	public int selectLimitCost(int developmentNo) {
		SqlSession session = getSqlSession();

		int limitCost = welfareDAO.selectLimitCost(session, developmentNo);

		session.close();

		return limitCost;
	}

	public int insertWelfareItemContent(WorkingDocumentItemDTO documentItemDTO) {
		SqlSession session = getSqlSession();

		int result = welfareDAO.insertWelfareItemContent(session, documentItemDTO);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return result;
	}

	public int insertWelfareApprover(ApproverPerReportDTO approverPerReportDTO) {

		SqlSession session = getSqlSession();

		int result = welfareDAO.insertWelfareApprover(session, approverPerReportDTO);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return result;
	}

	public int selectEventNo(FamilyEventDTO familyEventDTO) {
		SqlSession session = getSqlSession();

		int eventNo = welfareDAO.selectEventNo(session, familyEventDTO);

		session.close();

		return eventNo;
	}

	public int selectSupportFund(int eventNo) {
		SqlSession session = getSqlSession();

		int supportFund = welfareDAO.selectSupportFund(session, eventNo);

		session.close();

		return supportFund;
	}

	public List<MemberOverTimeLogDTO> checkNightTrans(int memberNo) {
		SqlSession session = getSqlSession();

		List<MemberOverTimeLogDTO> checkNightTrans = welfareDAO.checkNightTrans(session, memberNo);

		session.close();

		return checkNightTrans;
	}

	public List<DomitoryListDTO> selectDomitory() {

		SqlSession session = getSqlSession();

		List<DomitoryListDTO> domitoryList = welfareDAO.selectDomitory(session);

		session.close();

		return domitoryList;
	}

	public List<ReportDTO> selectAppliedWelfareList(int no) {
		
		SqlSession session = getSqlSession();

		List<ReportDTO> workReportList = welfareDAO.selectAppliedWelfareList(session, no);

		session.close();

		return workReportList;
	}

}
