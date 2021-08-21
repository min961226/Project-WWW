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
							<form class="form-horizontal" action="${ pageContext.servletContext.contextPath }/schedule/workingSystem/insert"
								method="post" onsubmit="return askAgain();" enctype="multipart/form-data">
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

												<select id="work" class="select" name="workNo"
													onchange="testChange(this)" required>
													<option value="">--근무제목록-</option>
													<c:forEach items="${ requestScope.workTypeList }"
														var="workTypeList">
														<option value="${ workTypeList.workCode }"><c:out
																value="${ workTypeList.workName }" /></option>
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
												<input class="form-control" type="file" name="file1"
													multiple="multiple"> <small class="help-block">파일
													최대 사이즈: 10 MB. 허용된 확장자: jpg, gif, png. </small>
											</div>

										</div>
									</div>


									<!-- 첫번째 컬럼 끝 -->
									<div class="col-md-6" id="div1" hidden>
										<h4>선택근무 추가작성내용</h4>
										<div><small>* 선택근무 시, 일일 최소근무시간은 4시간 입니다.</small></div>
										<div><small>** 일일 휴게시간은 1시간이며, 시간 설정시 1시간을 빼고 주간근무시간이 계산됩니다. (주간 : 40시간 이내)</small></div>
										<div>
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">월 출근</label>
												<div class="col-md-6">
													<select class="select" name="monStartTimeHour" required>
														<option value="07">07</option>
														<option value="08">08</option>
														<option value="09" selected>09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="monStartTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">월 퇴근</label> 
												<div class="col-md-6">
													<select class="select" name="monEndTimeHour" required>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18" selected>18</option>
														<option value="19">19</option>
														<option value="20">20</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="monEndTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">화 출근</label>
												<div class="col-md-6">
													<select class="select" name="tueStartTimeHour" required>
														<option value="07">07</option>
														<option value="08">08</option>
														<option value="09" selected>09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="tueStartTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">화 퇴근</label> 
												<div class="col-md-6">
													<select class="select" name="tueEndTimeHour" required>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18" selected>18</option>
														<option value="19">19</option>
														<option value="20">20</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="tueEndTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">수 출근</label>
												<div class="col-md-6">
													<select class="select" name="wedStartTimeHour" required>
														<option value="07">07</option>
														<option value="08">08</option>
														<option value="09" selected>09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="wedStartTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">수 퇴근</label> 
												<div class="col-md-6">
													<select class="select" name="wedEndTimeHour" required>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18" selected>18</option>
														<option value="19">19</option>
														<option value="20">20</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="wedEndTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">목 출근</label>
												<div class="col-md-6">
													<select class="select" name="thuStartTimeHour" required>
														<option value="07">07</option>
														<option value="08">08</option>
														<option value="09" selected>09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="thuStartTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">목 퇴근</label> 
												<div class="col-md-6">
													<select class="select" name="thuEndTimeHour" required>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18" selected>18</option>
														<option value="19">19</option>
														<option value="20">20</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="thuEndTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">금 출근</label>
												<div class="col-md-6">
													<select class="select" name="friStartTimeHour" required>
														<option value="07">07</option>
														<option value="08">08</option>
														<option value="09" selected>09</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="friStartTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="col-md-12 control-label">금 퇴근</label> 
												<div class="col-md-6">
													<select class="select" name="friEndTimeHour" required>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18" selected>18</option>
														<option value="19">19</option>
														<option value="20">20</option>
													</select>
												</div>
												<div class="col-md-6">
													<select class="select" name="friEndTimeMin" required>
														<option value="00">00</option>
														<option value="30">30</option>
													</select>
												</div>
											</div>
											
											
										</div>

										<!-- <div class="col-md-6">
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
										</div> -->

									</div>


									<div class="row" id="div2" hidden>
										<h4>초과근무 추가작성내용</h4>
										<div><small>* 초과근무는 주 12시간을 넘을 수 없습니다. 신청 전 결재내역 확인부탁드립니다. </small></div>
										<div><small>** 여러일에 걸치지 않는경우, 시작일과 종료일을 같은날로 설정해주시기 바랍니다. </small></div>
										<br>
										<hr>
										<br>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-md-3 control-label">시작시간</label>
												<div class="form-group col-sm-6">
													<select class="select" name="overtimeStartHour" id="overtimeStartHour" required>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">종료시간</label>
												<div class="form-group col-sm-6">
													<select class="select" name="overtimeEndHour" id="overtimeEndHour"
														onchange="calOvertimeDuring(this)" required>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
														<option value="24">24</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">초과근무시간</label>
												<div class="col-md-6">
													<input type="text" class="form-control"
														id="overtimeDuring" name="overtimeDuring" required disabled>
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
			
			//초과근무시간계산
			function calOvertimeDuring(obj) {
				var sdd = document.getElementById('startDay').value;
				var edd = document.getElementById('endDay').value;
				console.log(sdd);
				console.log(edd);
				
				var osh = document.getElementById('overtimeStartHour').value;
				var oeh = document.getElementById('overtimeEndHour').value;
				console.log(osh);
				console.log(oeh);
				
				var dif = oeh - osh;
				console.log(dif);
			    
				//시작일, 종료일, 시작시간, 종료시간이 모두 있을 때
			    if(sdd && edd && osh && oeh){
			    	
			    	//시작일 != 종료일
			    	if(sdd != edd) {
			    		oeh += 24;
			    		console.log(oeh);
			    		
			    		document.getElementById('overtimeDuring').value = dif;
			    	
			    	//시작일 == 종료일
			    	} else {
				    	document.getElementById('overtimeDuring').value = dif;
			    	}
			    	
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