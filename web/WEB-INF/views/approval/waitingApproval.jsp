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
                    <div class="col-xs-12">
                        <h4 class="page-title">결재대기함</h4>
                    </div>
                </div>
                <div class="search-area" align="right">
			<select id="searchCondition" name="searchCondition">
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search">
			<button type="submit" style="background-color:orange;">검색하기</button>
			
			
		</div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card-box">
                            <div class="card-block">
                                <p class="content-group">
                                </p>
                                <table class="display datatable table table-stripped">
                                   
                                   <thead>
                                        <tr bgcolor = "FFBC35">
                                            <th>결재번호</th>
                                            <th>결재분류</th>
                                            <th>결재제목</th>
                                            <th>기안자</th>
                                            <th>결재라인</th>
                                            <th>결재 날짜</th>
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
                                <div class="pagingArea" align="center">
		<!-- 맨 앞으로 이동 버튼 -->
	    <button id="startPage"><<</button>
		
		<!-- 이전 페이지 버튼 -->
		<c:if test="${ requestScope.selectCriteria.pageNo <= 1 }">
			<button disabled><</button>
		</c:if>
		<c:if test="${ requestScope.selectCriteria.pageNo > 1 }">
			<button id="prevPage"><</button>
		</c:if>
		
		<!-- 숫자 버튼 -->
		<c:forEach var="p" begin="${ requestScope.selectCriteria.startPage }" end="${ requestScope.selectCriteria.endPage }" step="1">
			<c:if test="${ requestScope.selectCriteria.pageNo eq p }">
				<button disabled><c:out value="${ p }"/></button>
			</c:if>
			<c:if test="${ requestScope.selectCriteria.pageNo ne p }">
				<button onclick="pageButtonAction(this.innerText);"><c:out value="${ p }"/></button>
			</c:if>
		</c:forEach>
		
		<!-- 다음 페이지 버튼 -->
		<c:if test="${ requestScope.selectCriteria.pageNo >= requestScope.selectCriteria.maxPage }">
			<button disabled>></button>
		</c:if>
		<c:if test="${ requestScope.selectCriteria.pageNo < requestScope.selectCriteria.maxPage }">
			<button id="nextPage">></button>
		</c:if>
		
		<!-- 마지막 페이지로 이동 버튼 -->
		<button id="maxPage">>></button> 
	</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
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