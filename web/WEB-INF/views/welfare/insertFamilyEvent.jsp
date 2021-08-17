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
<script>
	$goBack.onclick = function() {
		location.href = "${ pageContext.servletContext.contextPath }";
	}
</script>
</head>

<body>
	<div class="main-wrapper">
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<h4 class="page-title">경조금 신청서</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form name="insertSelfDev"	action="${ pageContext.servletContext.contextPath }/welfare/familyEvent/insert"	method="POST">
							<div class="form-group">
								<label>직원 ID</label> <input name="memberNo" class="form-control" type="text" value="${memberNo}" readonly="readonly" />
							</div>
							<div class="form-group">
								<label>부서</label> <input name="deptName" class="form-control" type="text" value="${ sessionScope.memberInfo.department.deptName }" required="required">
							</div>
							<div class="form-group">
								<label>직위</label> <input name="jobName" class="form-control" type="text" value="${ sessionScope.memberInfo.job.jobName }" required="required">
							</div>
							<div class="form-group">
								<label>신청자</label> <input name="name" class="form-control"	type="text" value="${ sessionScope.memberInfo.name }" readonly="readonly">
							</div>
							<br>
							<label>경조사 종류</label> 
							<select id="document" class="form-control" name="eventType" onchange="testChange(this)" required="required">
								<option value="">-- 전체 경조사 --</option>
								<option value="1">결혼</option>
								<option value="2">출산</option>
								<option value="3">회갑</option>
								<option value="4">사망</option>
							</select>
							<div id="marrige" class="form-group" hidden>
								<select id="selectBox" class="form-control" name="marrige">
									<option value="">-- 관계 --</option>
									<option value="본인">본인</option>
									<option value="자녀">자녀</option>
								</select>
							</div>
							<div id="childBirth" class="form-group" hidden>
								<select id="selectBox" class="form-control" name="childBirth">
									<option value="">-- 자녀 --</option>
									<option value="첫째/둘째">첫째/둘째</option>
									<option value="셋째/넷째">셋째/넷째</option>
								</select>
							</div>
							<div id="sixtyBirth" class="form-group" hidden>
								<select id="selectBox" class="form-control" name="sixtyBirth">
									<option value="">-- 관계 --</option>
									<option value="본인">본인</option>
									<option value="부모">부모</option>
									<option value="조부모">조부모</option>
								</select>
							</div>
							<div id="death" class="form-group" hidden>
								<select id="selectBox" class="form-control" name="death">
									<option value="">-- 관계 --</option>
									<option value="본인">본인</option>
									<option value="배우자">배우자</option>
									<option value="자식">자식</option>
									<option value="부모">부모</option>
								</select>
							</div>
							<br>
							<div class="form-group">
								<label>화환 여부</label> 
								<br>
								<input name="wreathStatus" type="radio" value="Y" required="required">&nbsp; 예 &nbsp;&nbsp;<input name="wreathStatus" type="radio" value="N" required="required">&nbsp; 아니오
							</div>
							<br>
							<div class="form-group">
								<label>경조사명</label> <input name="eventName" class="form-control" type="text" required="required">
							</div>
							<div class="form-group">
								<label>경조사 일자</label> <input name="date" class="form-control" type="date" required="required">
							</div>
							<div class="form-group">
								<label>장소</label> <input name="eventPlace" class="form-control" type="text" required="required">
							</div>
							<div class="form-group">
								<label>결재선 지정</label> <select name="lineList" 	class="form-control" required="required">
									<option value="">-- 결재선 지정 목록 --</option>
									<c:forEach items="${ requestScope.lineList }" var="lineList"><option value=${ lineList.lineNo }>${ lineList.lineName }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>신청 내용</label>
								<textarea name="eventInfo" cols="30" rows="6" class="form-control" required="required"></textarea>
							</div>

							<div class="form-group">
								<label>증빙 자료 첨부</label>
								<div>
									<input class="form-control" type="file"> <small	class="help-block">파일 최대 사이즈: 50 MB. 허용된 확장자: jpg, gif, png. </small>
								</div>

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
		function testChange(obj) {
			var result = $('#document option:selected').val();
			if (result == 1) {
				$('#marrige').show();
				$('#childBirth').hide();
				$('#sixtyBirth').hide();
				$('#death').hide();
			}else if(result == 2) {
				$('#marrige').hide();
				$('#childBirth').show();
				$('#sixtyBirth').hide();
				$('#death').hide();
			}else if(result == 3) {
				$('#marrige').hide();
				$('#childBirth').hide();
				$('#sixtyBirth').show();
				$('#death').hide();
			}else if(result == 4) {
				$('#marrige').hide();
				$('#childBirth').hide();
				$('#sixtyBirth').hide();
				$('#death').show();
			}
		}

		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {
	        	
	        } else {
				location.href = "${ pageContext.servletContext.contextPath }/welfare/list/select"
	        }
		}
	</script>
</body>

</html>