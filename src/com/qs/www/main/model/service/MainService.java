package com.qs.www.main.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dao.MainDAO;
import com.qs.www.main.model.dto.CommutingLogDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;
import com.qs.www.main.model.dto.WorkingTypeDTO;

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
	
	// 근무 유형 및 시간 조회
	public List<WorkingLogDTO> selectWorkingLog(WorkInfoDTO workInfo, Calendar selectedCalDate, SimpleDateFormat sdf) {
		
		SqlSession sqlSession = getSqlSession();

		List<WorkingLogDTO> workingLogList = new ArrayList<>();
		
		// 날짜별 근무 유형 및 시간 조회(월요일 ~ 일요일)
		for(int i = 0; i < 7; i++) {
			String selectedDate = sdf.format(selectedCalDate.getTime());
			workInfo.setSelectedDate(selectedDate);
			
			// 근무 이력 조회
			WorkingLogDTO workingLog = new WorkingLogDTO();
			workingLog = mainDAO.selectWorkingLog(sqlSession, workInfo);
			workingLog.setSelectedDate(selectedDate);
			
			// 근무 유형 조회
			WorkingTypeDTO workingType = new WorkingTypeDTO();
			workingType = mainDAO.selectWorkingType(sqlSession, workingLog);
			
			if(workingLog.getWorkType().equals("표준")) {
				LocalDate date = LocalDate.parse(selectedDate);
				DayOfWeek dayOfWeek = date.getDayOfWeek();
				String[] week = {"월", "화", "수", "목", "금", "토", "일"};
				int w = dayOfWeek.getValue() - 1;
				workingType.setDayOfWeek(week[w]);
			}
			
			workingLog.setWorkingType(workingType);
			workingLogList.add(workingLog);
			for(WorkingLogDTO workingLogA : workingLogList) {
				System.out.println("A : " + workingLogA);
			}
			// 날짜 추가(요일 변경)
			selectedCalDate.add(Calendar.DATE, 1);
		}
		
		mainDAO.selectWorkingLog(sqlSession, workInfo);

		sqlSession.close();
		
		return workingLogList;
	}
}
