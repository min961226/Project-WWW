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
                        <h4 class="page-title">자유 게시판</h4>
                    </div>
                </div>
                
                <!-- 검색 시작 -->
                <div class="search-area" align="right">
             		<form id="loginForm" action="${ pageContext.servletContext.contextPath }/board/free/select" method="get" style="display:inline-block">		
			    		<input type="hidden" name="currentPage" value="1">
						<select id="searchCondition" name="searchCondition">
							
							<option value="name" ${ requestScope.selectCriteria.searchCondition eq "name"? "selected": "" }>작성자</option>
							<option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
							<option value="body" ${ requestScope.selectCriteria.searchCondition eq "body"? "selected": "" }>내용</option>
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
	                            <table class="display datatable table table-stripped">
	                                <thead>
	                                    <tr bgcolor = "FFBC35">
	                                        <th>번호</th>
	                                        <th>제목</th>
	                                        <th>작성자</th>
	                                        <th>조회수</th>
	                                        <th>수정 날짜</th>
	                                    </tr>
	                                </thead>
	                                <c:forEach var="board" items="${ requestScope.freeList }">
										<tr>
											<td><c:out value="${ board.no }"/></td>
											<td><c:out value="${ board.title }"/></td>
											<td><c:out value="${ board.name }"/></td>
											<td><c:out value="${ board.count }"/></td>
											<td><c:out value="${ board.created }"/></td>
										</tr>
									</c:forEach>	
	                            </table>
	                            
	                            		<%-- 페이지 처리 --%>
								<jsp:include page="../common/paging.jsp"/>
	                           
	                          	 <div class="search-area" align="left">
			             		<form id="loginForm" action="${ pageContext.servletContext.contextPath }/board/free/select" method="get" style="display:inline-block">							
								
								<c:if test="${ sessionScope.memberNo eq member }">
								</c:if>
								<button type="button" class="btn btn-primary rounded pull-right" id="writeFree"><i class="fa fa-plus"></i> 작성하기</button>

								</form>
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
					this.parentNode.style.backgroundColor = "LightGoldenRodYellow";
					this.parentNode.style.cursor = "pointer";
				}
				
				$tds[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}
				
				$tds[i].onclick = function() {
					/* 게시물 번호까지 알아왔으니 이제 상세보기는 할 수 있겠지? */
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/board/free/selectOne?no=" + no;
				}
				
				
			}
			
		}
		
		if(document.getElementsByTagName("writeFree")) {
			const $goBack = document.getElementById("writeFree");
    		$goBack.onclick = function() {
    			location.href = "${ pageContext.servletContext.contextPath }/board/free/insert"
    		}
		}
		
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