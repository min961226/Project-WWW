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
						<h4 class="page-title">휴가일정 생성 신청</h4>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="card-box">
							<h4 class="card-title">내용작성</h4>

							<form class="form-horizontal" id="frm"
								action="${ pageContext.servletContext.contextPath }/schedule/holiday/insert"
								method="post" onsubmit="return askAgain();" enctype="multipart/form-data">
								<div class="row">
									<div class="col-md-9">

										<div class="form-group">
											<label class="col-md-3 control-label">신청자</label>
											<div class="col-md-9">
												<input type="text" value="${ sessionScope.memberInfo.name }"
													class="form-control" disabled>
												<small>*정기연차 이외 휴가의 소멸기간은 1년 입니다.</small>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">결재라인</label>
											<div class="col-md-9">
												<select class="select" name="line">
													<option>--결재라인 선택--</option>
													<c:forEach var="line" items="${ requestScope.lineList }">
														<option value=${ line.lineNo }>${ line.lineName }</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">휴가종류</label>
											<div class="col-md-9">
												<select class="select" name="holidayCode">
													<option>--휴가종류 선택--</option>
													<c:forEach var="holidayCode"
														items="${ requestScope.holidayTypeList }">
														<option value=${ holidayCode.holidayCode }>${ holidayCode.holidayName }</option>
													</c:forEach>
												</select>
											</div>
										</div>
					<!-- 시작일보다 종료일이 빠를경우, 예외처리 필요 -->
										<div class="form-group">
											<label class="col-md-3 control-label">시작일</label>
											<div class="col-md-4">
												<input id="startDay" type="date" class="form-control" name="startDay" required>
											</div>
											<div class="col-md-4">
												<select class="select" id="startDayAllday" name="startDayAllday">
													<option value="종일">종일</option>
													<option value="오전">오전시작</option>
													<option value="오후">오후시작</option>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">종료일</label>
											<div class="col-md-4">
												<input id="endDay" type="date" class="form-control" name="endDay" onchange="calDuring(this)" required>
											</div>
											<div class="col-md-4">
												<select class="select" id="endDayAllday" name="endDayAllday">
													<option value="종일">종일</option>
													<option value="오전">오전까지</option>
													<option value="오후">오후까지</option>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">사유</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="reason" placeholder="사유를 기입하세요">
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-3 control-label">증빙자료 첨부</label>
											<div class="col-md-9">
												<input type="file" class="form-control" name="file1" multiple="multiple"> 
												<small class="help-block">파일 최대 사이즈: 10 MB. 허용된 확장자: jpg, gif, png. </small>
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

				<br> <br>

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
			//onchange를 endDayAllday에 달아야 할 것 같은데
			function calDuring(obj) {
				var sdd = document.getElementById('startDay').value;
				var edd = document.getElementById('endDay').value;
				var sda = document.getElementById('startDayAllday').value; //'오후'이면 -0.5
				var eda = document.getElementById('endDayAllday').value; //'오전'이면 -0.5
				console.log(sdd);
				console.log(edd);
				console.log(sda);
				console.log(eda);
								
				var ar1 = sdd.split('-');
			    var ar2 = edd.split('-');
			    var da1 = new Date(ar1[0], ar1[1], ar1[2]);
			    var da2 = new Date(ar2[0], ar2[1], ar2[2]);
			    var dif = da2 - da1;
			    
			    var cDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
			    var cMonth = cDay * 30;// 월 만듬
			    var cYear = cMonth * 12; // 년 만듬
			    
				let $during = document.getElementById('during');
			    			
				if(sdd && edd){
					
					if(sda === '오후') {
					$during.value = parseInt(dif/cDay) + 0.5;
					}
					
					if(sda === '오전') {
					$during.value = parseInt(dif/cDay) + 0.5;
					}
				 }
			} 

			const $goBack = document.getElementById("goBack");
			$goBack.onclick = function() {
				if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {

				} else {
					location.href = "${ pageContext.servletContext.contextPath }/schedule/holiday/select"
				}
			}

			function askAgain() {
				var yn;
				yn = confirm('휴가 신청을 완료하시겠습니까?\n신청 후에는 수정이 불가합니다');

				if (yn == true) {
					return true;
				} else {
					return false;
				}
			}
			
		</script>
	
</body>

</html>