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
		<jsp:include page="../common/navbar.jsp" />
		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<h4 class="page-title">결재신청</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						

							<form class="form-horizontal" action="${ pageContext.servletContext.contextPath }/approval/insert" method="post">


								<div class="form-group">
									<div class="col-sm-6">
										<label>결재 유형</label>
										<div class="col-md-12">
											<select id="document" class="form-control" name="documentNo"  onchange="testChange(this)">
												<option value='업무'>업무</option>
												<option value='복지'>복지</option>
											</select>
										</div>
									</div>
									
									
									
									<div class="col-sm-6">
										<label>결재라인 명</label>
										<div class="col-md-12">
											<input type="text" name="title" class="form-control" required="required">
										</div>
									</div>
								</div>


								
								
								<div class="row">
                                    <div class="col-sm-12 text-center m-t-20">
                                        <button type="submit" class="btn btn-primary btn-lg"> 생성 </button>
                                        <button type="reset" class="btn btn-primary btn-lg" id="goBack">취소</button>
                                    </div>
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
    		location.href = "${ pageContext.servletContext.contextPath }/approval/line/select"
    	}
    	
    	
    	
    </script>
</body>

</html>