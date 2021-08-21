package com.qs.www.mng.holiday.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.holiday.model.dao.MngHolidayDAO;
import com.qs.www.mng.holiday.model.dto.HolidayRuleDTO;
import com.qs.www.mng.holiday.model.dto.MemberHolidayInfoDTO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

public class MngHolidayService {

	private final MngHolidayDAO mngHolidayDAO;

	public MngHolidayService() {
		mngHolidayDAO = new MngHolidayDAO();
	}

	public int selectHolidayAPPCount(Map<String, String> searchMap) {
		SqlSession session = getSqlSession();

		int count = mngHolidayDAO.selectHolidayAPPCount(session, searchMap);

		session.close();

		return count;
	}

	public List<ReportDTO> selectHolidayAPP(SelectCriteria selectCriteria) {
		SqlSession session = getSqlSession();

		List<ReportDTO> reportList = mngHolidayDAO.selectHolidayAPP(session, selectCriteria);

		session.close();

		return reportList;
	}

	public int selectHolidayLogNum(int reportNo) {
		SqlSession session = getSqlSession();

		int logNo = mngHolidayDAO.selectHolidayLogNum(session, reportNo);

		session.close();

		return logNo;
	}


	public int cancleSelectedReport(int reportNo) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.cancleSelectedReport(session, reportNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public int deleteHolidayUseInfo(int logNo) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.deleteHolidayUseInfo(session, logNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}
	public int deleteHolidayLog(int logNo) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.deleteHolidayLog(session, logNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public String selectDuringDate(int logNo) {
		SqlSession session = getSqlSession();

		String duringDate = mngHolidayDAO.selectDuringDate(session, logNo);

		session.close();

		return duringDate;
	}

	public List<HolidayRuleDTO> selectHolidayRule() {
		SqlSession session = getSqlSession();

		List<HolidayRuleDTO> holidayRuleList = mngHolidayDAO.selectHolidayRule(session);

		session.close();

		return holidayRuleList;
	}

	public int updateholidayRuleNumber(Map<String, Object> ruleMap) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.updateholidayRuleNumber(session, ruleMap);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public int selectMemberHolidayInfoCount(Map<String, String> searchMap) {
		SqlSession session = getSqlSession();

		int count = mngHolidayDAO.selectMemberHolidayInfoCount(session, searchMap);

		session.close();

		return count;
	}

	public List<MemberHolidayInfoDTO> selectMemberHolidayInfoList(SelectCriteria selectCriteria) {
		SqlSession session = getSqlSession();

		List<MemberHolidayInfoDTO> infoList = mngHolidayDAO.selectMemberHolidayInfoList(session, selectCriteria);

		session.close();

		return infoList;
	}

	public List<HolidayLogDTO> selectHolidayLogList(int memberNo) {
		SqlSession session = getSqlSession();

		List<HolidayLogDTO> logList = mngHolidayDAO.selectHolidayLogList(session, memberNo);

		session.close();

		return logList;
	}

	public MemberHolidayInfoDTO selectMemberHolidayInfo(int memberNo) {
		SqlSession session = getSqlSession();

		MemberHolidayInfoDTO info = mngHolidayDAO.selectMemberHolidayInfo(session, memberNo);

		session.close();

		return info;
	}

	public int selectHolidayLogCount(HashMap<String, Object> countMap) {
		SqlSession session = getSqlSession();

		int conunt = mngHolidayDAO.selectHolidayLogCount(session, countMap);

		session.close();

		return conunt;
	}

	public List<HolidayLogDTO> selectPagingHolidayLogList(HashMap<String, Object> selectedInfoMap) {
		SqlSession session = getSqlSession();

		List<HolidayLogDTO> logList = mngHolidayDAO.selectPagingHolidayLogList(session, selectedInfoMap);

		session.close();

		return logList;
	}

	public MemberInfoDTO selectMemberInfo(int memberNo) {
		SqlSession session = getSqlSession();

		MemberInfoDTO memberInfo = mngHolidayDAO.selectMemberInfo(session, memberNo);

		session.close();

		return memberInfo;
	}

	public int insertManualHolidayLog(HolidayLogDTO holidayLogDTO) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.insertManualHolidayLog(session, holidayLogDTO);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public List<HolidayTypeDTO> selectHolidayType() {
		SqlSession session = getSqlSession();

		List<HolidayTypeDTO> categoryList = mngHolidayDAO.selectHolidayType(session);

		session.close();

		return categoryList;
	}

	public HolidayTypeDTO selectOneHolidayType(int no) {
		SqlSession session = getSqlSession();

		HolidayTypeDTO category= mngHolidayDAO.selectOneHolidayType(session, no);

		session.close();

		return category;
	}

	public int updateHolidayType(HolidayTypeDTO holidayCategory) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.updateHolidayType(session, holidayCategory);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public int insertHolidayType(HolidayTypeDTO holidayCategory) {
		SqlSession session = getSqlSession();

		int result = mngHolidayDAO.insertHolidayType(session, holidayCategory);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}




}
