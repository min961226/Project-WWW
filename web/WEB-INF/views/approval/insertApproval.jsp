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
						<h4 class="page-title">결재신청</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						

							<form class="form-horizontal" action="${ pageContext.servletContext.contextPath }/approval/insert" method="post" onsubmit="return askAgain();"  enctype="multipart/form-data">


								<div class="form-group">
									<div class="col-sm-6">
										<label>결재 분류</label>
										<div class="col-md-12">
											<select id="document" class="form-control" name="documentNo"  onchange="testChange(this)">
												<option value=1>일반기안문</option>
												<option value=2>일반품의서</option>
												<option value=3>일반결의서</option>
											</select>
										</div>
									</div>			
									<div class="col-sm-6">
										<label>결재 제목</label>
										<div class="col-md-12">
											<input type="text" name="title" class="form-control" required="required">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-6">
										<label>기안자</label>
										<div class="col-md-12">
										<input class="form-control" value="${ sessionScope.memberInfo.name }"  disabled/>
										</div>
									</div>

									<div class="col-sm-6">
										<label>결재라인</label>
										<div class="col-md-12">
											<select class="form-control" name="line" required>
												<option value=''>--결재라인 선택--</option>
												<c:forEach var="line" items="${ requestScope.lineList }">
												<option value= ${ line.lineNo }>${ line.lineName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								
								<div class="form-group">
								<div class="col-sm-6">
									<label>첨부서류</label>
									<div class="col-md-12">
										<input class="form-control" type="file"  name="file1" multiple="multiple"> <small	class="help-block">파일 최대 사이즈: 10 MB. 허용된 확장자: jpg,	gif, png. </small>
									</div>
								</div>
								
								<div class="col-sm-6">
										<label>보존기간</label>
										<div class="col-md-12">
										
										<input class="form-control" value="~${ requestScope.endDate }"  disabled/>
										</div>
									</div>
								
								
								</div>
								
								
								<div id="div1" class="form-group" hidden>
								
									<div class="col-sm-3">
										<label>계약일</label>
										<div class="col-md-12">
										<input type="date" name="contractDate"  class="col-md-12"/>
										</div>
										
									</div>

									<div class="col-sm-3">
										<label>지출예정일</label>
										<div class="col-md-12">
											<input type="date" name="payDate"  class="col-md-12"/>
										</div>
									</div>
									
									
									
									 <div class="col-sm-6">
										<label>물품분류</label>
										<div class="col-md-12">
											<select class="form-control" name="productNo">
												<option value="p-1">업무지원비</option>
												<option value="p-2">식비</option>
												<option value="p-3">교통비</option>
												<option value="p-4">물품구입비</option>
												<option value="p-5">기타</option>
											</select>
										</div>
									</div>
								</div>
								
								
								
								
								<div class="form-group">
								<div class="col-sm-12">
									<label>내용</label>
									<div class="col-lg-12">
										<textarea name="body" rows="8" cols="5" class="form-control"
											placeholder="내용을 입력해주세요" required="required"></textarea>
									</div>
								</div>
								</div>
								
								
								<div class="form-group">
								<div class="col-sm-12">
									<label>비고</label>
									<div class="col-lg-12">
										<textarea name="note" rows="5" cols="5" class="form-control"
											placeholder="비고를 입력해주세요"></textarea>
									</div>
								</div>
								</div>					
								<div class="row">
                                    <div class="col-sm-12 text-center m-t-20">
                                        <button type="submit" class="btn btn-primary btn-lg"> 신청 </button>
                                        <button type="reset" class="btn btn-primary btn-lg" id="goBack">취소</button>
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
	const $goBack = document.getElementById("goBack");
	$goBack.onclick = function() {
		if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {
        	
        } else {
			location.href = "${ pageContext.servletContext.contextPath }/approval/applied/select"
        }
	}
	function askAgain(){
		
		var yn;
		yn = confirm('작성하신 내용의 결재를 기안하시겠습니까?');
		
		if(yn == true){
			return true;
		}
		else if(yn == false){
			return false;
		}
	}	
	
	 // onchange에서 호출하는 함수. 
    function testChange(obj){
    	var result = $('#document option:selected').val();
        if (result == 3) {
          $('#div1').show();
        } else {
          $('#div1').hide();
        }
    }

    	
    </script>
</body>


</html>