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
					<div class="col-xs-8">
						<h4 class="page-title">Product Details</h4>
					</div>
					<div class="col-xs-4 text-right m-b-30">
						<a href="#" class="btn btn-primary rounded pull-right" data-toggle="modal" data-target="#add_leave">
							<i class="fa fa-plus"></i>근무추가하기
						</a>
					</div>
				</div>
				<div class="card-box">
					<div class="row">

						<div class="col-xs-12">
							<ul class="nav nav-tabs nav-tabs-bottom">
								<li class="active"><a href="#product_desc" data-toggle="tab">근무제 추가 설명</a></li>
								<li><a href="#product_reviews" data-toggle="tab">기본근태</a></li>
							</ul>
							
							<div class="tab-content">
								<div class="tab-pane active" id="product_desc">
									<div class="product-content">
										<p>설명설명</p>
										<blockquote>
											<p>설명설명</p>
										</blockquote>
										<p>설명설명</p>
									</div>
								</div>

								<div class="tab-pane" id="product_reviews">
									<div class="product-write-review">
										<h3>Write Review</h3>
										<form>
											<div class="row">
												<div class="col-sm-8">
													<div class="form-group">
														<label>근무제 명 <span class="text-red">*</span></label> 
														<input type="text" class="form-control">
													</div>

													<div class="form-group">
														<label>출퇴근 자율여부 (라디오버튼)<span class="text-red">*</span></label>
														<input type="email" class="form-control">
													</div>

													<div class="form-group col-sm-6">
														<label>월 출근</label> 
														<select class="select" name="monCheckIn" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select> 
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 퇴근 </label> 
														<select class="select" name="monCheckOut" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select>
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 출근</label> 
														<select class="select" name="monCheckIn" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select> 
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 퇴근 </label> 
														<select class="select" name="monCheckOut" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select>
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 출근</label> 
														<select class="select" name="monCheckIn" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select> 
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 퇴근 </label> 
														<select class="select" name="monCheckOut" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select>
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 출근</label> 
														<select class="select" name="monCheckIn" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select> 
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 퇴근 </label> 
														<select class="select" name="monCheckOut" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select>
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 출근</label> 
														<select class="select" name="monCheckIn" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select> 
													</div>
													
													<div class="form-group col-sm-6">
														<label>월 퇴근 </label> 
														<select class="select" name="monCheckOut" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select>
													</div>


													<br>
													
													<div class="form-group">
														<label>최소근로시간<span class="text-red">*</span></label>
														<input type="text" class="form-control">
													</div>
													
													<div class="form-group">
														<label>기본휴게시간</label> 
														<select class="select" name="monCheckOut" required>
															<option value="0">07</option>
															<option value="1">08</option>
															<option value="2">09</option>
															<option value="3">10</option>
															<option value="4">11</option>
														</select>
													</div>

													
													<div class="col-xs-4 text-right m-b-30">
														<a href="#" class="btn btn-danger rounded pull-right" data-toggle="modal" data-target="#delete_approve">
															<i class="fa fa-minus"></i> 삭제하기
														</a>
													</div>
													
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>


						</div>
					</div>


				</div>
			</div>

		</div>
		
		<!-- 모달 부분 -->
		 <div id="add_leave" class="modal custom-modal fade" role="dialog">
            <div class="modal-dialog">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="modal-content modal-md">
                    <div class="modal-header">
                        <h4 class="modal-title">Add Leave Request</h4>
                    </div>
                    <div class="modal-body">
						<form>
							<div class="form-group">
								<label>출퇴근자율여부 <span class="text-danger">*</span></label> <select id="workType"
									class="select" name="workType" onchange="testChange(this)">
									<option value=1>자율출퇴근 설정</option>
									<option value=2>출퇴근시간 설정</option>
								</select>
							</div>
							<div class="form-group col-md-6">
								<label>From <span class="text-danger">*</span></label>
								<div class="cal-icon">
									<input class="form-control datetimepicker" type="text">
								</div>
							</div>
							<div class="form-group col-md-6">
								<label>To <span class="text-danger">*</span></label>
								<div class="cal-icon">
									<input class="form-control datetimepicker" type="text">
								</div>
							</div>
							
							<div class="form-group">
								<label>Leave Reason <span class="text-danger">*</span></label>
								<textarea rows="4" cols="5" class="form-control"></textarea>
							</div>
							<div class="m-t-20 text-center">
								<button class="btn btn-primary btn-lg">Send Leave
									Request</button>
							</div>

							<div id="div1" hidden>
								<div class="form-group col-sm-6">
									<label>월 출근</label> <select class="select" name="monCheckIn"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 퇴근 </label> <select class="select" name="monCheckOut"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 출근</label> <select class="select" name="monCheckIn"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 퇴근 </label> <select class="select" name="monCheckOut"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 출근</label> <select class="select" name="monCheckIn"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 퇴근 </label> <select class="select" name="monCheckOut"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 출근</label> <select class="select" name="monCheckIn"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 퇴근 </label> <select class="select" name="monCheckOut"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 출근</label> <select class="select" name="monCheckIn"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>

								<div class="form-group col-sm-6">
									<label>월 퇴근 </label> <select class="select" name="monCheckOut"
										required>
										<option value="0">07</option>
										<option value="1">08</option>
										<option value="2">09</option>
										<option value="3">10</option>
										<option value="4">11</option>
									</select>
								</div>
							</div>

						</form>
					</div>
                </div>
            </div>
        </div>
        
        <!-- 삭제관련 모달부분 -->
         <div id="delete_approve" class="modal custom-modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content modal-md">
                    <div class="modal-header">
                        <h4 class="modal-title">Decline Leave Request</h4>
                    </div>
                    <form>
                        <div class="modal-body card-box">
                            <p>Are you sure want to declined this leave request?</p>
                            <div class="m-t-20 text-left">
                                <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                                <button type="submit" class="btn btn-danger">Decline</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

		<script>
			// onchange에서 호출하는 함수. 
			function testChange(obj) {
				var result = $('#workType option:selected').val();
				if (result == 2) {
					$('#div1').show();
				} else {
					$('#div1').hide();
				}
			}
			
		</script>
		
	</body>

</html>