<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        
        <div class="page-wrapper" style="min-height: 722px;">
            <div class="content container-fluid">
                <div class="row">
                    <div class="col-xs-12">
                        <h4 class="page-title">근무시간 조회</h4>
                    </div>
                </div>
                <div class="row">
                	<div class="col-lg-12">
                        <div class="card-box">
                            <div class="card-block">
                                <h5 class="text-bold card-title">이번 달 근태 통계</h5>
                                <table class="table table-striped">
                                    <tbody>
                                        <tr>
                                            <td>이번 달 근무일수</td>
                                            <td colspan=2><c:out value="${ requestScope.thisMonthWorkDateNum2 } / ${ requestScope.thisMonthWorkDateNum }"/></td>
                                            <td>이번 달 지각</td>
                                            <td colspan=2><c:out value="${ requestScope.thisMonthlateNum } 회"/></td>
                                        </tr>
                                        <tr>
                                            <td>이번 달 출/퇴근 미체크</td>
                                            <td colspan=2><c:out value="${ requestScope.noCheckInTimeNum } 회  / ${ requestScope.noCheckOutTimeNum } 회"/></td>
                                            <td>오늘 퇴근 체크</td>
                                            <td colspan=2><c:out value="${ requestScope.checkedOutToday }"/></td>
                                        </tr>
                                        <tr>
                                            <td>연 누적 지각</td>
                                            <td colspan=2>받아와야 할까...?</td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                
                                
                <div class="row">
                	<div class="col-lg-12">
                        <div class="card-box">
                            <div class="card-block">
                                <h5 class="text-bold card-title">근무시간 현황</h5>
                                
                                <div><h6><c:out value="${ requestScope.name } ( ${ requestScope.memberId } ) [ ${ requestScope.appWorkType }, ${ requestScope.workCode }번 유형 적용중]"/></h6></div>
                                
                                <!-- 월별시간 -->
                                <div><h6><c:out value="이번 주 정규근무시간 : 시간 [잔여 :  시간]"/></h6></div>
                                <div class="progress">
                                	<div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                
                                <div><h6><c:out value="이번 주 초과근무시간 : ${ requestScope.overtimeSum } 시간 [잔여 : ${ 12 - requestScope.overtimeSum } 시간]"/></h6></div>
                                <div class="progress">
                                	<div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: ${ requestScope.overtimeSum }%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                
                                
                                
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <div class="row">
                	<div class="col-lg-12">
                        <div class="card-box">
                            <div class="card-block">
                            	
                            	<!-- 버튼이 잘 되어야 하는디... -->
                            	<div class="content container-fluid">
                            	<!-- 전월로 이동버튼 추가 -->
                            	<button id="startPage"><</button>
                            	<!-- 연도와 날짜도 받아야 함 -->
                                <h5 class="text-bold card-title">2021년 8월 -- 이것도 수정해야 함</h5>
                                <!-- 다음달로 이동버튼 추가-->
                                <button id="maxPage">></button>
                                </div>
                            	 
                                <c:set var="thisMonthLastDate" value="${ requestScope.thisMonthLastDate }" scope="request"/>
                                
                                <!-- 월별시간 -->
                                <table class="table table-striped">
                                	<thead>
                                		<tr>
                                			<th>이름</th>
                                			<th>소속</th>   
                                			<c:forEach var="days" items="${ requestScope.dayList }">
                                				<td><c:out value="${ days }"/></td>
                                            </c:forEach>                             			
                                		</tr>
                                	</thead>
                                    <tbody>
                                        <tr id="tdDaysofWeek">
                                            <td><c:out value="${ sessionScope.memberInfo.name }"/></td>
                                            <td><c:out value="부서명"></c:out></td>
                                            
                                            <c:forEach var="daysofWeek" items="${ requestScope.dayofWeekList }">
                                            	
                                            	<c:set var="isSaterDay" value="${ fn:contains(daysofWeek, \"토\") }"/>
                                            	<c:set var="isSundayDay" value="${ fn:contains(daysofWeek, \"일\") }"/>
                                            	
                                            		<c:choose>
                                            			<c:when test="${ isSaterDay }">
                                            				<td><div style="color : #1C7EBB"><c:out value="${ daysofWeek }"/></div></td>
                                            			</c:when>
                                            			<c:when test="${ isSundayDay }">
                                            				<td><div style="color : #E94B3B"><c:out value="${ daysofWeek }"/></div></td>
                                            			</c:when>
                                            			<c:otherwise>
                                            				<td><div><c:out value="${ daysofWeek }"/></div></td>
                                            			</c:otherwise>
                                            		</c:choose>
                                            	
                                            </c:forEach>
                                        </tr>
                                        
                                        <tr id="tdInTime">
                                            <td></td>
                                            <td>출근</td>
                                            <c:forEach var="monthlyinCommutingLog" items="${ requestScope.commutingLogMontlyList }" varStatus="status">
                                            	<td><div style="font-size : 8px">
                                            		<c:out value="${ monthlyinCommutingLog.inTime }"/></div></td>
                                            </c:forEach>
                                            
                                            <%-- <c:forEach var="dates" items="${ requestScope.dayList }">
                                            	
                                            	<c:set var="commutingLogMontlyList" value="${ requestScope.commutingLogMontlyList }"/>
                                            	<c:set var="isDaysEqaul" value="${ fn:contains(commutingLogMontlyList.day[index], daysofWeek)}"/>
                                            	
                                            	<td>
                                            	<c:if test="${ isDaysEqaul }">
                                            	<div style="font-size : 8px">
                                            		<c:out value="${ commutingLogMontlyList.inTime }"/></div>
                                            	</c:if>
                                            	</td>
                                            	
                                            </c:forEach> --%>
                                        </tr>
                                        
                                        <tr>
                                            <td></td>
                                            <td>퇴근</td>
                                            <c:forEach var="monthlyOutCommutingLog" items="${ requestScope.commutingLogMontlyList }" varStatus="status">
                                            	<td><div style="font-size : 8px">
                                            		<c:out value="${ monthlyOutCommutingLog.outTime }"/></div></td>
                                            </c:forEach>
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
    
    <script>
    	if(document.getElementsByTagName("tr")) {
    		const $tdDaysofWeek = document.getElementByTagName("tdDaysofWeek");
    		const $tdInTime = document.getElementByTagName("tdInTime");
    		
    		for(let i = 2; i < $tdDaysofWeek.length; i++) {
    			
    		}
    	}
    </script>
</body>

</html>