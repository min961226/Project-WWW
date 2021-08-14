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
        			<div class="col-xs-12" align="center">
                        <h4 class="page-title">${ requestScope.workInfo.weekStartDate } ~ ${ requestScope.workInfo.weekEndDate }</h4>
                	</div>
                </div>
                <div class="row">
                	<div class="col-lg-5 col-md-5 col-xs-12">
	                    <div class="panel">
	                       	<div class="panel-heading text-center">
	                           	<h3 class="panel-title">주간 근무 현황</h3>
	                       	</div>
	                       	<div class="panel-body">
	                       		<div class="col-xs-6">
                                <div id="donutChart" class="rad-chart"></div>
                                </div>
                                <div class="col-xs-6">
                                <div id="donutChart" class="rad-chart"></div>
                                </div>
                            </div>
	                    </div>
                    </div>
                    <div class="col-lg-7 col-md-7 col-xs-12">
                    	<div class="panel">
	                       	<div class="panel-heading text-center">
	                           	<h3 class="panel-title">출퇴근 현황</h3>
	                       	</div>
	                       	<div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped custom-table m-b-0" id="inOutTable">
                                        <thead>
                                            <tr>
                                                <th>날짜</th>
                                                <th>출근</th>
                                                <th>퇴근</th>
                                                <th>정규 근무시간</th>
                                                <th>초과 근무시간</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                        	<%-- <c:forEach var="item" items="${ list }" begin=0 end=1>
                                        	</c:forEach> --%>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
	                    </div>
                    </div>
            	</div>
            </div>
        </div>
    </div>
    <div class="sidebar-overlay" data-reff=""></div>
    <script>
    	$(document).ready(function() {
   			$.ajax({
   				url: "/main",
   				type: "get",
   				success: function(data, textStatus, xhr) {
   					console.log(data);
   					let $tbody = $("#inOutTable > tbody");
   					
   					for(let i in data) {
   						let $tr = $("<tr>");
   						
   						for(let key in data[i]) {
   							let $td = $("<td>");
   							$td.text(data[i][key]);
   							
   							$tr.append($td);
   						}
   						
   						$tbody.append($tr);
   					}
   				},
   				error: function(xhr, status, error) {
   					console.log(xhr);
   				}
   			});
   		});
    </script>
</body>

</html>