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
						<h4 class="page-title">게시판 상세보기</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						

							<form class="form-horizontal" action="${ pageContext.servletContext.contextPath }/approval/insert" method="post">
										
								<div class="form-group">
									<div class="col-sm-6">
										<label>제목</label>
										<div class="col-md-12">
										
										<input class="form-control" value="${ requestScope.free.title }"  disabled/>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-6">
										<label>내용</label>
										<div class="col-md-12">
										
										<input class="form-control" value="${ requestScope.free.body }"  disabled/>
										</div>
									</div>


								</div>
								

					
								<div class="form-group">
								<div class="col-sm-12">
									<label>내용</label>
									<div class="col-lg-12">
										<textarea name="body" rows="8" cols="5" class="form-control"
											required="required" disabled></textarea>
									</div>
								</div>
								</div>
								
								<div class="form-group">
								<div class="col-sm-6">
									<label>첨부서류</label>
									<div class="col-md-12">
										<input class="form-control" type="file" disabled>
									</div>
								</div>
								</div>
								
								

								
								
								<div class="row">
                                    <div class="col-sm-12 text-center m-t-20">
                                        <button type="submit" class="btn btn-primary btn-lg"> 수정 </button>
                                        <button type="reset" class="btn btn-primary btn-lg" id="goBack">삭제</button>
                                    </div>
                                </div>
								
							</form>
					</div>
				</div>
			</div>
			
		</div>

	</div>
	<div class="sidebar-overlay" data-reff=""></div>
	
	<script>
	 // onchange에서 호출하는 함수. 
    function testChange(obj){
    	var result = $('#document option:selected').val();
        if (result == 3) {
          $('#div1').show();
        } else {
          $('#div1').hide();
        }
    }
	
	
	
    	const $goBack = document.getElementById("goBack");
    	$goBack.onclick = function() {
    		location.href = "${ pageContext.servletContext.contextPath }/board/free/select"
    	}
    	
    	
    	
    </script>
</body>


</html>