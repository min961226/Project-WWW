package com.qs.www.mypage.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.member.model.dto.CheckQuestionDTO;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.mypage.model.dao.MypageDAO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;

public class MypageService {
	
	private final MypageDAO mypageDAO;
	
	public MypageService() {
		mypageDAO = new MypageDAO();
	}
	
	public List<CheckQuestionDTO> selectQuestionList() {
		
		SqlSession sqlSession = getSqlSession();
		
		List<CheckQuestionDTO> questionList = mypageDAO.selectQuestionList(sqlSession);
		
		sqlSession.close();
		
		return questionList;
	}

	public int updateInfo(MemberInfoDTO memberInfo) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = mypageDAO.updateInfo(sqlSession, memberInfo);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

	public int insertCommute(CommutingLogDTO commutingLog) {
		
		SqlSession sqlSession = getSqlSession();
		
		int result = mypageDAO.insertCommute(sqlSession, commutingLog);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		return result;
	}
}
