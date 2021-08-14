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
                	<div class="col-lg-5 col-md-5 col-xs-12">
	                    <div class="panel">
	                       	<div class="panel-heading text-center">
	                           	<h3 class="panel-title">Weekly Report</h3>
	                       	</div>
	                       	<div class="panel-body">
	                       		<div class="col-xs-6">
                                	<div id="donutChart" class="rad-chart"></div>
                                </div>
                                <div class="col-xs-6">
                                	<div id="donutChart" class="rad-chart"></div>
                                </div>
                            </div>
	                    </div>
                    </div>
                    <div class="col-lg-7 col-md-7 col-xs-12">
                    	<div class="panel">
	                       	<div class="panel-heading text-center">
	                           	<h3 class="panel-title">출퇴근 현황</h3>
	                       	</div>
	                       	<div class="panel-body">
                                <div class="table-resposive">
                                	<h5>근무 제도 : <c:out value="${ workingLog.workType }"/></h5>
                                    <table class="table table-hover custom-table m-b-0">
                                        <thead>
                                            <tr>
                                                <th>날짜</th>
                                                <th>출근</th>
                                                <th>퇴근</th>
                                                <th>정규 근무시간</th>
                                                <th>초과 근무시간</th>
                                            </tr>
                                        </thead>

                                        <tbody>

                                        	<c:forEach var="commutingLog" items="${ commutingLogList }" varStatus="status">
                                        		<tr>
                                        			<td>${ commutingLog.yearMonth }${ commutingLog.day }</td>
                                        			<td>${ commutingLog.inTime }</td>
                                        			<td>${ commutingLog.outTime }</td>
                                        			<td>${ workingLogList[status.index].workingType.checkInTime }</td>
                                        			<td>-</td>
                                        		</tr>
                                        	</c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
	                    </div>
                    </div>
            	</div>
            	<div class="row">
                	<div class="col-lg-5 col-md-5 col-xs-12">
                		<hr style="background-color: #888888; height: 2px; border:0px;">
	                    <div class="panel">
	                       	<div class="panel-heading text-center">
	                           	<h3 class="panel-title">Monthly Report</h3>
	                       	</div>
	                       	<div class="panel-body">
	                       		<div class="col-xs-6" style="height: 80px"><h1 >58h 40m</h1></div>
                                <div class="col-xs-6" style="height: 80px"><h1>0h</h1></div>
                                <div class="col-xs-6">정규 근무시간
                                	<hr style="background-color: #888888; height: 1px; border:0px;">
                                </div>
                                <div class="col-xs-6">초과 근무시간
                                	<hr style="background-color: #888888; height: 1px; border:0px;">
                                </div>
                            </div>
	                    </div>
                    </div>
                    <div class="col-lg-7 col-md-7 col-xs-12">
                		<hr style="background-color: #888888; height: 2px; border:0px;">
                    	<div class="panel">
	                       	<div class="panel-heading text-center">
	                           	<h3 class="panel-title">휴가 현황</h3>
	                       	</div>
	                       	<div class="panel-body">
                                <div class="table-resposive">
                                    <table class="table table-hover custom-table m-b-0">
                                        <tbody>
                                        	<tr>
                                        		<td class="col-md-2">생성 연차</td>
                                        		<td class="col-md-4">15일 (정기: 15일, 포상: 0일)</td>
                                        		<td class="col-md-2">기간</td>
                                        		<td class="col-md-4">[2021.01.01] ~ [2021.12.31]</td>
                                        	</tr>
                                        	<tr>
                                        		<td>사용 연차</td>
                                        		<td>5일</td>
                                        		<td>잔여 연차</td>
                                        		<td>10일</td>
                                        	</tr>
                                        </tbody>
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