<%@page import="com.qs.www.member.model.dto.MemberInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<link href="https://fonts.googleapis.com/css?family=Fira+Sans:400,500,600,700" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/fullcalendar.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/plugins/morris/morris.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/style.css">
	<title>Wonderful Welfare Workspace</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 	<style>
		.icon{
			display: inline-block;
			width: 36px;
		}
	</style>
</head>

<body>
	<div class="main-wrapper">
		<div class="header">
			<div class="header-left">
				<a href="${ pageContext.servletContext.contextPath }" class="logo">
					<img src="${ pageContext.servletContext.contextPath }/assets/img/www_icon.png" width="40" height="40" alt="">
				</a>
			</div>
			<div class="page-title-box pull-left">
				<h4><span style="font-size:25px">W</span>onderful
					<span style="font-size:25px">W</span>elfare
					<span style="font-size:25px">W</span>orkspace</h4>
			</div>
			<a id="mobile_btn" class="mobile_btn pull-left" href="#sidebar"><i class="fa fa-bars" aria-hidden="true"></i></a>
			<ul class="nav navbar-nav navbar-right user-menu pull-right">
				<li class="dropdown hidden-xs" id="in_time">
					<a href="#">
						<img src="${ pageContext.servletContext.contextPath }/assets/img/sign_in.png" alt=""></img>
					</a>
				</li>
				<li class="dropdown hidden-xs" id="out_time">
					<a href="#">
						<img src="${ pageContext.servletContext.contextPath }/assets/img/sign_out.png" alt=""></img>
					</a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle user-link" data-toggle="dropdown">
						<span class="status online"></span>
						<span class="user-img"><img class="img-circle" src="${ pageContext.servletContext.contextPath }/assets/img/user.jpg" width="40" alt="Admin"></span>
						<span>${ sessionScope.memberInfo.name } 님</span>
						<i class="caret"></i>
					</a>
					<ul class="dropdown-menu">
						<li style=""><a href="${ pageContext.servletContext.contextPath }/mypage/info/select">마이페이지</a></li>
						<li><a href="${ pageContext.servletContext.contextPath }/mypage/contact/select">사내연락망</a></li>
						<li><a href="${ pageContext.servletContext.contextPath }/mypage/logout">로그아웃</a></li>
					</ul>
				</li>
			</ul>
		</div>
		
		<div class="sidebar-overlay" data-reff=""></div>
		<div class="sidebar" id="sidebar">
	        <div class="sidebar-inner slimscroll">
	            <div id="sidebar-menu" class="sidebar-menu">
	                <ul>
	                    <li class="menu-title">WWW Main</li>
	                    <li class="submenu">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-laptop" aria-hidden="true"></i></span>
	                            <span>전자결재</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/approval/insert">결재 신청</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/approval/waiting/select">결재 대기함</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/approval/applied/select">결재 요청함</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/approval/line/select">결재 라인 관리</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/approval/received/select">수신참조함</a></li>
	                        </ul>
	                    </li>
	                    <li class="submenu">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-calendar" aria-hidden="true"></i></span>
	                            <span>일정관리</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/schedule/workingSystem/insert">근무 신청</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/schedule/workingSystem/select">근무 신청 현황</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/schedule/workingHours/select">근무시간 조회</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/schedule/workingHours/team/select">팀 근무/휴가 조회</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/schedule/holiday/insert">휴가 신청</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/schedule/holiday/select">휴가 신청 현황</a></li>
	                        </ul>
	                    </li>
	                    <li class="submenu">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-edit" aria-hidden="true"></i></span>
	                            <span>게시판</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/board/notice/select">공지사항</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/board/form/select">문서서식 게시판</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/board/free/select">자유게시판</a></li>
	                        </ul>
	                    </li>
	                    <li class="submenu">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-ticket" aria-hidden="true"></i></span>
	                            <span>복지신청</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/welfare/list/select">복지 신청</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/welfare/applied/list/select">복지 신청 내역</a></li>
	                        </ul>
	                    </li>
	                    
	                    <li class="menu-title">ADMIN</li>
	                    <li class="submenu" id="submenu_mng_employee">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-address-card" aria-hidden="true"></i></span>
	                            <span>직원관리</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/employee/list/select">전체 직원 조회</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/employee/list/insert">계정 생성</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/employee/role/insert">권한 관리</a></li>
	                        </ul>
	                    </li>
	                    <li class="submenu" id="submenu_mng_working_system">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-table" aria-hidden="true"></i></span>
	                            <span>근태관리</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/workingSystem/select">근무 제도 관리</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/workingSystem/applied/select">근무 신청 목록 조회</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/workingSystem/commute/select">직원 출퇴근 기록</a></li>
	                        </ul>
	                    </li>
	                    <li class="submenu" id="submenu_mng_holiday">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-rocket" aria-hidden="true"></i></span>
	                            <span>휴가관리</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/holiday/applied/select">휴가 신청 목록</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/holiday/category/select">휴가 유형 관리</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/holiday/manual/select">휴가일수 수동 발생</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/holiday/rule/select">휴가일수 발생규칙</a></li>
	                        </ul>
	                    </li>
	                    <li class="submenu" id="submenu_mng_board">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-columns" aria-hidden="true"></i></span>
	                            <span>게시판관리</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/board/notice/select">공지사항 관리</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/board/form/select">서식게시판 관리</a></li>
	                        </ul>
	                    </li>
	                    <li class="submenu" id="submenu_mng_welfare">
	                        <a href="#">
	                            <span class="icon"><i class="fa fa-shopping-cart" aria-hidden="true"></i></span>
	                            <span>복지관리</span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="list-unstyled" style="display: none;">
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/welfare/list/select">시행 복지 관리</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/welfare/applied/select">복지 신청 목록</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/welfare/laptopRental/select">복지 물품 관리</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/mng/welfare/domitory/select">기숙사 입주 관리</a></li>
	                        </ul>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="sidebar-overlay" data-reff=""></div>
	<script>
		$(document).ready(function() {
		});
		
		
		$("#in_time").click(function() {
			$.ajax({
				url: "${ pageContext.servletContext.contextPath }/mypage/commute/insert",
				type: "post",
				success: function(data, textStatus, xhr) {
					if(data.inTime == null) {
						alert("출근 시간이 기록되었습니다.\n"
							+ "현재 시각 : ["
							+ data.yearMonth
							+ "-"
							+ data.day
							+ " "
							+ data.inTime
							+ "]");
					} else {
						alert("이미 입력된 기록이 있습니다.");
					}
				},
				error: function(xhr, status, error) {
					alert(error);
				}
			});
		});
	</script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/select2.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/moment.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/plugins/morris/morris.min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/plugins/raphael/raphael-min.js"></script>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/app.js"></script>
</body>
</html>