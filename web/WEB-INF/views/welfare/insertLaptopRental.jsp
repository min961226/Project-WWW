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
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/summernote/summernote-lite.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"rel="stylesheet">

</head>

<body>
	<div class="main-wrapper">
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<h4 class="page-title">경조사신청서</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<form name="insertLaptopRental"	action="${ pageContext.servletContext.contextPath }/welfare/laptopRental/insert"	method="POST">
							<div class="form-group">
								<label>직원 ID</label> <input class="form-control" type="text" value="${memberNo}" readonly="readonly" />
							</div>
							<div class="form-group">
								<label>부서</label> <input class="form-control" type="text"
									value="${deptName}">
							</div>
							<div class="form-group">
								<label>직위</label> <input class="form-control" type="text"
									value="${jobName}">
							</div>
							<div class="form-group">
								<label>신청자</label> <input class="form-control" type="text"
									value="${name}">
							</div>
							<div class="form-group">
								<label>회의실명</label> <input class="form-control" type="text">
							</div>
							<div class="form-group">
								<label>이용인원</label> <input class="form-control" type="text">
							</div>
							<div class="form-group">
								<label>이용 시간</label> <select class="select">
									<option>Select</option>
									<option>2014.07.28 (목) 09:00</option>
									<option>Fashion</option>
									<option>Books</option>
									<option>Toys</option>
								</select>
							</div>
							<div class="form-group">
								<label>이용 종료 시간</label> <select class="select">
									<option>Select</option>
									<option>2014.07.28 (목) 10:30</option>
									<option>Fashion</option>
									<option>Books</option>
									<option>Toys</option>
								</select>
							</div>

							<div class="form-group">
								<label>회의 목적</label>
								<textarea cols="30" rows="6" class="form-control"></textarea>

							</div>
							<table class="b-table" border="5" bordercolor="orange">
								<tr>
									<td><font size="4">내용</font></td>
								</tr>
								<tr>

									<td colspan="2"><textarea id="summernote" name="postContent" readonly="readonly"></textarea> <!-- <textarea name="postContent" cols="60" rows="15" style="resize:none;" required>--></td>
								</tr>
							</table>
							<div class="form-group">
								<label>파일첨부</label>
								<div>
									<input class="form-control" type="file"> <small
										class="help-block">파일 최대 사이즈: 50 MB. 허용된 확장자: jpg,
										gif, png. </small>
								</div>

							</div>
							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg">신청 완료</button>
								<button class="btn btn-primary btn-lg">돌아가기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js">
	</script>
	<script src="/assets/js/summernote/lang/summernote-ko-KR.js"></script>
	<script>
		$(document).ready(function() {
			$('#summernote').summernote({
				minHeight : 370,
				maxHeight : null,
				focus : true,
				lang : 'ko-KR'
			});
		});
	</script>
</body>

</html>