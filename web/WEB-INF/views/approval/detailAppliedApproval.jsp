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
						<h4 class="page-title">결재요청함 상세보기</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						

							<form class="form-horizontal" action="${ pageContext.servletContext.contextPath }/approval/applied/selectOne?no=${ requestScope.selectedReport.reportNo }" method="post">
							
							
							  
								
							
								<div class="form-group">
									<div class="col-sm-6">
										<label>결재 분류</label>
										<div class="col-md-12">
											<input class="form-control" value=
											     <c:if test="${requestScope.selectedReport.documentNo eq 1}">"일반기안문"</c:if>
											     <c:if test="${requestScope.selectedReport.documentNo eq 2}">"일반품의서"</c:if>
											     <c:if test="${requestScope.selectedReport.documentNo eq 3}">"일반결의서"</c:if>  disabled/>
										</div>
									</div>
									
									<div class="col-sm-6">
										<label>결재 제목</label>
										<div class="col-md-12">
											<input class="form-control" value="${ requestScope.selectedReport.reportTitle }"  disabled/>
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
											<input class="form-control" value="${ requestScope.selectedReport.lineName }"  disabled/>
										</div>
									</div>
								</div>
								
								<div class="form-group">
								<div class="col-sm-6">
									<label>첨부서류</label>
									<div class="col-md-12">
										<input class="form-control" type="file">
									</div>
								</div>
								
								<div class="col-sm-6">
										<label>보존기간</label>
										<div class="col-md-12">
										<input class="form-control" value="~${ requestScope.endDate }"  disabled/>
										</div>
									</div>
								
								
								</div>
								
								<c:set var = "no" value ="${ requestScope.selectedReport.documentNo }" />
								<c:if test="${  no eq 3 }">
								<div id="area3" class="form-group" >
								
									<div class="col-sm-3">
										<label>계약일</label>
										<div class="col-md-12">
										<input class="form-control" value="${ requestScope.contractDate }"  disabled/>
										</div>
										
									</div>

									<div class="col-sm-3">
										<label>지출예정일</label>
										<div class="col-md-12">
											<input class="form-control" value="${ requestScope.payDate }"  disabled/>
										</div>
									</div>
									
									
									
									 <div class="col-sm-6">
										<label>물품번호</label>
										<div class="col-md-12">
											<input class="form-control" value="${ requestScope.productNo }"  disabled/>
										</div>
									</div>
								</div>
								</c:if>
								
								
								
								<div class="form-group">
								<div class="col-sm-12">
									<label>내용</label>
									<div class="col-lg-12">
										<textarea name="body" rows="8" cols="5" class="form-control"
											disabled>${ requestScope.body }</textarea>
									</div>
								</div>
								</div>
								
								
								<div class="form-group">
								<div class="col-sm-12">
									<label>비고</label>
									<div class="col-lg-12">
										<textarea name="note" rows="5" cols="5" class="form-control"
											disabled>${ requestScope.selectedReport.reportNote }</textarea>
									</div>
								</div>
								</div>
								
								<c:forEach var="line" items="${ requestScope.ALPRList }">
								<div class="card-box col-lg-3">
                                    <h4 class="card-title">${ line.memberName }님의 결재내용 조회</h4>
                                    <div class="form-group">
                                    
                                        <div class="col-lg-10">
                                          <label>결재처리</label>
                                                 <input name="appStatus" value=${ line.appStatus } disabled/>
                                        </div>
								        <div class="col-md-12">
									        <label>의견</label>
									        <br>
								        	<div class="col-sm-12">
										        <textarea name="opinion" rows="4" cols="6" class="form-control"
											        disabled>${ line.appNote }</textarea>
											        <br><br>
									        </div>
								        </div>
								        </div> 
                                </div>
                                </c:forEach>
                                
								<div class="row">
                                    <div class="col-sm-12 text-center m-t-20">
                                        <c:set var = "no" value ="${ requestScope.selectedReport.reportStatus }" />
				                        <c:if test="${  no eq '대기' }">
                                            <button type="submit" class="btn btn-primary btn-lg"> 회수하기 </button>
                                        </c:if>
                                        <button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
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
    		location.href = "${ pageContext.servletContext.contextPath }/approval/applied/select"
    	}
    </script>
</body>

</html>