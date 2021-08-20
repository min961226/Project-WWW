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
						<h4 class="page-title">결재라인 생성</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						

							<form class="form-horizontal" action="${ pageContext.servletContext.contextPath }/approval/line/insert" method="post"  onsubmit="return askAgain();">


								<div class="form-group">
									<div class="col-sm-6">
										<label>결재 유형</label>
										<div class="col-md-12">
											<select class="form-control" name="workType"  onchange="testChange(this)">
												<option value='업무'>업무</option>
												<option value='복지'>복지</option>
											</select>
										</div>
									</div>
									<div class="col-sm-6">
										<label>결재라인 명</label>
										<div class="col-md-12">
											<input type="text" name="lineName" class="form-control" required="required">
										</div>
									</div>
								</div>
								<!-- <div class="col-xs-12 text-right m-b-30">
						            <a class="btn btn-primary rounded pull-right" onclick="addApprover('alim');"><i class="fa fa-plus"></i>결재자 추가</a>
					            </div> -->
								  <div class="card-box col-lg-12">
								  <h4 align="center"> 1번 결재자</h4>
                                       <div class="form-group">
                                           <div class="col-xs-4">
                                           <label>역할종류</label>
                                                <select class="form-control" name="appType1" required="required">
								                    <option value="결재">결재</option>
							                    </select>
							                </div>
							                <div class="col-xs-4">
							               <label>결재자 부서 -직위 - 성명 </label>
								               <select id="selectJob" class="form-control" name="app1"required="required">
									               <option value="">-- 직위를 선택해주세요 --</option>
									               <c:forEach var="memberINF" items="${ requestScope.memberList }">
									                   <c:set var = "dp" value ="${ memberINF.department.deptCode }" />
									                   <c:if test="${  memberINF.memberNo ne requestScope.memberNo }">
									                   <option value="${ memberINF.memberNo}/${ memberINF.name}">${ memberINF.department.deptName } - ${ memberINF.job.jobName} - ${ memberINF.name}</option>
									                   </c:if>  
												   </c:forEach>
								               </select>
								            </div>
								            
								            <div class="col-xs-4">
							                <label>결재순위</label>
                                                 <input type="text" name="priority-1" class="form-control" value='1번째 결재자의 결재순위는 1입니다.' disabled/>
							          		</div>		                         
								  </div>
								  </div>
								  
								  <div class="card-box col-lg-12">
								  <h4 align="center"> 2번 결재자</h4>
                                       <div class="form-group">
                                           <div class="col-xs-4">
                                           <label>역할종류</label>
                                                <select id="workMethod" class="form-control" name="appType2" onchange="testChange1(this)">
								                    <option value="참조">참조</option>
								                    <option value="결재">결재</option>
							                    </select>
							                </div>
							                <div class="col-xs-4">
							               <label>결재자 부서 -직위 - 성명 </label>
								               <select id="selectJob" class="form-control" name="app2">
									               <option value="a0/z">-- 결재자를 선택해주세요 --</option>
									               <c:forEach var="memberINF" items="${ requestScope.memberList }">        
									                   <c:set var = "dp" value ="${ memberINF.department.deptCode }" />
									                   <c:if test="${  memberINF.memberNo ne requestScope.memberNo }">
									                   <option value="${ memberINF.memberNo}/${ memberINF.name}">${ memberINF.department.deptName } - ${ memberINF.job.jobName} - ${ memberINF.name}</option>
									                   </c:if>  
												   </c:forEach>
								               </select>
								            </div>
								            <div id="priority" class="col-xs-4" hidden>
							                <label>결재순위</label>
                                                 <input type="text" name="priority-2" class="form-control" value='결재순위는 박스순서대로 지정됩니다.' disabled/>
							          		</div>		                         
								  </div>
								  </div>
								  <div class="card-box col-lg-12">
								  <h4 align="center"> 3번 결재자</h4>
                                       <div class="form-group">
                                           <div class="col-xs-4">
                                           <label>역할종류</label>
                                                <select id="workMethod2" class="form-control" name="appType3" onchange="testChange2(this)">
								                    <option value="참조">참조</option>
								                    <option value="결재">결재</option>
							                    </select>
							                </div>
							                <div class="col-xs-4">
							               <label>결재자 부서 -직위 - 성명 </label>
								               <select id="selectJob" class="form-control" name="app3">
									               <option value="a1/z">-- 결재자를 선택해주세요 --</option>
									               <c:forEach var="memberINF" items="${ requestScope.memberList }">
									              
									                   <c:set var = "dp" value ="${ memberINF.department.deptCode }" />
									                   <c:if test="${  memberINF.memberNo ne requestScope.memberNo }">
									                   <option value="${ memberINF.memberNo}/${ memberINF.name}">${ memberINF.department.deptName } - ${ memberINF.job.jobName} - ${ memberINF.name}</option>
									                   </c:if>  
												   </c:forEach>
								               </select>
								            </div>
								            <div id="priority2" class="col-xs-4" hidden>
							                <label>결재순위</label>
                                                 <input type="text" name="priority-2" class="form-control" value='결재순위는 박스순서대로 지정됩니다.' disabled/>
							          		</div>		                         
								  </div>
								  </div>
								  <div class="card-box col-lg-12">
								  <h4 align="center"> 4번 결재자</h4>
                                       <div class="form-group">
                                           <div class="col-xs-4">
                                           <label>역할종류</label>
                                                <select id="workMethod3" class="form-control" name="appType4" onchange="testChange3(this)">
								                    <option value="참조">참조</option>
								                    <option value="결재">결재</option>
							                    </select>
							                </div>
							                <div class="col-xs-4">
							               <label>결재자 부서 -직위 - 성명 </label>
								               <select id="selectJob" class="form-control" name="app4">
									               <option value="a2/z">-- 결재자를 선택해주세요 --</option>
									               <c:forEach var="memberINF" items="${ requestScope.memberList }">
									                   <c:set var = "dp" value ="${ memberINF.department.deptCode }" />
									                   <c:if test="${  memberINF.memberNo ne requestScope.memberNo }">
									                   <option value="${ memberINF.memberNo}/${ memberINF.name}">${ memberINF.department.deptName } - ${ memberINF.job.jobName} - ${ memberINF.name}</option>
									                   </c:if>  
												   </c:forEach>
								               </select>
								            </div>
								            <div id="priority3" class="col-xs-4" hidden>
							                <label>결재순위</label>
                                                 <input type="text" name="priority-2" class="form-control" value='결재순위는 박스순서대로 지정됩니다.' disabled/>
							          		</div>		                         
								  </div>
								  </div>						
								<div class="row">
                                    <div class="col-sm-12 text-center m-t-20">
                                        <button type="submit" class="btn btn-primary btn-lg"> 생성 </button>
                                        <button type="reset" class="btn btn-primary btn-lg" id="goBack">취소</button>
                                    </div>
                                </div>
								<h5>***결재라인 생성 시 주의 사항 : <br>
								1. 동일한 결재자를 여러명 올릴 수 없습니다.<br>
								2. 전번 결재자를 지정하지않고 후번결재자만 선택할 시 다음결재자를 자동으로 전번결재자로 처리합니다.<br>
								(ex : 2번 결재자를 선택하지 않고 3번결재자만 넣고 라인 생성 시 3번 결재자를 2번 결재자로 처리)</h5>
							</form>
					</div>
				</div>
			</div>
			
		</div>

	</div>
	
	<script>
	    function addApprover() {
		    alert("asdf");
	    }
	    function testChange1(obj) {
		    var result = $('#workMethod option:selected').val();
		    if (result == '결재') {
		    	$('#priority').show();
	    	} else{
	     		$('#priority').hide();
	    	}
	    }
	    function testChange2(obj) {
		    var result = $('#workMethod2 option:selected').val();
		    if (result == '결재') {
		    	$('#priority2').show();
	    	} else{
	     		$('#priority2').hide();
	    	}
	    }
	    function testChange3(obj) {
		    var result = $('#workMethod3 option:selected').val();
		    if (result == '결재') {
		    	$('#priority3').show();
	    	} else{
	     		$('#priority3').hide();
	    	}
	    }
	    
	    
	    const $goBack = document.getElementById("goBack");
		$goBack.onclick = function() {
			if (!confirm("돌아가시겠습니까?\n작성중이던 모든 내용이 삭제됩니다.")) {
	        	
	        } else {
				location.href = "${ pageContext.servletContext.contextPath }/approval/line/select"
	        }
		}
		function askAgain(){
			
			var yn;
			yn = confirm('라인을 생성하시겠습니까?');
			
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