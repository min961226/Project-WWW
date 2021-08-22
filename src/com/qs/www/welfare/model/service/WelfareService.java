package com.qs.www.welfare.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.NoticeDTO;
import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;
import com.qs.www.schedule.model.dto.ApproverPerReportDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.schedule.model.dto.WorkingDocumentItemDTO;
import com.qs.www.welfare.model.dao.WelfareDAO;
import com.qs.www.welfare.model.dto.DomitoryListDTO;
import com.qs.www.welfare.model.dto.DomitoryWaitListDTO;
import com.qs.www.welfare.model.dto.FamilyEventDTO;
import com.qs.www.welfare.model.dto.LaptopDTO;
import com.qs.www.welfare.model.dto.MemberOverTimeLogDTO;
import com.qs.www.welfare.model.dto.NightTransportationLogDTO;
import com.qs.www.welfare.model.dto.SelfDevelopmetLogDTO;
import com.qs.www.welfare.model.dto.SeminarReservTimeDTO;
import com.qs.www.welfare.model.dto.SeminarRoomDTO;
import com.qs.www.welfare.model.dto.SeminarRoomReservDTO;
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

	public List<ReportDTO> selectAppliedWelfareList(HashMap<String, Object> selectedInfoMap) {

		SqlSession session = getSqlSession();

		List<ReportDTO> workReportList = welfareDAO.selectAppliedWelfareList(session, selectedInfoMap);

		session.close();

		return workReportList;
	}

	public List<SeminarRoomDTO> selectSeminarRoom() {
		SqlSession session = getSqlSession();

		List<SeminarRoomDTO> seminarRoomList = welfareDAO.selectSeminarRoom(session);

		session.close();

		return seminarRoomList;
	}

	public List<SeminarRoomReservDTO> selectSeminarRoomReserv(int roomNo) {
		SqlSession session = getSqlSession();

		List<SeminarRoomReservDTO> seminarRoomReserv = welfareDAO.selectSeminarRoomReserv(session, roomNo);

		session.close();

		return seminarRoomReserv;
	}

	public List<SeminarReservTimeDTO> selectSeminarReservTime() {
		SqlSession session = getSqlSession();

		List<SeminarReservTimeDTO> seminarReservTime = welfareDAO.selectSeminarReservTime(session);

		session.close();

		return seminarReservTime;
	}

	public int insertSeminarRoom(SeminarRoomReservDTO seminarRoomReservDTO) {

		SqlSession session = getSqlSession();

		int insertSeminarRoomResult = welfareDAO.insertSeminarRoom(session, seminarRoomReservDTO);

		if (insertSeminarRoomResult > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return insertSeminarRoomResult;
	}

	public List<LaptopDTO> selectLaptopList() {
		SqlSession session = getSqlSession();

		List<LaptopDTO> laptopList = welfareDAO.selectLaptopList(session);

		session.close();

		return laptopList;
	}

	public String selectOneLaptop(int itemNo) {
		SqlSession session = getSqlSession();

		String laptopStatus = welfareDAO.selectOneLaptop(session, itemNo);

		session.close();

		return laptopStatus;
	}

	public String selectItemNameByItemNo(int itemNo) {
		SqlSession session = getSqlSession();

		String itemName = welfareDAO.selectItemNameByItemNo(session, itemNo);

		session.close();

		return itemName;
	}

	public List<SeminarRoomReservDTO> selectSeminarRoomByMemberNo(HashMap<String, Object> selectedInfoMap) {
		SqlSession session = getSqlSession();

		List<SeminarRoomReservDTO> seminarRoomList = welfareDAO.selectSeminarRoomByMemberNo(session, selectedInfoMap);

		session.close();

		return seminarRoomList;
	}

	public SeminarRoomReservDTO selectAppliedSeminarRoom(SeminarRoomReservDTO seminarRoomReservDTO) {
		SqlSession session = getSqlSession();

		SeminarRoomReservDTO seminarRoom = welfareDAO.selectAppliedSeminarRoom(session, seminarRoomReservDTO);

		session.close();

		return seminarRoom;
	}

	public int deleteAppliedSeminarRoom(SeminarRoomReservDTO seminarRoomReservDTO) {
		
		SqlSession session = getSqlSession();

		int deleteAppliedSeminarRoomResult = welfareDAO.deleteAppliedSeminarRoom(session, seminarRoomReservDTO);

		if (deleteAppliedSeminarRoomResult > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return deleteAppliedSeminarRoomResult;
	}

	public int selectMyWelfareListCount(HashMap<String, Object> countMap) {

		SqlSession session = getSqlSession();

		int welfareCount = welfareDAO.selectMyWelfareListCount(session, countMap);

		session.close();

		return welfareCount;
	}

	public int selectMySeminarRoomListCount(HashMap<String, Object> countMap) {

		SqlSession session = getSqlSession();

		int seminarRoomCount = welfareDAO.selectMySeminarRoomListCount(session, countMap);

		session.close();

		return seminarRoomCount;
	}

	public int insertDomitoryWaitList(DomitoryWaitListDTO domitoryWaitListDTO) {
		SqlSession session = getSqlSession();

		int insertDomitoryWaitListResult = welfareDAO.insertDomitoryWaitList(session, domitoryWaitListDTO);

		if (insertDomitoryWaitListResult > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return insertDomitoryWaitListResult;
	}

	public int insertItemLog(ItemDTO itemDTO) {
		SqlSession session = getSqlSession();

		int insertItemLog = welfareDAO.insertItemLog(session, itemDTO);

		if (insertItemLog > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return insertItemLog;
	}

	public int updateItemStatus(int itemNo) {
		SqlSession session = getSqlSession();

		int updateItemStatus = welfareDAO.updateItemStatus(session, itemNo);

		if (updateItemStatus > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return updateItemStatus;
	}

	public int insertSelfDevLog(SelfDevelopmetLogDTO selfDevelopmetLogDTO) {
		SqlSession session = getSqlSession();

		int insertSelfDevLog = welfareDAO.insertSelfDevLog(session, selfDevelopmetLogDTO);

		if (insertSelfDevLog > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return insertSelfDevLog;
	}

	public int insertFamilyEventLog(SelfDevelopmetLogDTO selfDevelopmetLogDTO) {
		SqlSession session = getSqlSession();

		int insertFamilyEventLog = welfareDAO.insertFamilyEventLog(session, selfDevelopmetLogDTO);

		if (insertFamilyEventLog > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return insertFamilyEventLog;
	}

	public List<DomitoryWaitListDTO> selectDomitoryWaitList() {
		SqlSession session = getSqlSession();

		List<DomitoryWaitListDTO> selectDomitoryWaitList = welfareDAO.selectDomitoryWaitList(session);

		session.close();

		return selectDomitoryWaitList;
	}

	public int insertNightTransLog(NightTransportationLogDTO nightTransportationLogDTO) {
		SqlSession session = getSqlSession();

		int insertNightTransLog = welfareDAO.insertNightTransLog(session, nightTransportationLogDTO);

		if (insertNightTransLog > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return insertNightTransLog;
	}


}
