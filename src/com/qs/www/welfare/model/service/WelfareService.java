package com.qs.www.welfare.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.welfare.model.dao.WelfareDAO;

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

	public String selectDeptName(String deptCode) {
		SqlSession session = getSqlSession();

		String deptName = welfareDAO.selectDeptName(session, deptCode);

		session.close();

		return deptName;
	}

	public String selectJobName(String jobCode) {
		SqlSession session = getSqlSession();

		String jobName = welfareDAO.selectJobName(session, jobCode);

		session.close();

		return jobName;
	}

	public List<String> checkSelfDevList() {
		SqlSession session = getSqlSession();

		List<String> selfDevList = welfareDAO.checkSelfDevList(session);

		session.close();

		return selfDevList;
	}

	public List<String> selectApproverLine(int memberNo) {
		SqlSession session = getSqlSession();

		List<String> approverLine = welfareDAO.selectApproverLine(session,memberNo);

		session.close();

		return approverLine;
	}

}
