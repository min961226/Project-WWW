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
                        <h4 class="page-title">공지사항</h4>
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
			<button id="writeFree" style="background-color:orange;">작성하기</button>
			
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
                                            <th >번호</th>
                                            <th>제목</th>
                                            <th>작성일</th>
                                            <th>조회수</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>4</td>
                                            <td >WWW 에어론 체어 서비스 연장 안내</td>
                                            <td>2021.07.26</td>
                                            <td>61</td>

                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>WWW 관리자 정기교육 안내</td>
                                            <td>2021.07.25</td>
                                            <td>63</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>WWW 정기세미나 안내</td>
                                            <td>2021.07.20</td>
                                            <td>66</td>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>WWW 정기세미나 안내</td>
                                            <td>2021.06.27</td>
                                            <td>22</td>
                                        </tr>

                                    </tbody>
                                                                        <thead>
                                        <tr bgcolor = "FFBC35">
                                            <th>번호</th>
                                            <th>제목</th>
                                            <th>조회수</th>
                                            <th>수정 날짜</th>
                                        </tr>

                                    </thead>
                                		<c:forEach var="board" items="${ requestScope.noticeList }">
										<tr>
											<td><c:out value="${ board.no }"/></td>
											<td><c:out value="${ board.title }"/></td>
											<td><c:out value="${ board.count }"/></td>
											<td><c:out value="${ board.created }"/></td>
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
</body>

</html>