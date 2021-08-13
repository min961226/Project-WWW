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
                        <h4 class="page-title">자유게시판 상세보기</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">                        
                                   			<table align="center">
				<tr>
					<td>제목 </td>
					<td colspan="3"><p><c:out value="${ requestScope.board.title }"/></p></td>
				</tr>
				<tr>
					<td>작성자 </td>
					<td><p><c:out value="${ requestScope.board.count }"/></p></td>
					<td>작성일</td>
					<td><p><c:out value="${ requestScope.board.created }"/></p></td>
				</tr>
				<tr>
					<td>내용 </td>
					<td colspan="3">
						<textarea style="resize:none; width:90%; height:200px;" readonly><c:out value="${ requestScope.board.body }"/></textarea>
					</td>
				</tr>
			</table>
			
			
                            
                            
                            
				<div align="center">
				<button onclick="location.href='${ pageContext.servletContext.contextPath }/board/free/select'">메뉴로 돌아가기</button>
							</div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    	<script>
		if(document.getElementsByTagName("td")) {
			const $tds = document.getElementsByTagName("td");
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onmouseenter = function() {
					this.parentNode.style.backgroundColor = "orangered";
					this.parentNode.style.cursor = "pointer";
				}
				
				$tds[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}
				
				$tds[i].onclick = function() {
					/* 게시물 번호까지 알아왔으니 이제 상세보기는 할 수 있겠지? */
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/board/free/selectOne?no=" + no;
				}
				
			}
			
		}
		
		/* 제이쿼리 이용하는 경우 */
		/* $(function() {
			$("#listArea td").hover(function() {
				$(this).parent().css({"background":"orangered", "cursor":"pointer"});
			}, function() {
				$(this).parent().css({"background":"black"});
			}).click(function() {
				const no = $(this).parent().children(":eq(0)").text();
				location.href = "${ pageContext.servletContext.contextPath }/notice/detail?no=" + no;
			});
		}); */
	</script>
</body>

</html>