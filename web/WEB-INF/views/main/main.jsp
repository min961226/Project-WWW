<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
<title>Wonderful Welfare Workspace</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
    <div class="main-wrapper">
		<jsp:include page="../common/navbar.jsp"/>
        
        <div class="page-wrapper">
        	<div class="content container-fluid">
        		<div class="row">
        			<div class="col-xs-12" align="center">
                        <h4 class="page-title">${ requestScope.workInfo.weekStartDate } ~ ${ requestScope.workInfo.weekEndDate }</h4>
                	</div>
                </div>
                <div class="row">
                	<div class="col-lg-6 col-md-6 col-xs-12">
	                    <div class="card-box">
	                       	<div class="panel-heading text-center">
	                           	<h3 class="panel-title">Weekly Report</h3>
	                       	</div>
	                       	<div class="panel-body">
	                       		<div>
		                       		<div class="col-xs-6" style="height: 80px;">
		                       			<h1>${ workTimeSum } hr</h1>
		                       		</div>
	                                <div class="col-xs-6" style="height: 80px;">
	                                	<h1>${ overtimeSum } hr</h1>
	                                </div>
	                       		</div>
	                       		<div>
	                                <div class="col-xs-6">정규 근무시간
	                                	<hr style="background-color: #888888; height: 1px; border:0px;">
	                                </div>
	                                <div class="col-xs-6">초과 근무시간
	                                	<hr style="background-color: #888888; height: 1px; border:0px;">
	                                </div>
	                       		</div>
                            </div>
	                    </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-xs-12">
                    	<div class="card-box">
                        	<h3 style="height: 40px;font: bold;margin-top: 10px" align="center"><b><출퇴근 현황></b></h3>
							<h6 align="right">근무 제도 : ${ sessionScope.memberInfo.appWorkType }</h6>	                           	
                            <div class="table-resposive">
                                <table class="display datatable table table-stripped">
                                    <thead>
                                      <tr bgcolor="FFBC35">
                                            <th>날짜</th>
                                            <th>출근</th>
                                            <th>퇴근</th>
                                            <th>정규 근무시간</th>
                                            <th>초과 근무시간</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="workingLog" items="${ workingLogList }" varStatus="status">
                                    		<tr>
                                    			<td>${ workingLog.selectedDate } (${ workingLog.selectedDayOfWeek })</td>
                                    			<td>${ commutingLogList[status.index].inTime }</td>
                                    			<td>${ commutingLogList[status.index].outTime }</td>
                                    			<td>${ workingLog.dailyWorkTime } hr</td>
                                    			<td>
                                    				<c:forEach var="overTimeLog" items="${ overTimeLogList }">
                                      			<c:if test="${ workingLog.selectedSqlDate eq overTimeLog.overtimeStartDay}">
                                      				${ overTimeLog.overtimeDuring } hr
                                      			</c:if>
                                     			</c:forEach>
                                    			</td>
                                    		</tr>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
	                    </div>
                    </div>
            	</div>
            	<div class="row">
                	<div class="col-lg-6 col-md-6 col-xs-12">
                		<hr style="background-color: #888888; height: 2px; border:0px;">
	                    <div class="card-box">
	                       	<!-- <div class="panel-heading text-center">
	                           	<h3 class="panel-title" style="color:red">Weekly Report</h3>
	                       	</div>
	                       	<div class="panel-body">
	                       		<div class="col-xs-6">
                                	<div id="donutChart" class="rad-chart"></div>
                                </div>
                                <div class="col-xs-6">
                                	<div id="donutChart" class="rad-chart"></div>
                                </div>
                            </div> -->
                            <div class="panel-heading text-center">
	                           	<h2  style="height: 40px;font: bold;"><b><최근 공지사항></b></h2>
	                       	</div>
                            <table class="display datatable table table-stripped">
								<thead>
									<tr bgcolor="FFBC35">
										<th>번호</th>
										<th>공지사항명</th>
										<th>조회수</th>
										<th>작성일</th>
									</tr>
								</thead>
								<c:forEach var="noticeList"
									items="${ requestScope.noticeList }">
									<tr style="height: 40px">
										<td><c:out value="${ noticeList.rowNum }" /></td>
										<td><c:out value="${ noticeList.title }" /></td>
										<td><c:out value="${ noticeList.count }" /></td>
										<td><c:out value="${ noticeList.createdDate }" /></td>
									</tr>
								</c:forEach>
							</table>
	                    </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-xs-12">
                		<hr style="background-color: #888888; height: 2px; border:0px;">
                    	<div class="panel">
	                         <div class="panel-heading text-center">
	                           	<h2  style="height: 40px;font: bold;"><b><신청 복지목록></b></h2>
	                       	</div>
	                       	<div class="panel-body">
                                <div class="table-resposive">
                                    <table class="table table-striped custom-table m-b-0 datatable">

										<thead>
											<tr style="background-color: #ffbc34">
												<!-- 시작일과 종료일도 뜨게 해주고 싶네 -->
												<th>번호</th>
												<th>신청 복지 제목</th>
												<th>진행상태</th>
												<th>상신일</th>
											</tr>
										</thead>


										<c:forEach var="welfareList"
											items="${ requestScope.welfareList }">

											<tr>
												<td><c:out value="${ welfareList.rowNum }" /></td>
												<td ><c:out value="${ welfareList.welfareTitle }" /></td>

												<td><c:choose>
														<c:when test="${ welfareList.reportNote eq '승인' }">
															<i class="fa fa-dot-circle-o text-success"></i>
															<c:out value=" ${ welfareList.reportNote }" />
														</c:when>
														<c:otherwise>
															<i class="fa fa-dot-circle-o text-danger"></i>
															<c:out value=" ${ welfareList.reportNote }" />
														</c:otherwise>
												</c:choose></td>
												<td><c:out value="${ welfareList.selfDevDate }" /></td>
											</tr>
										</c:forEach>

									</table>
                                </div>
                            </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="sidebar-overlay" data-reff=""></div>
</body>

</html>