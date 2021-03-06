package com.qs.www.mypage.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.dto.WorkingTypeDTO;
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
	
	public WorkingTypeDTO selectWorkingType(WorkingLogDTO workingLog) {
		
		SqlSession sqlSession = getSqlSession();
		
		WorkingTypeDTO workingType = mypageDAO.selectWorkingType(sqlSession, workingLog);
		
		sqlSession.close();
		
		return workingType;
	}

	public int updateCommuteTo(CommutingLogDTO commutingLog) {
		
		SqlSession sqlSession = getSqlSession();
		
		String inTime = mypageDAO.selectCommuteInTime(sqlSession, commutingLog);
		String checkInTime = commutingLog.getWorkingLog().getWorkingType().getCheckInTime();
		
		int result = 0;
		if(inTime == null) {
			LocalTime localInTime = LocalTime.parse(commutingLog.getInTime());
			LocalTime localCheckInTime = LocalTime.parse(checkInTime);
			
			if(localInTime.isAfter(localCheckInTime)) {
				commutingLog.setLateYn("Y");
			} else {
				commutingLog.setLateYn("N");
			}
			
			result = mypageDAO.updateCommuteTo(sqlSession, commutingLog);
			
			if(result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		}
		
		return result;
	}

	public int updateCommuteFrom(CommutingLogDTO commutingLog, LocalDateTime currentDateTime) {
		
		SqlSession sqlSession = getSqlSession();
		// ????????? ?????? ??????
		String inTime = mypageDAO.selectCommuteInTime(sqlSession, commutingLog);
		String outTime = mypageDAO.selectCommuteOutTime(sqlSession, commutingLog);
		String checkOutTime = commutingLog.getWorkingLog().getWorkingType().getCheckOutTime();
		int result = 0;
		
		// ?????? ????????? ?????? ??????
		if(outTime == null) {
			// ?????? ????????? ?????? ???????????? ??????
			outTime = commutingLog.getOutTime();
			int outTimeHour = Integer.parseInt(outTime.substring(0, 2));
			
			LocalTime localOutTime = LocalTime.parse(commutingLog.getOutTime());
			LocalTime localCheckOutTime = LocalTime.parse(checkOutTime);
			
			if(localOutTime.isBefore(localCheckOutTime)) {
				commutingLog.setLeaveEarlyYn("Y");
			} else {
				commutingLog.setLeaveEarlyYn("N");
			}
			
			// ?????? ????????? ?????? ??????, ?????? ?????? ??????
			if(inTime != null) {
				result = mypageDAO.updateCommuteFrom(sqlSession, commutingLog);
			
			// ?????? ????????? ?????? ??????
			} else {
				// ?????? ????????? ?????? 6??? ????????? ??????, ????????? ????????? ???????????? ??????
				if(outTimeHour >= 6) {
					result = mypageDAO.updateCommuteFrom(sqlSession, commutingLog);
				// ?????? ????????? ?????? 6??? ????????? ??????, ?????? ?????? ????????? ??????
				} else {
					commutingLog.setYearMonth(currentDateTime.minusDays(1)
							.format(DateTimeFormatter.ofPattern("yyyy-MM")));
					commutingLog.setDay(currentDateTime.minusDays(1)
							.format(DateTimeFormatter.ofPattern("dd")));
					
					String yesterdayOutTime = mypageDAO.selectCommuteOutTime(sqlSession, commutingLog);
					
					// ?????? ?????? ????????? ?????? ?????? ??????
					if(yesterdayOutTime == null) {
						result = mypageDAO.updateCommuteFrom(sqlSession, commutingLog);
					}
				}
			}
			commutingLog.setOutTime(outTime);
		}
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		return result;
	}
}
