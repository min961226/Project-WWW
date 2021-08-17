package com.qs.www.mng.welfare.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.mng.welfare.model.dao.MngWelfareDAO;
import com.qs.www.mng.welfare.model.dto.WelfareYnDTO;

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
	
}
