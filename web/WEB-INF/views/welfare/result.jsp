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
<!-- include summernote css/js-->

<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/summernote/summernote-lite.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"rel="stylesheet">
<!-- include summernote-ko-KR -->

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
							
							<table class="b-table" border="10" bordercolor="yellowgreen">
								<tr>
									<td><font size="4">내용</font></td>
								</tr>
								<tr>

									<td colspan="2"><textarea id="summernote"
											name="postContent" readonly="readonly">${requestScope.requestContent}</textarea> <!-- <textarea name="postContent" cols="60" rows="15" style="resize:none;" required>
 --></td>
								</tr>
							</table>
							
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