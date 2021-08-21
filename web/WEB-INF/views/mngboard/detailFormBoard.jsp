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
				<div class="form-horizontal">
										<div class="row">
						<div class="form-group">
							<div class="col-xs-12">
								<label>작성날짜</label>
								<div class="col-md-12">
									<input name = "title" class="form-control" value="${ requestScope.mngform.created }"  disabled/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<div class="col-xs-12">
								<label>제목</label>
								<div class="col-md-12">
									<input name = "title" class="form-control" value="${ requestScope.mngform.title }"  disabled/>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group">
							<div class="col-sm-12">
								<label>작성자</label>
								<div class="col-md-12">
									<input name = "name" class="form-control" value="${ requestScope.mngform.name }"  disabled/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<div class="col-sm-12">
								<label>내용</label>
								<div class="col-lg-12">
								<textarea name="body" rows="8" cols="5" class="form-control"
										required="required" disabled>${ requestScope.mngform.body }</textarea>
								</div>
							</div>
						</div>
					</div>
					<c:if test="${ !empty requestScope.boardattachmentDTO.savedName}">
								<div class="form-group">
									<div class="col-sm-6">
										<div class="col-md-12">
												<label>첨부 파일 :</label>&nbsp;&nbsp;<a	href="${ pageContext.servletContext.contextPath }/boardFileDown?fileName=${requestScope.boardattachmentDTO.savedName }"><u>${requestScope.boardattachmentDTO.originalName}</u></a>
										</div>
									</div>
								</div>
							</c:if>
				</div>
				<div class="row">
                	<div class="col-sm-12 text-center m-t-20">
                	<button class="btn btn-primary btn-lg" onclick="location.href='${ pageContext.servletContext.contextPath }/mng/board/form/update?no=${ requestScope.mngform.no }'">수정하기</button>
                	<button class="btn btn-primary btn-lg" onclick="location.href='${ pageContext.servletContext.contextPath }/mng/board/form/delete?no=${ requestScope.mngform.no  }'">삭제하기</button>
	                    <button type="reset" class="btn btn-primary btn-lg" id="goBack"> 돌아가기 </button>
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
    		location.href = "${ pageContext.servletContext.contextPath }/mng/board/form/select"
    	}
    	
    	
    	
    </script>
</body>


</html>