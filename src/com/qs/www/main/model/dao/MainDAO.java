package com.qs.www.main.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.NoticeDTO;
import com.qs.www.main.model.dto.AuthorityDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.dto.WorkingTypeDTO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;

public class MainDAO {

	public List<CommutingLogDTO> selectCommutingLog(SqlSession sqlSession, WorkInfoDTO workInfo) {
		return sqlSession.selectList("MainDAO.selectCommutingLog", workInfo);
	}

	public WorkingLogDTO selectWorkingLog(SqlSession sqlSession, WorkInfoDTO workInfo) {
		return sqlSession.selectOne("MainDAO.selectWorkingLog", workInfo);
	}

	public WorkingTypeDTO selectWorkingType(SqlSession sqlSession, WorkingLogDTO workingLog) {
		return sqlSession.selectOne("MainDAO.selectWorkingType", workingLog);
	}

	public List<AuthorityDTO> selectAccessAuthorityList(SqlSession sqlSession, String roleCode) {
		return sqlSession.selectList("MainDAO.selectAccessAuthorityList", roleCode);
	}

	public String selectCommute(SqlSession sqlSession, WorkInfoDTO workInfo) {
		return sqlSession.selectOne("MainDAO.selectCommute", workInfo);
	}
	
	public int insertCommute(SqlSession sqlSession, WorkInfoDTO workInfo) {
		return sqlSession.insert("MainDAO.insertCommute", workInfo);
	}

	public List<NoticeDTO> selectNoticeList(SqlSession sqlSession) {
		return sqlSession.selectList("MainDAO.selectNoticeList");
	}

	public List<WelfareListDTO> selectWelfareList(SqlSession sqlSession, int memberNo) {
		return sqlSession.selectList("MainDAO.selectWelfareList",memberNo);
	}
}
