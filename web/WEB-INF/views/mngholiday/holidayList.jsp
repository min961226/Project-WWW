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
                        <h4 class="page-title">휴가신청목록</h4>
                    </div>
                    </div>
                </div>
                
                <!-- 검색 시작 -->
                <div class="search-area" align="right">
             		<form id="loginForm" action="${ pageContext.servletContext.contextPath }/mng/holiday/applied/select" method="get" style="display:inline-block">		
			    		<input type="hidden" name="currentPage" value="1">
						<select id="searchCondition" name="searchCondition">
						    <option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
							<option value="name" ${ requestScope.selectCriteria.searchCondition eq "name"? "selected": "" }>기안자</option>
							<option value="content" ${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>비고</option>
						</select>
						<input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
					<button class="btn-success" type="submit" >검색하기</button>
					<!-- <button type="button" id="writeFree">작성하기</button> -->
					</form>
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
                                            <th>결재제목</th>
                                            <th>기안자</th>
                                            <th>결재라인</th>
                                            <th>기안날짜</th>
                                            <th>결재상태</th>
                                        </tr>

                                    </thead>
                                		<c:forEach var="board" items="${ requestScope.reportList }">
										<tr>
											<td><c:out value="${ board.reportNo }"/></td>
											<td><c:out value="${ board.reportTitle}"/></td>
											<td><c:out value="${ board.memberName }"/></td>
											<td><c:out value="${ board.lineName }"/></td>
											<td><c:out value="${ board.reportDate }"/></td>
											 <td><i class=
                                                <c:if test="${board.reportStatus eq '대기'}">"fa fa-dot-circle-o text-success"</c:if>
                                                <c:if test="${board.reportStatus eq '승인'}">"fa fa-dot-circle-o text-info"</c:if>
                                                <c:if test="${board.reportStatus eq '반려'}">"fa fa-dot-circle-o text-danger"</c:if>
                                                <c:if test="${board.reportStatus eq '회수'}">"fa fa-dot-circle-o text-warning"</c:if>
                                                <c:if test="${board.reportStatus eq '취소'}">"fa fa-dot-circle-o text-purple"</c:if>
												></i> ${ board.reportStatus }</td>
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

		if(document.getElementsByTagName("td")) {
			const $tds = document.getElementsByTagName("td");
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onmouseenter = function() {
					this.parentNode.style.backgroundColor = "LightGoldenRodYellow";
					this.parentNode.style.cursor = "pointer";
				}
				
				$tds[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}
				
				$tds[i].onclick = function() {
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/mng/holiday/applied/selectOne?no=" + no;
				}
				
			}
			
		}
		
		
	</script>
</body>

</html>