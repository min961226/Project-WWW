package com.qs.www.mng.welfare.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.welfare.model.dao.MngWelfareDAO;
import com.qs.www.mng.welfare.model.dto.DomitoryLogDTO;
import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.welfare.model.dto.DomitoryListDTO;
import com.qs.www.welfare.model.dto.DomitoryWaitListDTO;
import com.qs.www.welfare.model.dto.LaptopDTO;

public class MngWelfareService {

	private final MngWelfareDAO mngWelfareDAO;

	public MngWelfareService() {
		mngWelfareDAO = new MngWelfareDAO();
	}

	public List<WelfareYnDTO> selectWelfareYn() {

		SqlSession session = getSqlSession();

		List<WelfareYnDTO> welfareYn = mngWelfareDAO.selectWelfareYn(session);

		session.close();

		return welfareYn;
	}

	public int updateWelfare(WelfareYnDTO dto) {

		SqlSession session = getSqlSession();

		int updateWelfare = mngWelfareDAO.updateWelfare(session, dto);

		if (updateWelfare > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return updateWelfare;
	}

	public List<ReportDTO> selectAllAppliedWelfareList(SelectCriteria selectCriteria) {

		SqlSession session = getSqlSession();

		List<ReportDTO> allAppliedWelfareList = mngWelfareDAO.selectAllAppliedWelfareList(session, selectCriteria);

		session.close();

		return allAppliedWelfareList;
	}

	public int selectWaitingAPPCount(Map<String, String> searchMap) {

		SqlSession session = getSqlSession();

		int appCount = mngWelfareDAO.selectWaitingAPPCount(session, searchMap);

		session.close();

		return appCount;
	}

	public int selectNextItemNo() {
		SqlSession session = getSqlSession();

		int nextItemNo = mngWelfareDAO.selectNextItemNo(session);

		session.close();

		return nextItemNo;
	}

	public int insertItem(ItemDTO itemDTO) {
		SqlSession session = getSqlSession();

		int insertItem = mngWelfareDAO.insertItem(session, itemDTO);

		if (insertItem > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return insertItem;
	}

	public int deleteItem(List<ItemDTO> itemList) {
		SqlSession session = getSqlSession();

		int deleteItem = mngWelfareDAO.deleteItem(session, itemList);

		if (deleteItem > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return deleteItem;
	}

	public ItemDTO selectOneItemLog(int itemNo) {
		SqlSession session = getSqlSession();

		ItemDTO selectOneItem = mngWelfareDAO.selectOneItemLog(session, itemNo);

		session.close();

		return selectOneItem;
	}

	public List<ItemDTO> selectAllItemLog(int itemNo) {
		SqlSession session = getSqlSession();

		List<ItemDTO> selectAllItem = mngWelfareDAO.selectAllItemLog(session, itemNo);

		session.close();

		return selectAllItem;
	}


	public int updateReturnItem(ItemDTO itemDTO) {

		SqlSession session = getSqlSession();

		int returnItem = mngWelfareDAO.updateReturnItem(session, itemDTO);

		if (returnItem > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return returnItem;
	}

	public int updateChangeStatus(int itemNo) {

		SqlSession session = getSqlSession();

		int updateChangeStatus = mngWelfareDAO.updateChangeStatus(session, itemNo);

		if (updateChangeStatus > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return updateChangeStatus;
	}

	public int selectMemberNoByWaitingNo(int waitingNo) {
		SqlSession session = getSqlSession();

		int memberNo = mngWelfareDAO.selectMemberNoByWaitingNo(session, waitingNo);

		session.close();

		return memberNo;
	}

	public int insertDomitoryLog(DomitoryLogDTO domitoryLogDTO) {

		SqlSession session = getSqlSession();

		int insertDomitoryLog = mngWelfareDAO.insertDomitoryLog(session, domitoryLogDTO);

		if (insertDomitoryLog > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return insertDomitoryLog;
	}
	
	public int deleteDomitoryWaitList(int waitingNo) {
		SqlSession session = getSqlSession();

		int deleteDomitoryWaitList = mngWelfareDAO.deleteDomitoryWaitList(session,waitingNo);

		if (deleteDomitoryWaitList > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return deleteDomitoryWaitList;
	}

	public int updateDomitoryCapacity(int domitoryNo) {
		SqlSession session = getSqlSession();

		int updateDomitory = mngWelfareDAO.updateDomitoryCapacity(session,domitoryNo);

		if (updateDomitory > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return updateDomitory;
	}

	public DomitoryListDTO selectDomitory(int domitoryNo) {
		SqlSession session = getSqlSession();

		DomitoryListDTO domitoryDTO = mngWelfareDAO.selectDomitory(session, domitoryNo);

		session.close();

		return domitoryDTO;
	}

	public List<DomitoryLogDTO> selectDomitoryLogResult(int domitoryManageNo) {
		SqlSession session = getSqlSession();

		List<DomitoryLogDTO> domitoryLogList = mngWelfareDAO.selectDomitoryLogResult(session, domitoryManageNo);

		session.close();

		return domitoryLogList;
	}

	public int updateOutReason(DomitoryLogDTO domitoryLogDTO) {
		SqlSession session = getSqlSession();

		int updateOutReason = mngWelfareDAO.updateOutReason(session,domitoryLogDTO);

		if (updateOutReason > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();
		
		return updateOutReason;
	}

	public int minusDomitoryCapacity(int domitoryManageNo) {
		SqlSession session = getSqlSession();

		int minusDomitoryCapacity = mngWelfareDAO.minusDomitoryCapacity(session,domitoryManageNo);

		if (minusDomitoryCapacity > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return minusDomitoryCapacity;
	}



}
