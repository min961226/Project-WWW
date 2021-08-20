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
					<div class="col-xs-12">
						<h4 class="page-title">근무일정 생성 신청</h4>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="card-box">
							<h4 class="card-title">내용 작성</h4>
							<form class="form-horizontal"
								action="${ pageContext.servletContext.contextPath }/schedule/workingSystem/insert"
								method="post" onsubmit="return askAgain();"
								enctype="multipart/form-data">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-3 control-label">신청근무제도</label>
											<div class="col-md-9">

												<!-- 일단은 선택가능한 거에 넣어두기 -->
												<!-- <select id="work" class="select" name="workNo"
													onchange="testChange(this)" required>
													<option value="">-- 근무제목록 --</option>
													<option value="1">기본근태</option>
													<option value="2">시차출퇴근A</option>
													<option value="3">시차출퇴근B</option>
													<option value="4">시차출퇴근C</option>
													<option value="5">시차출퇴근D</option>
													<option value="6">선택근무제</option>
													<option value="7">초과근무</option>
													<option value="8">새벽출퇴근</option>
												</select> -->
												 
												<select id="work" class="select" name="workNo" onchange="testChange(this)" required>
													<option value="">--근무제목록-</option>
													<c:forEach items="${ requestScope.workTypeList }" var="workTypeList">
														<option value ="${ workTypeList.workCode }"><c:out value="${ workTypeList.workName }"/></option>
													</c:forEach>
												</select>
												 
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">신청자</label>
											<div class="col-md-9">
												<input type="text" value="${ sessionScope.memberInfo.name }"
													class="form-control" disabled>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">결재라인</label>
											<div class="col-md-9">
												<select class="select" name="line" required>
													<option>--결재라인 선택--</option>
													<c:forEach var="line" items="${ requestScope.lineList }">
														<option value=${ line.lineNo }>${ line.lineName }</option>
													</c:forEach>
												</select>
											</div>
										</div>
				<!-- 시작일보다 종료일이 빠를경우, 예외처리 필요 -->
										<div class="form-group">
											<label class="col-md-3 control-label">시작일</label>
											<div class="col-md-9">
												<input type="date" class="form-control" id="startDay"
													name="startDay" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">종료일</label>
											<div class="col-md-9">
												<input type="date" class="form-control" id="endDay"
													name="endDay" required onchange="calDuring(this)">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">기간</label>
											<div class="col-md-9">
												<input type="text" class="form-control" id="during" disabled>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">사유</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="changeReason"
													placeholder="사유를 기입하세요" required>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">증빙자료첨부</label>
											<div class="col-md-9">
												<input class="form-control" type="file" name="file1" multiple="multiple"> 
												<small class="help-block">파일 최대 사이즈: 10 MB. 허용된 확장자: jpg, gif, png. </small>
											</div>

										</div>
									</div>


									<!-- 첫번째 컬럼 끝 -->


									<div class="row" id="div1" hidden>
										<h4>선택근무 추가작성내용</h4>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-md-3 control-label">Address Line 1</label>
												<div class="col-md-9">
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Address Line 2</label>
												<div class="col-md-9">
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">State</label>
												<div class="col-md-9">
													<input type="text" class="form-control">
												</div>
											</div>
										</div>

									</div>


									<div class="row" id="div2" hidden>
										<h4>초과근무 추가작성내용</h4>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-md-3 control-label">Address Line 1</label>
												<div class="col-md-9">
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Address Line 2</label>
												<div class="col-md-9">
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">State</label>
												<div class="col-md-9">
													<input type="text" class="form-control">
												</div>
											</div>
										</div>

									</div>


								</div>

								<div align="center">
									<button type="submit" class="btn btn-success">신청하기</button>
									<button type="reset" class="btn btn-default" id="goBack">돌아가기</button>
								</div>

							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
	<script>
			// onchange에서 호출하는 함수. 
			function testChange(obj) {
				var result = $('#work option:selected').val();
				
				if (result == 6) {
					$('#div1').show();
				} else {
					$('#div1').hide();
				}

				if (result == 7) {
					$('#div2').show();
				} else {
					$('#div2').hide();
				}
			}
			
			//근무기간 계산 
			function calDuring(obj) {
				var sdd = document.getElementById('startDay').value;
				var edd = document.getElementById('endDay').value;
				console.log(sdd);
				console.log(edd);
				
				var ar1 = sdd.split('-');
			    var ar2 = edd.split('-');
			    var da1 = new Date(ar1[0], ar1[1], ar1[2]);
			    var da2 = new Date(ar2[0], ar2[1], ar2[2]);
			    var dif = da2 - da1;
			    
			    var cDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
			    var cMonth = cDay * 30;// 월 만듬
			    var cYear = cMonth * 12; // 년 만듬
			    			
				if(sdd && edd){
				    document.getElementById('during').value = parseInt(dif/cDay) + 1;
				 }
			}

			const $goBack = document.getElementById("goBack");

			$goBack.onclick = function() {
				if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {

				} else {
					location.href = "${ pageContext.servletContext.contextPath }/schedule/workingSystem/select"
				}
			}

			function askAgain() {

				var yn;
				yn = confirm('근무 신청을 완료하시겠습니까?\n신청 후에는 수정이 불가합니다');

				if (yn == true) {
					return true;
				} else {
					return false;
				}
			}
		</script>
</body>

</html>