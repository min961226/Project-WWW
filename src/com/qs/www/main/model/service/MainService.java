package com.qs.www.main.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.board.model.dto.NoticeDTO;
import com.qs.www.main.model.dao.MainDAO;
import com.qs.www.main.model.dto.AuthorityDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.dto.WorkingTypeDTO;
import com.qs.www.mypage.model.dto.CommutingLogDTO;
import com.qs.www.welfare.model.dto.WelfareListDTO;

public class MainService {

	private final MainDAO mainDAO;

	public MainService() {
		mainDAO = new MainDAO();
	}

	// 출퇴근 기록 조회
	public List<CommutingLogDTO> selectCommutingLog(WorkInfoDTO workInfo) {

		SqlSession sqlSession = getSqlSession();

		List<CommutingLogDTO> commutingLogList = mainDAO.selectCommutingLog(sqlSession, workInfo);

		sqlSession.close();

		return commutingLogList;
	}

	// 근무 유형 조회
	public WorkingLogDTO selectWorkingLog(WorkInfoDTO workInfo) {

		SqlSession sqlSession = getSqlSession();

		WorkingLogDTO workingLog = new WorkingLogDTO();
		workingLog = mainDAO.selectWorkingLog(sqlSession, workInfo);

		sqlSession.close();

		return workingLog;
	}

	// 이번 주 근무 유형 및 시간 조회
	public List<WorkingLogDTO> selectWorkingLogList(WorkInfoDTO workInfo) {

		SqlSession sqlSession = getSqlSession();

		List<WorkingLogDTO> workingLogList = new ArrayList<>();

		// 날짜별 근무 유형 및 시간 조회(월요일 ~ 일요일)
		for (int i = 0; i < 7; i++) {
			// 월요일부터 일요일까지 반복
			LocalDate selectedLocalDate = workInfo.getSelectedLocalDate().plusDays(i);
			// 해당 날짜를 String으로 형변환
			String selectedDate = selectedLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			workInfo.setSelectedDate(selectedDate);
			// 해당 날짜의 요일 구하기
			DayOfWeek dayOfWeek = selectedLocalDate.getDayOfWeek();
			String[] week = { "월", "화", "수", "목", "금", "토", "일" };
			String selectedDayOfWeek = week[dayOfWeek.getValue() - 1];

			// 근무 이력 조회하여 현재 적용 중인 근무 제도 확인
			WorkingLogDTO workingLog = new WorkingLogDTO();
			workingLog = mainDAO.selectWorkingLog(sqlSession, workInfo);

			if (workingLog != null) {
				workingLog.setSelectedDate(selectedDate);
				workingLog.setSelectedDayOfWeek(selectedDayOfWeek);
				// 현재 적용 중인 근무의 상세 내용을 조회
				WorkingTypeDTO workingType = new WorkingTypeDTO();
				workingType = mainDAO.selectWorkingType(sqlSession, workingLog);

				workingLog.setWorkingType(workingType);
				workingLogList.add(workingLog);
			}
		}

		sqlSession.close();

		return workingLogList;
	}

	public List<AuthorityDTO> selectAccessAuthorityList(String roleCode) {

		SqlSession sqlSession = getSqlSession();

		List<AuthorityDTO> roleAuthorityList = mainDAO.selectAccessAuthorityList(sqlSession, roleCode);

		sqlSession.close();

		return roleAuthorityList;
	}

	public int insertCommute(WorkInfoDTO workInfo) {

		SqlSession sqlSession = getSqlSession();

		String commuteLog = mainDAO.selectCommute(sqlSession, workInfo);
		int result = 0;

		if (commuteLog == null) {
			result = mainDAO.insertCommute(sqlSession, workInfo);

			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		}

		sqlSession.close();

		return result;
	}

	public List<NoticeDTO> selectNoticeList() {
		SqlSession sqlSession = getSqlSession();

		List<NoticeDTO> selectNoticeList = mainDAO.selectNoticeList(sqlSession);

		sqlSession.close();

		return selectNoticeList;
	}

	public List<WelfareListDTO> selectWelfareList(int memberNo) {
		SqlSession sqlSession = getSqlSession();

		List<WelfareListDTO> selectWelfareList = mainDAO.selectWelfareList(sqlSession,memberNo);

		sqlSession.close();

		return selectWelfareList;
	}
}