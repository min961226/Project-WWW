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
                    <div class="col-xs-6">
                        <h4 class="page-title">전체 직원 조회</h4>
                    </div>
                </div>
                
                <div class="row">
	                <div class="col-lg-12">
	                    <div class="card-box">
	                    	<div class="card-block">
	                            <table class="table datatable table-hover">
	                                <thead>
	                                    <tr bgcolor = "FFBC35">
	                                        <th>사번</th>
	                                        <th>ID</th>
	                                        <th>이름</th>
	                                        <th>부서</th>
	                                        <th>직급</th>
	                                        <th>E-mail</th>
	                                        <th>사내전화</th>
	                                        <th>입사일</th>
	                                        <th>재직 상태</th>
	                                        <th>권한</th>
	                                    </tr>
	                                </thead>
	                                <c:forEach var="memberInfo" items="${ memberInfoList }">
										<tr>
											<td><c:out value="${ memberInfo.memberNo }"/></td>
											<td><c:out value="${ memberInfo.memberId }"/></td>
											<td><c:out value="${ memberInfo.name }"/></td>
											<td><c:out value="${ memberInfo.department.deptName }"/></td>
											<td><c:out value="${ memberInfo.job.jobName }"/></td>
											<td><c:out value="${ memberInfo.email }"/></td>
											<td><c:out value="${ memberInfo.department.deptCallNumber }"/></td>
											<td><c:out value="${ memberInfo.enrollDate }"/></td>
											<td>
												<c:if test="${ memberInfo.entYn eq 'N'}"><c:out value="재직"/></c:if>
												<c:if test="${ memberInfo.entYn eq 'Y'}"><c:out value="퇴직"/></c:if>
											</td>
											<td>
												<c:if test="${ empty memberInfo.role.roleName }"><c:out value="-"/></c:if>
												<c:if test="${ !empty memberInfo.role.roleName }"><c:out value="${ memberInfo.role.roleName }"/></c:if>
											</td>
										</tr>
									</c:forEach>	
	                            </table>
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
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/mng/employee/list/update?no=" + no;
				}
			}
		}
	</script>
</body>

</html>