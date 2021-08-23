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
        
        <div class="page-wrapper">
            <div class="content container-fluid">
                <div class="row">
                    <div class="col-xs-12">
                        <h4 class="page-title">팀근무 시간조회</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card-box">
                            <div class="card-block">
                                <h6 class="card-title text-bold">같은 팀원들의 오늘 출퇴근기록입니다</h6>
                                
                                                                
                                <table class="table table-bordered">
                                    <thead>
                                        <tr align="center">
                                            <th>이름</th>
                                            <th>부서</th>
                                            <th>직위</th>
                                            <th>근무제명</th>
                                            <th>근무제코드</th>
                                            <th>휴가시작일</th>
                                            <th>휴가종료일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      	<c:forEach var="member" items="${ requestScope.teamMemberInfoList }">
	                                        <tr>
		                                        <c:forEach var="workinghour" items="${ requestScope.teamWorkingHourList }">
		                                        	<c:if test="${ member.name eq workinghour.name }">
		                                        		<td><c:out value="${ workinghour.name }"/></td>
		                                            	<td><c:out value="${ workinghour.deptName }"/></td>
		                                            	<td><c:out value="${ workinghour.jobName }"/></td>
		                                            	<td><c:out value="${ workinghour.appWorkType }"/></td>
		                                            	<td><c:out value="${ workinghour.workCode }"/></td>
		                                        	</c:if>
		                                         </c:forEach>
		                                         
		                                         <c:forEach var="holiday" items="${ requestScope.teamHolidayLogList }">
		                                         	<c:if test="${ member.memberNo eq holiday.memberNo }">
		                                         	<c:choose>
		                                         		<c:when test="${ holiday eq null }">
		                                         			<td></td>
		                                         			<td></td>
		                                         		</c:when>
		                                         		<c:otherwise>
		                                         			<td><c:out value="${ holiday.holidayUseInfoDTO.holidayStartDay }"></c:out></td>
			                                            	<td><c:out value="${ holiday.holidayUseInfoDTO.holidayEndDay }"></c:out></td>
		                                         		</c:otherwise>
		                                         	</c:choose>
		                                         	</c:if>
		                                         </c:forEach>
	                                         </tr>
	                                         
                                      	</c:forEach>
                                        
                                        
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
		const currentPage = window.location.pathname;

	    let link = "";
	    let searchText = "";
	    
	    switch (currentPage) {

			case "${ pageContext.servletContext.contextPath }/schedule/workingHours/team/select":
				 link = "/WWW/schedule/workingHours/team/select";
				break;
				
	    }
		
		if(document.getElementById("prevPage")) {
			const $prevPage = document.getElementById("prevPage");
			$prevPage.onclick = function() {
				location.href = link + "?currentPage=${ requestScope.selectCriteria.pageNo - 1 }" + searchText;
			}
		}
		
		if(document.getElementById("nextPage")) {
			const $nextPage = document.getElementById("nextPage");
			$nextPage.onclick = function() {
				location.href = link + "?currentPage=${ requestScope.selectCriteria.pageNo + 1 }" + searchText;
			}
		}
		
		if(document.getElementById("today")) {
			const $nextPage = document.getElementById("today");
			$nextPage.onclick = function() {
				location.href = link + "?currentPage=${ requestScope.selectCriteria.pageNo + 1 }" + searchText;
			}
		}
		
		
		
		function pageButtonAction(text) {
			location.href = link + "?currentPage=" + text + searchText;
		}
	</script>
        
</body>

</html>