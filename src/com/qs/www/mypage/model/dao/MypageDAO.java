package com.qs.www.mypage.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.dto.WorkingTypeDTO;
import com.qs.www.member.model.dto.CheckQuestionDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;

public class MypageDAO {
	
	public List<CheckQuestionDTO> selectQuestionList(SqlSession sqlSession) {
		return sqlSession.selectList("MypageDAO.selectQuestionList");
	}

	public int updateInfo(SqlSession sqlSession, MemberInfoDTO memberInfo) {
		return sqlSession.update("MypageDAO.updateInfo", memberInfo);
	}

	public String selectCommuteInTime(SqlSession sqlSession, CommutingLogDTO commutingLog) {
		return sqlSession.selectOne("MypageDAO.selectCommuteInTime", commutingLog);
	}
	
	public String selectCommuteOutTime(SqlSession sqlSession, CommutingLogDTO commutingLog) {
		return sqlSession.selectOne("MypageDAO.selectCommuteOutTime", commutingLog);
	}
	
	public WorkingTypeDTO selectWorkingType(SqlSession sqlSession, WorkingLogDTO workingLog) {
		return sqlSession.selectOne("MypageDAO.selectWorkingType", workingLog);
	}
	
	public int updateCommuteTo(SqlSession sqlSession, CommutingLogDTO commutingLog) {
		return sqlSession.update("MypageDAO.updateCommuteTo", commutingLog);
	}

	public int updateCommuteFrom(SqlSession sqlSession, CommutingLogDTO commutingLog) {
		return sqlSession.update("MypageDAO.updateCommuteFrom", commutingLog);
	}
}
