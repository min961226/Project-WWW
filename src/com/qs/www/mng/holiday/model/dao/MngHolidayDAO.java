package com.qs.www.mng.holiday.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mng.holiday.model.dto.HolidayRuleDTO;
import com.qs.www.mng.holiday.model.dto.MemberHolidayInfoDTO;
import com.qs.www.schedule.model.dto.HolidayLogDTO;
import com.qs.www.schedule.model.dto.HolidayTypeDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

public class MngHolidayDAO {
	
	public int selectHolidayAPPCount(SqlSession session, Map<String, String> searchMap) {
		
		return session.selectOne("MngHolidayDAO.selectHolidayAPPCount", searchMap);
	}

	public List<ReportDTO> selectHolidayAPP(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("MngHolidayDAO.selectHolidayAPP", selectCriteria);
	}
	
	public int selectHolidayLogNum(SqlSession session, int reportNo) {
		
		return session.selectOne("MngHolidayDAO.selectHolidayLogNum", reportNo);
	}

	public int cancleSelectedReport(SqlSession session, int reportNo) {
		
		return session.update("MngHolidayDAO.cancleSelectedReport", reportNo);
	}

	public int deleteHolidayUseInfo(SqlSession session, int logNo) {
		
		return session.delete("MngHolidayDAO.deleteHolidayUseInfo", logNo);
	}
	public int deleteHolidayLog(SqlSession session, int logNo) {
		
		return session.delete("MngHolidayDAO.deleteHolidayLog", logNo);
	}

	public String selectDuringDate(SqlSession session, int logNo) {
		
		return session.selectOne("MngHolidayDAO.selectDuringDate", logNo);
	}

	public List<HolidayRuleDTO> selectHolidayRule(SqlSession session) {
		
		return session.selectList("MngHolidayDAO.selectHolidayRule");
	}

	public int updateholidayRuleNumber(SqlSession session, Map<String, Object> ruleMap) {
		
		return session.update("MngHolidayDAO.updateholidayRuleNumber", ruleMap);
	}

	public int selectMemberHolidayInfoCount(SqlSession session, Map<String, String> searchMap) {
		
		return session.selectOne("MngHolidayDAO.selectMemberHolidayInfoCount", searchMap);
	}

	public List<MemberHolidayInfoDTO> selectMemberHolidayInfoList(SqlSession session, SelectCriteria selectCriteria) {
		
		return session.selectList("MngHolidayDAO.selectMemberHolidayInfoList", selectCriteria);
	}

	public List<HolidayLogDTO> selectHolidayLogList(SqlSession session, int memberNo) {
		
		return session.selectList("MngHolidayDAO.selectHolidayLogList", memberNo);
	}

	public MemberHolidayInfoDTO selectMemberHolidayInfo(SqlSession session, int memberNo) {
		
		return session.selectOne("MngHolidayDAO.selectMemberHolidayInfo", memberNo);
	}

	public int selectHolidayLogCount(SqlSession session, HashMap<String, Object> countMap) {
		
		return session.selectOne("MngHolidayDAO.selectHolidayLogCount", countMap);
	}

	public List<HolidayLogDTO> selectPagingHolidayLogList(SqlSession session, HashMap<String, Object> selectedInfoMap) {
		
		return session.selectList("MngHolidayDAO.selectPagingHolidayLogList", selectedInfoMap);
	}

	public MemberInfoDTO selectMemberInfo(SqlSession session, int memberNo) {
		
		return session.selectOne("MngHolidayDAO.selectMemberInfo", memberNo);
	}

	public int insertManualHolidayLog(SqlSession session, HolidayLogDTO holidayLogDTO) {
		
		return session.insert("MngHolidayDAO.insertManualHolidayLog", holidayLogDTO);
	}

	public List<HolidayTypeDTO> selectHolidayType(SqlSession session) {
		
		return session.selectList("MngHolidayDAO.selectHolidayType");
	}

	public HolidayTypeDTO selectOneHolidayType(SqlSession session, int no) {
		
		return session.selectOne("MngHolidayDAO.selectOneHolidayType", no);
	}

	public int updateHolidayType(SqlSession session, HolidayTypeDTO holidayCategory) {
		
		return session.update("MngHolidayDAO.updateHolidayType", holidayCategory);
	}

	public int insertHolidayType(SqlSession session, HolidayTypeDTO holidayCategory) {
		
		return session.insert("MngHolidayDAO.insertHolidayType", holidayCategory);
	}

	public int deleteHolidayType(SqlSession session, List<Integer> itemList) {
		
		return session.delete("MngHolidayDAO.deleteHolidayType", itemList);
	}

	
}
