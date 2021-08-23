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
                        <h4 class="page-title">사내 연락망</h4>
                    </div>
                </div>
                
                <!-- 검색 시작 -->
                <div class="search-area" align="right">
             		<form id="loginForm" action="${ pageContext.servletContext.contextPath }/mypage/contact/select" method="get" style="display:inline-block">		
			    		<input type="hidden" name="currentPage" value="1">
						<select id="searchCondition" name="searchCondition">
							
							<option value="code" ${ requestScope.selectCriteria.searchCondition eq "code"? "selected": "" }>사원번호</option>
							<option value="name" ${ requestScope.selectCriteria.searchCondition eq "name"? "selected": "" }>부서</option>
							<option value="number" ${ requestScope.selectCriteria.searchCondition eq "number"? "selected": "" }>핸드폰번호</option>
						</select>
						<input type="search" id="searchValue" name="searchValue" value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
					<button type="submit" class="btn btn-success btn-sm" >검색하기</button>
					<!-- <button type="button" id="writeFree">작성하기</button> -->
					</form>
				</div>
			
			
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="card-box">
	                    	<div class="card-block">
	                            <table class="display datatable table table-stripped" text-align: center;>
	                                <thead>
	                                    <tr bgcolor = "FFBC35">
	                                    	<th>프로필 사진</th>
	                                        <th>사원이름</th>
	                                        <th>부서</th>
	                                        <th>휴대폰번호</th>

	                                    </tr>
	                                </thead>
	                                <c:forEach var="board" items="${ requestScope.contactList }">
										<tr>
										
											<td ><img class="img-responsive img-circle" src="${ pageContext.servletContext.contextPath }/assets/img/user.jpg" alt="" style="width: 80px"></td>
                                        	<!-- <td>
                                            <a href="profile.html" class="avatar">A</a>
                                            <h2><a href="profile.html"> <span></span></a></h2>
                                        	</td> -->
											<td><c:out value="${ board.name }"/></td>
											<td><c:out value="${ board.deptname }"/></td>
											<td><c:out value="${ board.phone }"/></td>
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
    </div>
    
    
    
                    
   	<script>

		

		
		/* 제이쿼리 이용하는 경우 */
		/* $(function() {
			$("#listArea td").hover(function() {
				$(this).parent().css({"background":"orangered", "cursor":"pointer"});
			}, function() {
				$(this).parent().css({"background":"black"});
			}).click(function() {
				const no = $(this).parent().children(":eq(0)").text();
				location.href = "${ pageContext.servletContext.contextPath }/notice/detail?no=" + no;
			});
		}); */
	</script>
</body>

</html>