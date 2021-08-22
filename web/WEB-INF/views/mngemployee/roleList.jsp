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
                        <h4 class="page-title">권한 관리</h4>
                    </div>
                </div>
                
                <div class="row">
                	<div class="col-lg-3">
                		<div class="card-box" style="background-color:#FEEB99;">
                			<h5 class="role-title m-b-20"><b>관리자 역할</b></h5>
	                		<div class="roles-menu">
	                            <ul class="nav">
	                            	<c:forEach var="role" items="${ roleList }">
	                            		<li value="${ role.roleCode }"><a>${ role.roleName }</a></li>
	                            	</c:forEach>
	                            </ul>
	                        </div>
	                    </div>
                    </div>
                    
	                <div class="col-lg-9">
	                    <div class="card-box">
	                    	<div class="card-block">
	                            <table class="table table-hover">
	                                <thead>
	                                    <tr bgcolor="FFBC35">
	                                        <th class="text-center">관리자 메뉴</th>
	                                        <th class="text-center">읽기</th>
	                                        <th class="text-center">수정</th>
	                                        <th class="text-center">생성</th>
	                                        <th class="text-center">삭제</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="menu" items="${ menuList }">
		                                	<tr>
		                                		<td><b>${ menu.menuCategoryName }</b></td>
		                                		<c:forEach var="authority" items="${ authorityList }">
		                                			<c:if test="${ authority.menuCategoryUri eq menu.menuCategoryUri }">
				                                		<td>
				                                			<c:if test="${ authority.menuName ne null }">
			                                					<input type="checkbox" style="margin:3px">
			                                					<c:out value="${ authority.menuName }"/>
			                                				</c:if>
				                                		</td>
				                                	</c:if>
		                                		</c:forEach>
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
    	$("li").click(function() {
    		/* $(this).attr(selected);
    		console.log($(this)); */
			/* $.ajax({
				url: "${ pageContext.servletContext.contextPath }/mng/employee/role/select",
				type: "post",
				data: "$(this).value()",
				success: function(data, textStatus, xhr) {
				},
				error: function(xhr, status, error) {
					alert(error);
				}
			}); */
		});
    
    </script>
</body>

</html>