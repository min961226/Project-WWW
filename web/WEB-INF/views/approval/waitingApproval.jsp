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
		
		<div class="page-wrapper">
            <div class="content container-fluid">
                <div class="row">
                    <div class="col-xs-12">
                        <h4 class="page-title">결재대기함</h4>
                    </div>
                    </div>
                </div>
                
                <!-- 검색 시작 -->
                <div class="search-area" align="right">
             		<form id="loginForm" action="${ pageContext.servletContext.contextPath }/approval/waiting/select" method="get" style="display:inline-block">		
			    		<input type="hidden" name="currentPage" value="1">
						<select id="searchCondition" name="searchCondition">
						    <option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
						    <option value="docCategory" ${ requestScope.selectCriteria.searchCondition eq "docCategory"? "selected": "" }>문서분류</option>
							<option value="name" ${ requestScope.selectCriteria.searchCondition eq "name"? "selected": "" }>기안자</option>
							<option value="content" ${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>비고</option>
						</select>
						<input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
					<button type="submit" >검색하기</button>
					<!-- <button type="button" id="writeFree">작성하기</button> -->
					</form>
				</div>
				
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card-box">
                            <div class="card-block">
                                <p class="content-group">
                                </p>
                                
                                
                                
                                
                                
                                <!-- 탭바 -->
                                <div>
                                <ul class="nav nav-tabs nav-tabs-top nav-justified">
                                <li class="<c:if test="${ requestScope.selectCriteria.searchValue == null}">active</c:if>"><a onclick="selectDoCate1()" data-toggle="tab">전체</a></li>
                                <li class="<c:if test="${ requestScope.selectCriteria.searchValue == '업무'}">active</c:if>"><a onclick="selectDoCate2()" data-toggle="tab">업무</a></li>
                                <li class="<c:if test="${ requestScope.selectCriteria.searchValue == '복지'}">active</c:if>"><a onclick="selectDoCate3()" data-toggle="tab">복지</a></li>
                                <li class="<c:if test="${ requestScope.selectCriteria.searchValue == '근태'}">active</c:if>"><a onclick="selectDoCate4()" data-toggle="tab">근태</a></li>
                                <li class="<c:if test="${ requestScope.selectCriteria.searchValue == '휴가'}">active</c:if>"><a onclick="selectDoCate5()" data-toggle="tab">휴가</a></li>
                                </ul>
                                <br><br>
                                </div>
                                
                                
                                
                                
                                
                                <table class="display datatable table table-stripped">
                                   
                                   <thead>
                                        <tr bgcolor = "FFBC35">
                                            <th>결재번호</th>
                                            <th>결재분류</th>
                                            <th>결재제목</th>
                                            <th>기안자</th>
                                            <th>결재라인</th>
                                            <th>기안날짜</th>
                                        </tr>

                                    </thead>
                                		<c:forEach var="board" items="${ requestScope.reportList }">
										<tr>
											<td><c:out value="${ board.reportNo }"/></td>
											<td> <c:if test="${board.documentNo eq 1}"> <c:out  value="일반기안문"/></c:if>
											     <c:if test="${board.documentNo eq 2}"> <c:out  value="일반품의서"/></c:if>
											     <c:if test="${board.documentNo eq 3}"> <c:out  value="일반결의서"/></c:if>
											     <c:if test="${board.documentNo eq 4}"> <c:out  value="근무신청서"/></c:if>
											     <c:if test="${board.documentNo eq 5}"> <c:out  value="초과근무신청서"/></c:if>
											     <c:if test="${board.documentNo eq 6}"> <c:out  value="휴가신청서"/></c:if>
											     <c:if test="${board.documentNo eq 7}"> <c:out  value="야간교통비신청서"/></c:if>
											     <c:if test="${board.documentNo eq 8}"> <c:out  value="경조사신청서"/></c:if>
											     <c:if test="${board.documentNo eq 9}"> <c:out  value="자기개발비신청서"/></c:if>
											     <c:if test="${board.documentNo eq 10}"> <c:out  value="기숙사입주신청서"/></c:if>
											     <c:if test="${board.documentNo eq 11}"> <c:out  value="회의실예약신청서"/></c:if>
											     <c:if test="${board.documentNo eq 12}"> <c:out  value="노트북대여신청서"/></c:if>
											     <c:if test="${board.documentNo eq 13}"> <c:out  value="야간교통비지출결의서"/></c:if>
											     <c:if test="${board.documentNo eq 14}"> <c:out  value="경조사지출결의서"/></c:if>
											     <c:if test="${board.documentNo eq 15}"> <c:out  value="자기개발비지출결의서"/></c:if> </td>
											<td><c:out value="${ board.reportTitle}"/></td>
											<td><c:out value="${ board.memberName }"/></td>
											<td><c:out value="${ board.lineName }"/></td>
											<td><c:out value="${ board.reportDate }"/></td>
										</tr>
										</c:forEach>

                                </table>
                               <%-- 페이지 처리 --%>
		<jsp:include page="../common/navbar.jsp"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    
    <script>
    
        if(document.getElementsByTagName("li")) {
        	
        	function selectDoCate1() {
            	location.href = "${ pageContext.servletContext.contextPath }/approval/waiting/select";
            }
        	function selectDoCate2() {
            	location.href = "${ pageContext.servletContext.contextPath }/approval/waiting/select?currentPage=1&searchCondition=docCategory&searchValue=업무";
            }
        	function selectDoCate3() {
            	location.href = "${ pageContext.servletContext.contextPath }/approval/waiting/select?currentPage=1&searchCondition=docCategory&searchValue=복지";
            }
        	function selectDoCate4() {
            	location.href = "${ pageContext.servletContext.contextPath }/approval/waiting/select?currentPage=1&searchCondition=docCategory&searchValue=근태";
            }
        	function selectDoCate5() {
            	location.href = "${ pageContext.servletContext.contextPath }/approval/waiting/select?currentPage=1&searchCondition=docCategory&searchValue=휴가";
            }
        }

		if(document.getElementsByTagName("td")) {
			const $tds = document.getElementsByTagName("td");
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onmouseenter = function() {
					this.parentNode.style.backgroundColor = "orangered";
					this.parentNode.style.cursor = "pointer";
				}
				
				$tds[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}
				
				$tds[i].onclick = function() {
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/approval/waiting/selectOne?no=" + no;
				}
				
			}
			
		}
		
		
	</script>
</body>

</html>