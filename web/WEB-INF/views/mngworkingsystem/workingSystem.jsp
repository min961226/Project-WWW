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
					<div class="col-xs-8">
						<h4 class="page-title">Product Details</h4>
					</div>
					<div class="col-xs-4 text-right m-b-30">
						<a href="#" class="btn btn-primary rounded pull-right"
							data-toggle="modal" data-target="#add_leave"> <i
							class="fa fa-plus"></i> 근무추가하기
						</a>
					</div>
				</div>
				<div class="card-box">
					<div class="row">
						<div class="col-xs-12">
							<!-- 네비게이션 바 부분 -->
							<ul class="nav nav-tabs nav-tabs-bottom">
								<c:forEach var="workType" items="${ requestScope.workTypeList }">
									<li><a href="w${ workType.workCode }" data-toggle="tab">
										<c:out value="${ workType.workName }"/></a></li>
								</c:forEach>
								<!-- <li><a href="#w1" data-toggle="tab">근무제 추가 설명. 기본근태</a></li>
								<li><a href="#w2" data-toggle="tab">시차출퇴근1</a></li>
								<li><a href="#w3" data-toggle="tab">시차출퇴근2</a></li> -->
							</ul>
							
							<!-- 네비게이션 바에 해당하는 내용부분 -->
							<c:forEach var="workTypeContent" items="${ requestScope.workTypeList }">
								<div class="tab-content">
									<div class="tab-pane active" id="w${ workTypeContent.workCode }">
										<div class="product-content">
											<div class="form-group">
												<label>근무제 명 <span class="text-red">*</span></label> 
												<input name="workNo" type="text" class="form-control" value="${ workTypeContent.workCode }" hidden> 
												<input type="text" class="form-control"
													value="${ workTypeContent.workName }">
											</div>
											<div class="form-group">
												<label>최소근무시간 <small> (일일 기준)</small></label> 
												<input type="text" class="form-control"
													value="${ workTypeContent.minimalWorkHour }">
											</div>
											<div class="form-group">
												<label>휴게시작시간 <span class="text-red">*</span></label> 
												<input type="text" class="form-control"
													value="${ workTypeContent.breakStartTime }">
											</div>
											<div class="form-group">
												<label>휴게종료시간 <span class="text-red">*</span></label> <input
													type="text" class="form-control"
													value="${ workTypeContent.breakStartTime }">
											</div>
											<div class="form-group">
												<label>출근시간 <span class="text-red">*</span></label> <input
													type="text" class="form-control"
													value="${ workTypeContent.checkInTime }">
											</div>
											<div class="form-group">
												<label>퇴근시간 <span class="text-red">*</span></label> <input
													type="text" class="form-control"
													value="${ workTypeContent.checkOutTime }">
											</div>

											<div class="col-xs-4 text-right m-b-30">
												<a href="${ pageContext.servletContext.contextPath }/mng/workingSystem/delete?workCode=${ workTypeContent.workCode }" 
												class="btn btn-danger rounded pull-right"
													data-toggle="modal" data-target="#delete_approve"
													id="decline"> <i class="fa fa-minus"></i> 삭제하기
												</a>
											</div>

										</div>
									</div>
								</div>
							</c:forEach>
							
							<!-- <ul>
							<li><a href="#w1" data-toggle="tab">근무제 추가 설명. 기본근태</a></li>
								<li><a href="#w2" data-toggle="tab">시차출퇴근1</a></li>
								<li><a href="#w3" data-toggle="tab">시차출퇴근2</a></li>
							</ul> -->
							
							<!-- <div class="tab-content">
								<div class="tab-pane active" id="w1">
									<div class="product-content">
										<div class="form-group">
											<label>근무제 명 <span class="text-red">*</span></label> <input
												id="workName" type="text" class="form-control" value="기본근태">
										</div>

										<div class="form-group">
											<label>근무제 번호 <span class="text-red">*</span></label> <input
												id="workNo" type="text" class="form-control" value="1">
										</div>
										<p>설명설명</p>
										<blockquote>
											<p>설명설명</p>
										</blockquote>
										<p>설명설명</p>
									</div>
									<div class="col-xs-4 text-right m-b-30">
										<a href="#" class="btn btn-danger rounded pull-right"
											data-toggle="modal" data-target="#delete_approve"
											id="decline"> <i class="fa fa-minus"></i> 삭제하기
										</a>
									</div>
								</div>
							</div> -->
							
							
						</div>
					</div>
				</div>
			</div>

		</div>

		<!-- 모달 부분 -->
		<div id="add_leave" class="modal custom-modal fade" role="dialog">
			<div class="modal-dialog">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="modal-content modal-md">
					<div class="modal-header">
						<h4 class="modal-title">Add Leave Request</h4>
					</div>
					<div class="modal-body">
						<form
							action="${ pageContext.servletContext.contextPath }/mng/workingSystem/insert"
							method="post">

							<div class="form-group">
								<label>근무제 명<span class="text-red">*</span></label> <input
									type="text" class="form-control" name="workName" required>
							</div>

							<div class="form-group">
								<label>최소근무시간
								<span class="text-red">*</span><span>(시간 단위. 숫자만 입력하세요)</span>
								</label> 
								<input type="text" class="form-control" name="minimal" required>
							</div>

							<div>
								<div class="form-group col-sm-6">
									<label>휴게시작시간</label> <select class="select"
										name="breakStartHour" required>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
									</select> <select class="select" name="breakStartMin" required>
										<option value="00">00</option>
										<option value="30">30</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>휴게종료시간</label> <select class="select"
										name="breakEndHour" required>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
									</select> <select class="select" name="breakEndMin" required>
										<option value="00">00</option>
										<option value="30">30</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label>출퇴근자율여부 <span class="text-danger">*</span></label> <select
									id="workType" class="select" name="workOutFreeType"
									onchange="testChange(this)">
									<option value=1>자율출퇴근 설정</option>
									<option value=2>출퇴근시간 설정</option>
								</select>
							</div>

							<!-- 출퇴근시간 설정을 선택할 경우, 나타나는 칸 -->
							<div id="div1" hidden>
								<div class="form-group col-sm-6">
									<label>매일 출근 시간</label> <select class="select"
										name="checkInHour" required>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
									</select> <select class="select" name="checkInMin" required>
										<option value="00">00</option>
										<option value="30">30</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>매일 퇴근 시간</label> <select class="select"
										name="checkOutHour" required>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
									</select> <select class="select" name="checkOutMin" required>
										<option value="00">00</option>
										<option value="30">30</option>
									</select>
								</div>
							</div>

							<!-- <div class="form-group col-md-6">
								<label>From <span class="text-danger">*</span></label>
								<div class="cal-icon">
									<input class="form-control datetimepicker" type="text">
								</div>
							</div>
							<div class="form-group col-md-6">
								<label>To <span class="text-danger">*</span></label>
								<div class="cal-icon">
									<input class="form-control datetimepicker" type="text">
								</div>
							</div>

							<div class="form-group">
								<label>Leave Reason <span class="text-danger">*</span></label>
								<textarea rows="4" cols="5" class="form-control"></textarea>
							</div> -->

							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg">표준근무제 추가하기</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- 삭제관련 모달부분 -->
		<!-- 데이터를 가져와야하는데 그게 번거로울 것 같아서 유보 -->
		<!-- <div id="delete_approve" class="modal custom-modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content modal-md">
					<div class="modal-header">
						<h4 class="modal-title">Decline Leave Request</h4>
					</div>
					<form>
						<div class="modal-body card-box">
							<input type="text" name="workName" id="declineworkName" value="" /><br>
							<input type="text" name="workNo" id="declineworkNo" value="" /><br>
							<p>위 번호의 근무제를 삭제하시겠습니까?</p>
							<div class="m-t-20 text-left">
								<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
								<button id="decline" type="submit" class="btn btn-danger">Decline</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div> -->

	</div>
	<!-- main-wrapper END -->

	<script>
			// onchange에서 호출하는 함수. 
			function testChange(obj) {
				var result = $('#workType option:selected').val();
				if (result == 2) {
					$('#div1').show();
				} else {
					$('#div1').hide();
				}
			}
			
			//삭제 모달부분 함수
			/* $(document.getElementById("decline")).on("click", function() {
				var workName = $(this).parant.getElementById("workName").val();
				console.log(workName);
				
				var workNo = getElementById("workNo").val();
				console.log(workNo);
				
				$(".modal-body #declineworkName").val( workName );
				$(".modal-body #declineworkNo").val( workNo );
			}) */
			
		</script>
</body>

</html>