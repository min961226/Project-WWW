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
		<jsp:include page="../common/navbar.jsp"/>
         <div class="page-wrapper">
            <div class="content container-fluid">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h4 class="page-title">경조사 신청서</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <form>
                            <div class="form-group">
                                <label>직원 ID</label>
                                 <input class="form-control" type="text" value= "${memberNo}" readonly="readonly"/>
                            </div>
                            <div class="form-group">
                                <label>부서</label>
                                <input class="form-control" type="text">
                            </div>
                            <div class="form-group">
                                <label>직위</label>
                                <input class="form-control" type="text">
                            </div>
                            <div class="form-group">
                                <label>신청자</label>
                                <input class="form-control" type="text">
                            </div>
                            <div class="form-group">
                                <label>회의실명</label>
                                <input class="form-control" type="text">
                            </div>
                            <div class="form-group">
                                <label>이용인원</label>
                                <input class="form-control" type="text">
                            </div>
                            <div class="form-group">
                                <label>이용 시간</label>
                                <select class="select">
                                    <option>Select</option>
                                    <option>2014.07.28 (목) 09:00</option>
                                    <option>Fashion</option>
                                    <option>Books</option>
                                    <option>Toys</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>이용 종료 시간</label>
                                <select class="select">
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
                            
                            <div class="form-group">
                                <label>파일첨부</label>
                                <div>
                                    <input class="form-control" type="file">
                                    <small class="help-block">파일 최대 사이즈: 50 MB. 허용된 확장자: jpg, gif, png. </small>
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
</body>

</html>