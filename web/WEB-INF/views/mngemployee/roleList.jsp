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
                		<a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#add_role"><i class="fa fa-plus"></i> Add Roles</a>
                        <div class="roles-menu">
                            <ul class="nav">
                                <!-- <li class="active">
                                    <a href="javascript:void(0);">Administrator
										<span class="role-action">
											<span class="action-circle large" title="Edit" data-toggle="modal" data-target="#edit_role">
												<i class="material-icons">edit</i>
											</span>
											<span class="action-circle large delete-btn" title="Delete" data-toggle="modal" data-target="#delete_role">
												<i class="material-icons">delete</i>
											</span>
										</span>
									</a>
								</li> -->
                            	<c:forEach var="role" items="${ roleList }">
                            		<li><a href="">${ role.roleName }</a></li>
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>
                    
	                <div class="col-lg-9">
	                    <div class="card-box">
	                    	<div class="card-block">
	                            <table class="table table-hover">
	                                <thead>
	                                    <tr bgcolor = "FFBC35">
	                                        <th>관리자 메뉴</th>
	                                        <th>읽기</th>
	                                        <th>생성</th>
	                                        <th>수정</th>
	                                        <th>삭제</th>
	                                    </tr>
	                                </thead>
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