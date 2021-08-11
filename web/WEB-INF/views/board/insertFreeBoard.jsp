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
                        <h4 class="page-title">게시물 작성</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <form>
                             <div class="form-group">
                                <label>글제목</label>
                                <input class="form-control" type="text" value= "${memberNo}">
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" type="text" value= "${deptName}">
                            </div>
                            <div class="form-group">
                                <label>글내용</label>
                                <input class="form-control" type="text" value= "${jobName}">
                            </div>  
                            <div class="form-group">
                                <label>파일첨부</label>
                                <div>
                                    <input class="form-control" type="file">
                                    <small class="help-block">파일 최대 사이즈: 50 MB. 허용된 확장자: jpg, gif, png. </small>
                                </div>
                                
                            </div>
							<div align="center">
								<button type="reset" id="cancleFree">취소하기</button>
								<button type="submit">등록하기</button>
							</div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>