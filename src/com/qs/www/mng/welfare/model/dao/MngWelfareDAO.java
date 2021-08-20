package com.qs.www.mng.welfare.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.common.paging.SelectCriteria;
import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;
import com.qs.www.schedule.model.dto.ReportDTO;
import com.qs.www.welfare.model.dto.LaptopDTO;

public class MngWelfareDAO {

	
	public List<WelfareYnDTO> selectWelfareYn(SqlSession session) {
		return session.selectList("WelfareDAO.selectWelfareYn");
	}

	public int updateWelfare(SqlSession session, WelfareYnDTO dto) {
		return session.update("WelfareDAO.updateWelfare",dto);
	}

	public List<ReportDTO> selectAllAppliedWelfareList(SqlSession session, SelectCriteria selectCriteria) {
		return session.selectList("WelfareDAO.selectAllAppliedWelfareList",selectCriteria);
	}

	public int selectWaitingAPPCount(SqlSession session, Map<String, String> searchMap) {
		return session.selectOne("WelfareDAO.selectWaitingAPPCount",searchMap);
	}

	public int selectNextItemNo(SqlSession session) {
		return session.selectOne("MngWelfareDAO.selectNextItemNo");
	}

	public int insertItem(SqlSession session, ItemDTO itemDTO) {
		return session.insert("MngWelfareDAO.insertItem",itemDTO);
	}

	public int deleteItem(SqlSession session, List<ItemDTO> itemList) {
		return session.delete("MngWelfareDAO.deleteItem",itemList);
	}

	public LaptopDTO selectOneItem(SqlSession session, int itemNo) {
		return session.selectOne("MngWelfareDAO.selectOneItem",itemNo);
	}
}