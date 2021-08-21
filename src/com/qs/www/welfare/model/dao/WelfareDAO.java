package com.qs.www.welfare.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.welfare.model.dto.DomitoryListDTO;
import com.qs.www.welfare.model.dto.DomitoryWaitListDTO;
import com.qs.www.welfare.model.dto.FamilyEventDTO;
import com.qs.www.welfare.model.dto.LaptopDTO;
import com.qs.www.welfare.model.dto.MemberOverTimeLogDTO;
import com.qs.www.welfare.model.dto.SelfDevelopmetLogDTO;
import com.qs.www.welfare.model.dto.SeminarReservTimeDTO;
import com.qs.www.welfare.model.dto.SeminarRoomDTO;
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;


public class WelfareDAO {

	public List<String> checkWelfareList(SqlSession session) {
		return session.selectList("WelfareDAO.selectWelfareList");
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
	
	public int insertWelfareReport(SqlSession session, WelfareListDTO welfareListDTO) {
		return session.insert("WelfareDAO.insertWelfareReport", welfareListDTO);
	}

	public int insertWelfareItemContent(SqlSession session, WorkingDocumentItemDTO documentItemDTO) {
		return session.insert("ScheduleDAO.applyWorkingSystemItemContent", documentItemDTO);
	}

	public int insertWelfareApprover(SqlSession session, ApproverPerReportDTO approverPerReportDTO) {
		return session.insert("ScheduleDAO.applyWorkingSystemApprover", approverPerReportDTO);
	}

	public int selectEventNo(SqlSession session, FamilyEventDTO familyEventDTO) {
		return session.selectOne("WelfareDAO.selectEventNo", familyEventDTO);
	}

	public int selectSupportFund(SqlSession session, int eventNo) {
		return session.selectOne("WelfareDAO.selectSupportFund", eventNo);
	}

	public List<MemberOverTimeLogDTO> checkNightTrans(SqlSession session, int memberNo) {
		return session.selectList("WelfareDAO.checkNightTrans", memberNo);
	}

	public List<DomitoryListDTO> selectDomitory(SqlSession session) {
		return session.selectList("WelfareDAO.selectDomitory");
	}

	public List<ReportDTO> selectAppliedWelfareList(SqlSession session, HashMap<String, Object> selectedInfoMap) {
		return session.selectList("WelfareDAO.selectAppliedWelfareList", selectedInfoMap);
	}

	public List<SeminarRoomDTO> selectSeminarRoom(SqlSession session) {
		return session.selectList("WelfareDAO.selectSeminarRoom");
	}

	public List<SeminarRoomReservDTO> selectSeminarRoomReserv(SqlSession session, int roomNo) {
		return session.selectList("WelfareDAO.selectSeminarRoomReserv", roomNo);
	}

	public List<SeminarReservTimeDTO> selectSeminarReservTime(SqlSession session) {
		return session.selectList("WelfareDAO.selectSeminarReservTime");
	}

	public int insertSeminarRoom(SqlSession session, SeminarRoomReservDTO seminarRoomReservDTO) {
		return session.insert("WelfareDAO.insertSeminarRoom", seminarRoomReservDTO);
	}

	public List<LaptopDTO> selectLaptopList(SqlSession session) {
		return session.selectList("WelfareDAO.selectLaptopList");
	}

	public String selectOneLaptop(SqlSession session, int itemNo) {
		return session.selectOne("WelfareDAO.selectOneLaptop",itemNo);
	}

	public String selectItemNameByItemNo(SqlSession session, int itemNo) {
		return session.selectOne("WelfareDAO.selectItemNameByItemNo",itemNo);
	}

	public List<SeminarRoomReservDTO> selectSeminarRoomByMemberNo(SqlSession session, HashMap<String, Object> selectedInfoMap) {
		return session.selectList("WelfareDAO.selectSeminarRoomByMemberNo",selectedInfoMap);
	}

	public SeminarRoomReservDTO selectAppliedSeminarRoom(SqlSession session,SeminarRoomReservDTO seminarRoomReservDTO) {
		return session.selectOne("WelfareDAO.selectAppliedSeminarRoom",seminarRoomReservDTO);
	}

	public int deleteAppliedSeminarRoom(SqlSession session, SeminarRoomReservDTO seminarRoomReservDTO) {
		return session.delete("WelfareDAO.deleteAppliedSeminarRoom", seminarRoomReservDTO);
	}

	public int selectMyWelfareListCount(SqlSession session, HashMap<String, Object> countMap) {
		return session.selectOne("WelfareDAO.selectMyWelfareListCount",countMap);
	}

	public int selectMySeminarRoomListCount(SqlSession session, HashMap<String, Object> countMap) {
		return session.selectOne("WelfareDAO.selectMySeminarRoomListCount",countMap);
	}

	public int insertDomitoryWaitList(SqlSession session, DomitoryWaitListDTO domitoryWaitListDTO) {
		return session.insert("WelfareDAO.insertDomitoryWaitList", domitoryWaitListDTO);
	}

	public int insertItemLog(SqlSession session, ItemDTO itemDTO) {
		return session.insert("WelfareDAO.insertItemLog", itemDTO);
	}

	public int updateItemStatus(SqlSession session, int itemNo) {
		return session.update("WelfareDAO.updateItemStatus",itemNo);
	}

	public int insertSelfDevLog(SqlSession session, SelfDevelopmetLogDTO selfDevelopmetLogDTO) {
		return session.insert("WelfareDAO.insertSelfDevLog", selfDevelopmetLogDTO);
	}

	public int insertFamilyEventLog(SqlSession session, SelfDevelopmetLogDTO selfDevelopmetLogDTO) {
		return session.insert("WelfareDAO.insertFamilyEventLog", selfDevelopmetLogDTO);
	}

	public List<DomitoryWaitListDTO> selectDomitoryWaitList(SqlSession session) {
		return session.selectList("WelfareDAO.selectDomitoryWaitList");
	}

	





}
