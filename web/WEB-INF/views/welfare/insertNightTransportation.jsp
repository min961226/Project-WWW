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
                    <div class="col-md-8 col-md-offset-2">
                        <h4 class="page-title">야간교통비 신청서</h4>
                    </div>
                </div>
                <div class="row">
                   <div class="col-md-8 col-md-offset-2">
						<form name = "insertNightTran" action="${ pageContext.servletContext.contextPath }/welfare/nightTransportation/insert" method="POST" onsubmit="return askAgain();"  enctype="multipart/form-data">
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
							<div class="form-group">
								<label>결재선 지정</label> <select name="lineList" 	class="form-control" required="required">
									<option value="">-- 결재선 지정 목록 --</option>
									<c:forEach items="${ requestScope.lineList }" var="lineList"><option value=${ lineList.lineNo }>${ lineList.lineName }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>추가 근무 근거</label> <select name="overTimeLog" class="form-control" required="required">
									<option value="">-- 결재 완료 야근 목록 --</option>
									<c:forEach items="${ requestScope.memberOverTimeLog }" var="memberOverTimeLog">
										<option value ="${ memberOverTimeLog.overTimeStartDay } ${memberOverTimeLog.overTimeStartTime } ~ ${memberOverTimeLog.overTimeEndDay} ${ memberOverTimeLog.overTimeEndTime }"><c:out value="${ memberOverTimeLog.overTimeStartDay } ${memberOverTimeLog.overTimeStartTime } ~ ${memberOverTimeLog.overTimeEndDay} ${ memberOverTimeLog.overTimeEndTime } ( ${ memberOverTimeLog.overTimeHour}시간)" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>청구 예상 교통비</label> <br><input name="transBill" class="" type="number" width="100px"required="required">￦
							</div>

							<div class="form-group">
								<label>업무 내용</label>
								<textarea name="overTimeLogInfo" cols="30" rows="6" class="form-control" required="required"></textarea>
							</div>

							<div class="form-group">
								<label>영수증 첨부</label>
								<div>
									<input class="form-control" type="file" name="file1" multiple="multiple"> <small	class="help-block">파일 최대 사이즈: 10 MB. 허용된 확장자: jpg,	gif, png. </small>
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
		const $goBack = document.getElementById("goBack");

		$goBack.onclick = function() {
			if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {
	        	
	        } else {
				location.href = "${ pageContext.servletContext.contextPath }/welfare/list/select"
	        }
		}
		
		function askAgain(){
			
			var yn;
			yn = confirm('야간 교통비 신청을 완료하시겠습니까?\n신청 후에는 수정이 불가합니다');
			
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