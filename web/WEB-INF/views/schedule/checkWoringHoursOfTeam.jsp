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
                                
                                <div>
                                <!-- 페이징 부분 -->
								<div class="pagingArea col-xs-6" align="left">
		
									<!-- 이전 페이지 버튼 -->
										<button id="prevPage"><</button>
										
										<button id="today">Today</button>
									<!-- 숫자 버튼 필요없을 것 같은데
									<c:forEach var="p" begin="${ requestScope.selectCriteria.startPage }" end="${ requestScope.selectCriteria.endPage }" step="1">
										<c:if test="${ requestScope.selectCriteria.pageNo eq p }">
											<button disabled><c:out value="${ p }"/></button>
										</c:if>
										<c:if test="${ requestScope.selectCriteria.pageNo ne p }">
											<button onclick="pageButtonAction(this.innerText);"><c:out value="${ p }"/></button>
										</c:if>
									</c:forEach> -->
		
									<!-- 다음 페이지 버튼 -->
										<button id="nextPage">></button>
		
								</div>
								
								<div class="search-area col-xs-6" align="right">
             						<form id="loginForm" action="${ pageContext.servletContext.contextPath }/board/free/select" method="get" style="display:inline-block">		
			    						<input type="hidden" name="date" value="${ requestScope.date }">
											<select id="searchCondition" name="searchCondition">
											<option value="date" ${ requestScope.selectCriteria.searchCondition eq "date"? "selected": "" }>연월일</option>
											</select>
										<input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
										<button type="submit" >검색하기</button>
									</form>
								</div>
								
								
								</div>
								
								<br>
								<br>
                                
                                <table class="table table-bordered">
                                    <thead>
                                        <tr align="center">
                                            <th>이름</th>
                                            <th>시간</th>
                                            <%-- <th colspan="2">12</th>
                                            <c:set var="startHourNum" value="1"/>
                                            <c:forEach var="hours" begin="1" end="23" step="1">
                                            	<th colspan="2"><c:out value="${ hours }"/></th>
                                            </c:forEach> --%>
                                            <th>근무일정</th>
                                            <th>휴가일정</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                      
                                        <c:forEach var="workinghour" items="${ requestScope.teamWorkingHourList }">
                                            <tr>
                                            	<td><c:out value="${ workinghour.name }"/></td>
                                            	<td><c:out value="${ workinghour.standardWorkDTO.checkInTime }"/></td>
                                            	<td>1</td>
                                            <td style="background:blue">2</td>
                                            <td>1</td>
                                            <td>1</td>
                                            <td>2</td>
                                            
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