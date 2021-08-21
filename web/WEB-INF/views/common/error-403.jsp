<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<link href="https://fonts.googleapis.com/css?family=Fira+Sans:400,500,600,700" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/assets/img/favicon.png">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/assets/css/style.css">
	<title>Wonderful Welfare Workspace</title>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/jquery-3.2.1.min.js"></script>
</head>

<body>
    <div class="main-wrapper error-wrapper">
        <div class="error-box">
            <h1>403</h1>
            <h3><i class="fa fa-warning"></i> 접근 권한이 없습니다!</h3>
            <p>서버에서 이 리소스에 대한 액세스가 거부되었습니다.</p>
            <a href="${ pageContext.servletContext.contextPath }" class="btn btn-primary go-home">홈으로 돌아가기</a>
        </div>
    </div>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/js/bootstrap.min.js"></script>
</body>

</html>