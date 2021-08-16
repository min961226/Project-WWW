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
						<h4 class="page-title">결재라인 상세보기</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
								<div class="form-group">
									<div class="col-sm-6">
										<label>결재라인명</label>
										<div class="col-md-12">
										<input class="form-control" value="${ requestScope.line.lineName }"  disabled/>
										</div>
									</div>

									<div class="col-sm-6">
										<label>업무유형</label>
										<div class="col-md-12">
											<input class="form-control" value="${ requestScope.line.workType }"  disabled/>
										</div>
									</div>
								</div>
								<br><br><br>
								<h4 class="card-title">라인내 결재자 조회</h4>
								<c:forEach var="approver" items="${ requestScope.approverList}">
								<div class="card-box col-lg-12">
                                  
                                          <label>결재방법</label>
                                                 <input name="approverType" value=${ approver.approverType } disabled/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          <label>결재자부서</label>
                                                 <input name="approverName" value=${ approver.deptName } disabled/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          <label>결재자직위</label>
                                                 <input name="approverName" value=${ approver.jobName } disabled/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          <label>결재자명</label>
                                                 <input name="approverName" value=${ approver.approverName } disabled/>
                                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:set var = "approverType" value ="${ approver.approverType }" />
								        <c:if test="${ approverType eq '결재' }">
								        
                                          <label>결재순번</label>
                                                 <input name="priority" value=${ approver.priority } disabled/>
                                        
								        </c:if>
								  </div>
                                </c:forEach>
								
								<div class="row">
                                    <div class="col-sm-12 text-center m-t-20">
                                        
                                        <button type="submit" class="btn btn-primary btn-lg" hide> 수정하기 </button>
                                        <button class="btn btn-primary btn-lg" onclick="location.href='${ pageContext.servletContext.contextPath }/approval/line/delete?no=${ requestScope.line.lineNo  }'">삭제하기</button>
                                        <button type="reset" class="btn btn-primary btn-lg" id="goBack">돌아가기</button>
                                    </div>
                                </div>	
								
							
						
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