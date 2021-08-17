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
                    <div class="col-xs-8">
                        <h4 class="page-title">결재라인목록</h4>
                    </div>
                    
                    <div class="col-xs-4 text-right m-b-30">
						<a href="${ pageContext.servletContext.contextPath }/approval/line/insert" class="btn btn-primary rounded pull-right" ><i class="fa fa-plus"></i>결재라인 추가</a>
					</div>
                    
                </div>
                
                 <!-- 검색 시작 -->
                <div class="search-area" align="right">
             		<form id="loginForm" action="${ pageContext.servletContext.contextPath }/approval/line/select" method="get" style="display:inline-block">		
			    		<input type="hidden" name="currentPage" value="1">
						<select id="searchCondition" name="searchCondition">
							<option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
							<option value="content" ${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>결재분류</option>
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
                                <table class="display datatable table table-stripped">
                                   
                                   <thead>
                                        <tr bgcolor = "FFBC35">
                                            <th>라인번호</th>
                                            <th>라인제목</th>
                                            <th>결재분류</th>
                                        </tr>

                                    </thead>
                                		<c:forEach var="line" items="${ requestScope.lineList }">
										<tr>
											<td><c:out value="${ line.lineNo }"/></td>
											<td><c:out value="${ line.lineName}"/></td>
											<td><c:out value="${ line.workType }"/></td>
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
					location.href = "${ pageContext.servletContext.contextPath }/approval/line/selectOne?no=" + no;
				} 
				
			}
			
		}
		
		
	</script></body>

</html>