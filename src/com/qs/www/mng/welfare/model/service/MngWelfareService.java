package com.qs.www.mng.welfare.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.welfare.model.dao.MngWelfareDAO;
import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
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

	public LaptopDTO selectOneItem(int itemNo) {
		SqlSession session = getSqlSession();

		LaptopDTO selectOneItem = mngWelfareDAO.selectOneItem(session, itemNo);

		session.close();

		return selectOneItem;
	}

}
