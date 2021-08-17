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
                        <h4 class="page-title">결재요청함</h4>
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
                                            <th>결재라인</th>
                                            <th>결재 날짜</th>
                                            <th>진행상태</th>
                                        </tr>

                                    </thead>
                                		<c:forEach var="board" items="${ requestScope.reportList }">
										<tr>
											<td><c:out value="${ board.reportNo }"/></td>
											<td> <c:if test="${board.documentNo eq 1}"> <c:out  value="일반기안문"/></c:if>
											     <c:if test="${board.documentNo eq 2}"> <c:out  value="일반품의서"/></c:if>
											     <c:if test="${board.documentNo eq 3}"> <c:out  value="일반결의서"/></c:if> </td>
											<td><c:out value="${ board.reportTitle}"/></td>
											<td><c:out value="${ board.lineName }"/></td>
											<td><c:out value="${ board.reportDate }"/></td>
											<td><c:out value="${ board.reportStatus }"/></td>
										</tr>
										</c:forEach>

                                </table>
                                    <%-- 페이지 처리 --%>
								    <jsp:include page="../common/paging.jsp"/>
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
					location.href = "${ pageContext.servletContext.contextPath }/approval/applied/selectOne?no=" + no;
				}
				
			}
			
		}
		
		
	</script>
</body>

</html>