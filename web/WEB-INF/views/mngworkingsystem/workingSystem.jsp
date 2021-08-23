<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
						<h4 class="page-title">근무제도 관리</h4>
					</div>
					<div class="col-xs-4 text-right m-b-30">
						<a href="#" class="btn btn-primary rounded pull-right" data-toggle="modal" data-target="#add_leave"> 
							<i class="fa fa-plus"></i> 근무추가하기
						</a>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-3 col-sm-4" style="display : block">
						
						<c:forEach var="workTypeContent" items="${ requestScope.workTypeList }">
							<div class="card-box project-box">
								<h4 class="project-title"><c:out value="${ workTypeContent.workName }"/></h4>
								<small class="block text-ellipsis m-b-15">
										<span class="text-xs">근무제코드 : </span><c:out value="${ workTypeContent.workCode }"/>
								</small>
								<div class="pro-deadline m-b-15">
	                                <div class="sub-title"> 최소근무시간(일일기준) </div>
	                                <div class="text-muted"><c:out value="${ workTypeContent.minimalWorkHour }"/></div>
	                            </div>
	                            <div class="pro-deadline m-b-15">
	                                <div class="sub-title"> 휴게시작시간 </div>
	                                <div class="text-muted"><c:out value="${ workTypeContent.breakStartTime }"/></div>
	                            </div>
	                            <div class="pro-deadline m-b-15">
	                                <div class="sub-title"> 휴게종료시간 </div>
	                                <div class="text-muted"><c:out value="${ workTypeContent.breakEndTime }"/></div>
	                            </div>
	                            <div class="pro-deadline m-b-15">
	                                <div class="sub-title"> 출근 ~ 퇴근 </div>
	                                <div class="text-muted"><c:out value="${ workTypeContent.checkInTime } ~ ${ workTypeContent.checkOutTime }"/></div>
	                            </div>
	                            <form action="${ pageContext.servletContext.contextPath }/mng/workingSystem/delete" method="post">
	                            	<input type="text" name="workCode" value="${ workTypeContent.workCode }" hidden>
	                            	<button type="submit" id="decline" class="btn btn-danger btn-md"> 삭제하기 </button>
	                            </form>
							</div>
							
						</c:forEach>
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

							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg">표준근무제 추가하기</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>

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
			
		</script>
</body>

</html>