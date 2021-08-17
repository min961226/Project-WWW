package com.qs.www.mng.welfare.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.welfare.model.dao.MngWelfareDAO;
import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;
import com.qs.www.schedule.model.dto.ReportDTO;

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

	public List<ReportDTO> selectAllAppliedWelfareList() {
		
		SqlSession session = getSqlSession();

		List<ReportDTO> allAppliedWelfareList = mngWelfareDAO.selectAllAppliedWelfareList(session);

		session.close();

		return allAppliedWelfareList;
	}
	
}
