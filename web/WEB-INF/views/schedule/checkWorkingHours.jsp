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
                	<div class="col-lg-9">
                        <div class="card-box">
                            <div class="card-block">
                                <h5 class="text-bold card-title">이번 달 근태 통계</h5>
                                <table class="table table-striped">
                                    <tbody>
                                        <tr>
                                            <td>이번 달 근무일수</td>
                                            <td><c:out value="${ requestScope.thisMonthWorkDateNum2 } / ${ requestScope.thisMonthWorkDateNum }"/></td>
                                            <td>이번 달 지각</td>
                                            <td><c:out value="${ requestScope.thisMonthlateNum } 회"/></td>
                                        </tr>
                                        <tr>
                                            <td>이번 달 출/퇴근 미체크</td>
                                            <td><c:out value="${ requestScope.noCheckInTimeNum } 회  / ${ requestScope.noCheckOutTimeNum } 회"/></td>
                                            <td>오늘 퇴근 체크</td>
                                            <c:if test="${ requestScope.checkedOutToday eq 0}"><td><c:out value="오늘 퇴근 미체크"/></td></c:if>
                                            <c:if test="${ requestScope.checkedOutToday eq 1}"><td><c:out value="오늘 퇴근 체크 완료"/></td></c:if>
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
                                
                                <div><h4><c:out value="${ requestScope.name } ( ${ requestScope.memberId } ) [ ${ requestScope.appWorkType }, ${ requestScope.workCode }번 유형 적용중]"/></h4></div>
                                <br>
                                
                                <!-- 월별시간 -->
                                <div><h5><c:out value="이번 주 정규근무시간 : 시간 [잔여 :  시간]"/></h5></div>
                                <div class="progress">
                                	<div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: 70%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                
                                <div><h5><c:out value="이번 주 초과근무시간 : ${ requestScope.overtimeSum } 시간 [잔여 : ${ 12 - requestScope.overtimeSum } 시간]"/></h5></div>
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
                            	<div class="content container-fluid" style="display : inline-block">
                            		<!-- 전월로 이동버튼 추가 -->
                            		<button id="startPage"><</button>
                            		<!-- 연도와 날짜도 받아야 함 -->
                                	<h5 class="text-bold card-title"><c:out value="${ requestScope.thisYear }년  ${ requestScope.thisMonth }월 "/></h5>
                                	<!-- 다음달로 이동버튼 추가-->
                                	<button id="maxPage">></button>
                                </div>
                                
                                <br>
                                <br>
                                
                                <div>
                                	<c:out value="이름 : ${ sessionScope.memberInfo.name } / "/>
                                    <c:out value="부서명 : ${ sessionScope.memberInfo.department.deptName }"/>
                                </div>
	                    		
	                    		<!-- 월별시간 -->
	                    		<div>
		                    		<table class="table table-striped">
		                    			<thead>
		                    				<tr>
	    	                					<c:forEach var="days" items="${ requestScope.dailyCommuteList }">
                    	            				<th><c:out value="${ days.dateNum }"/></th>
                        	                	</c:forEach> 
                        	                </tr>
	                    				</thead>
	                    				
	                    				<tbody>
	                    					<tr style="font-size:8px">
	                    						<c:forEach var="days" items="${ requestScope.dailyCommuteList }">
                                            		<c:set var="isSaterDay" value="${ fn:contains(days.dayOfWeek, \"토\") }"/>
                                            		<c:set var="isSundayDay" value="${ fn:contains(days.dayOfWeek, \"일\") }"/>
                                            		<c:choose>
                                            			<c:when test="${ isSaterDay }">
                                            				<td><div style="color : #1C7EBB"><c:out value="${ days.dayOfWeek }"/></div></td>
                                            			</c:when>
                                            			<c:when test="${ isSundayDay }">
                                            				<td><div style="color : #E94B3B"><c:out value="${ days.dayOfWeek }"/></div></td>
                                            			</c:when>
                                            			<c:otherwise>
                                            				<td><div><c:out value="${ days.dayOfWeek }"/></div></td>
                                            			</c:otherwise>
                                            		</c:choose>
                                            	</c:forEach>
	                    					</tr>
	                    					
	                    					<tr style="font-size:5px">
	                    						<c:forEach var="days" items="${ requestScope.dailyCommuteList }">
	                    							<c:set var="isSaterDay" value="${ fn:contains(days.dayOfWeek, \"토\") }"/>
                                            		<c:set var="isSundayDay" value="${ fn:contains(days.dayOfWeek, \"일\") }"/>
	                    							<c:set var="isLate" value="${ fn:contains(days.lateYn, \"Y\")}"/>
                                        			<c:choose>
                                        				<c:when test="${ isSaterDay || isSundayDay }">
                                        					<td><div style="color : #E94B3B">휴</div></td>
                                        				</c:when>
                                        				<c:when test="${ empty days.checkInTime && isLate}">
                                        					<th><i class="fa fa-dot-circle-o text-danger"></i><br><c:out value="${ days.checkInTime }"/></th>
                                        				</c:when>
                                        				<c:otherwise>
                                        					<th><c:out value="${ days.checkInTime }"/></th>
                                        				</c:otherwise>
                                        			</c:choose>
                                        		</c:forEach>
	                    					</tr>
	                    					
	                    					<tr style="font-size:5px">
	                    						<c:forEach var="days" items="${ requestScope.dailyCommuteList }">
	                    							<c:set var="isSaterDay" value="${ fn:contains(days.dayOfWeek, \"토\") }"/>
                                            		<c:set var="isSundayDay" value="${ fn:contains(days.dayOfWeek, \"일\") }"/>
	                    							<c:set var="isLeaveEarly" value="${ fn:contains(days.leaveEarlyYn, \"Y\")}"/>
                                        			<c:choose>
                                        				<c:when test="${ isSaterDay || isSundayDay }">
                                        					<td><div style="color : #E94B3B">휴</div></td>
                                        				</c:when>
                                        				<c:when test="${ empty days.checkOutTime && isLeaveEarly}">
                                        					<th><i class="fa fa-dot-circle-o text-danger"></i><br><c:out value="${ days.checkOutTime }"/></th>
                                        				</c:when>
                                        				<c:otherwise>
                                        					<th><c:out value="${ days.checkOutTime }"/></th>
                                        				</c:otherwise>
                                        			</c:choose>
                                        		</c:forEach>
	                    					</tr>
	                    					
	                    				</tbody>
	                    		
	                    			</table>
                                </div>
	                    		
	                    		
	                    		
                                <%-- 
                                	
                               		<c:forEach var="dailyCommute" items="${ requestScope.dailyCommuteList }">
                               			<thead>
                               			<tr style="display : inline-block">
                               				<c:forEach var="days" items="${ dailyCommute.dateNum }">
                               					<th><c:out value="${ days }"/></th>
                               				</c:forEach>
                               			</tr>
                               			</thead>
                               			
                               			<tbody>
                               			<tr>
                               				<!-- 토요일이라면 파란색, 일요일이라면 빨간색, 평일이면 기본색으로 출력 -->
                                            <c:set var="isSaterDay" value="${ fn:contains(dailyCommute.dayOfWeek, \"토\") }"/>
                                            <c:set var="isSundayDay" value="${ fn:contains(dailyCommute.dayOfWeek, \"일\") }"/>
                                            <c:forEach var="dayOfWeek" items="${ dailyCommute.dayOfWeek }">
                                            	<c:choose>
                                            		<c:when test="${ isSaterDay }">
                                            			<td><div style="color : #1C7EBB"><c:out value="${ dayOfWeek }"/></div></td>
                                            		</c:when>
                                            		<c:when test="${ isSundayDay }">
                                            			<td><div style="color : #E94B3B"><c:out value="${ dayOfWeek }"/></div></td>
                                            		</c:when>
                                            		<c:otherwise>
                                            			<td><div><c:out value="${ dayOfWeek }"/></div></td>
                                            		</c:otherwise>
                                            	</c:choose>
                                            </c:forEach>
                                        </tr>
                                        
                                        <tr>
                                        	<!-- 지각했다면 빨간원표시를 달아줌 -->
                                        	<c:set var="isLate" value="${ fn:contains(dailyCommute.lateYn, \"Y\")}"/>
                                        	<c:forEach var="checkInTime" items="${ dailyCommute.checkInTime }">
                                        		<c:choose>
                                        			<c:when test="${ checkInTime == null }">
                                        				<td>1</td>
                                        			</c:when>
                                        			<c:when test="${ empty checkInTime && isLate}">
                                        				<th><i class="fa fa-dot-circle-o text-danger"></i><br><c:out value="${ checkInTime }"/></th>
                                        			</c:when>
                                        			<c:otherwise>
                                        				<th><c:out value="${ checkInTime }"/></th>
                                        			</c:otherwise>
                                        		</c:choose>
                                        	</c:forEach>
                                        </tr>
                                        
                                        <tr>
                                        	<!-- 조퇴했다면 빨간원표시를 달아줌 -->
                                        	<c:set var="isLeaveEarly" value="${ fn:contains(dailyCommute.leaveEarlyYn, \"Y\")}"/>
                                        	<c:forEach var="checkOutTime" items="${ dailyCommute.checkOutTime }">
                                        		<c:choose>
                                        			<c:when test="${ checkOutTime eq null }">
                                        				<td>2</td>
                                        			</c:when>
                                        			<c:when test="${ checkOutTime ne null }">
                                        				<td><i class="fa fa-dot-circle-o text-danger"></i><br><c:out value="${ checkOutTime }"/></td>
                                        			</c:when>
                                        			<c:otherwise>
                                        				<th><c:out value="${ checkOutTime }"/></th>
                                        			</c:otherwise>
                                        		</c:choose>
                                        	</c:forEach>
                                        </tr>
                                        
                                        </tbody>
                                      </c:forEach> --%>
                               			
                                	 
                                	 
                                	 
                                    
                                    
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
    </div>
    
</body>

</html>