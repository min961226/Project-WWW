<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon"
	href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
<title>Wonderful Welfare Workspace</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>

<body>
	<div class="main-wrapper">
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<h4 class="page-title">세미나실 대여 신청서</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form name="insertSelfDev"
							action="${ pageContext.servletContext.contextPath }/welfare/seminarRoom/insert"	method="POST" onsubmit="return askAgain();">
							<div class="form-group">
								<label>직원 ID</label> <input name="memberNo" class="form-control"
									type="text" value="${memberNo}" readonly="readonly" />
							</div>
							<div class="form-group">
								<label>부서</label> <input name="deptName" class="form-control"
									type="text"
									value="${ sessionScope.memberInfo.department.deptName }">
							</div>
							<div class="form-group">
								<label>직위</label> <input name="jobName" class="form-control"
									type="text" value="${ sessionScope.memberInfo.job.jobName }">
							</div>
							<div class="form-group">
								<label>세미나실 대여 신청자</label> <input name="name" class="form-control"
									type="text" value="${ sessionScope.memberInfo.name }"
									readonly="readonly">
							</div>
							<br>
							<div class="card-box" style="background: lightyellow; border: 1px solid black;" >
								<div class="card-block" >
									<table class="display datatable table table-stripped">
										<thead>
											<tr>
												<td rowspan="1">
													<h3>
														<b>세미나실 대여 현황</b>
													</h3>
												</td>
											</tr>
											<tr bgcolor="FFBC35">

												<th>세미나실 번호</th>
												<th>대여 일자</th>
												<th>대여 시간</th>
												<th>대여 상태</th>
											</tr>
										</thead>
										<c:forEach var="seminarRoomReserv"
											items="${ requestScope.seminarRoomReserv }">
											<tr>
												<td><c:out value="${ seminarRoomReserv.meetingRoomNo }" /></td>
												<td><c:out value="${ seminarRoomReserv.useDate }" /></td>
												<td><c:out value="${ seminarRoomReserv.reservTime }" /></td>
												<td>Y</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
							<div class="form-group">
								<label>신청 중인 세미나실 번호</label> <input name="roomNo" class="form-control"
									type="text" value="${ requestScope.roomNo }"
									readonly="readonly"/>
							</div>
							<div class="form-group">
								<label>신청 날짜</label> <select name="useDate"
									class="form-control" required="required">
									<option value="">-- 예약 가능 일자 --</option>
									<option><c:out value="${ requestScope.sysDate }" /></option>
									<option><c:out value="${ requestScope.sysNextDate }" /></option>
									<option><c:out value="${ requestScope.sysTwiceNextDate }" /></option>
								</select>
							</div>
							<div class="form-group">
								<label>이용 시간</label> <select name="reservTime"
									class="form-control" required="required">
									<option value="">-- 사용 시간대 --</option>
									<c:forEach var="seminarReservTime"	items="${ requestScope.seminarReservTime }">
									<option value="${ seminarReservTime.reservNo }"><c:out value="${ seminarReservTime.reservTime }"/></option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label>회의 목적</label>
								<textarea name="seminarInfo" cols="30" rows="6" class="form-control" required="required"></textarea>
							</div>

							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg" type="submit">신청	완료</button>
								<button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {
	        	
	        } else {
				location.href = "${ pageContext.servletContext.contextPath }/welfare/list/selected?selectedWelfare=회의실예약신청"
	        }
		}
		
		function askAgain(){
			
			var yn;
			yn = confirm('회의실 대여 신청을 완료하시겠습니까?');
			
			if(yn == true){
				return true;
			}
			else if(yn == false){
				return false;
			}
		}	
	</script>
</body>

</html>