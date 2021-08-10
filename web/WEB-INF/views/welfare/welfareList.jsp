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
						<h4 class="page-title">Basic Inputs</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="card-box">
							<h4 class="card-title">Basic Inputs</h4>
							<form class="form-horizontal" action="#">
								<div class="form-group">
									<label class="control-label col-lg-2">Text Input</label>
									<div class="col-md-10">
										<input type="text" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Password</label>
									<div class="col-md-10">
										<input type="password" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Disabled Input</label>
									<div class="col-md-10">
										<input type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Readonly Input</label>
									<div class="col-md-10">
										<input type="text" class="form-control" value="readonly"
											readonly="readonly">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Placeholder</label>
									<div class="col-md-10">
										<input type="text" class="form-control"
											placeholder="Placeholder">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">File input</label>
									<div class="col-lg-10">
										<input class="form-control" type="file">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Default select</label>
									<div class="col-lg-10">
										<select class="form-control">

											<c:forEach items="${ welfareList }" var="welfare">
												<option><c:out value="${ welfare.name }" /></option>
											</c:forEach>
											<option>-- Select --</option>
											<option>Option 1</option>
											<option>Option 2</option>
											<option>Option 3</option>
											<option>Option 4</option>
											<option>Option 5</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Radio</label>
									<div class="col-lg-10">
										<div class="radio">
											<label> <input type="radio" name="radio">
												Option 1
											</label>
										</div>
										<div class="radio">
											<label> <input type="radio" name="radio">
												Option 2
											</label>
										</div>
										<div class="radio">
											<label> <input type="radio" name="radio">
												Option 3
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Checkbox</label>
									<div class="col-lg-10">
										<div class="checkbox">
											<label> <input type="checkbox" name="checkbox">
												Option 1
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="checkbox">
												Option 2
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="checkbox">
												Option 3
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Textarea</label>
									<div class="col-lg-10">
										<textarea rows="5" cols="5" class="form-control"
											placeholder="Enter text here"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Input Addons</label>
									<div class="col-lg-10">
										<div class="input-group">
											<span class="input-group-addon">$</span> <input
												class="form-control" type="text"> <span
												class="input-group-btn">
												<button class="btn btn-primary" type="button">Button</button>
											</span>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="card-box">
							<h4 class="card-title">Input Sizes</h4>
							<form class="form-horizontal" action="#">
								<div class="form-group">
									<label class="control-label col-lg-2">Large Input</label>
									<div class="col-md-10">
										<input type="text" class="form-control input-lg"
											placeholder=".input-lg">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Default Input</label>
									<div class="col-md-10">
										<input type="text" class="form-control"
											placeholder=".form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Small Input</label>
									<div class="col-md-10">
										<input type="text" class="form-control input-sm"
											placeholder=".input-sm">
									</div>
								</div>
							</form>
						</div>
						<div class="card-box">
							<h4 class="card-title">Validation Inputs</h4>
							<form class="form-horizontal" action="#">
								<div class="form-group has-warning has-feedback">
									<label class="control-label col-lg-2">Warning Input</label>
									<div class="col-lg-10">
										<input class="form-control form-control-warning"
											placeholder=".has-warning" type="text"> <span
											class="help-block">Warning input helper</span>
									</div>
								</div>
								<div class="form-group has-success has-feedback">
									<label class="control-label col-lg-2">Success Input</label>
									<div class="col-lg-10">
										<input class="form-control form-control-success"
											placeholder=".has-success" type="text"> <span
											class="help-block">Success input helper</span>
									</div>
								</div>

								<div class="form-group has-error has-feedback">
									<label class="control-label col-lg-2">Error Input</label>
									<div class="col-lg-10">
										<input class="form-control form-control-danger"
											placeholder=".has-error" type="text"> <span
											class="help-block">Error input helper</span>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="notification-box">
				<div class="msg-sidebar notifications msg-noti">
					<div class="topnav-dropdown-header">
						<span>Messages</span>
					</div>
					<div class="drop-scroll msg-list-scroll">
						<ul class="list-box">
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">R</span>
										</div>
										<div class="list-body">
											<span class="message-author">Richard Miles </span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item new-message">
										<div class="list-left">
											<span class="avatar">J</span>
										</div>
										<div class="list-body">
											<span class="message-author">John Doe</span> <span
												class="message-time">1 Aug</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">T</span>
										</div>
										<div class="list-body">
											<span class="message-author"> Tarah Shropshire </span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">M</span>
										</div>
										<div class="list-body">
											<span class="message-author">Mike Litorus</span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">C</span>
										</div>
										<div class="list-body">
											<span class="message-author"> Catherine Manseau </span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">D</span>
										</div>
										<div class="list-body">
											<span class="message-author"> Domenic Houston </span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">B</span>
										</div>
										<div class="list-body">
											<span class="message-author"> Buster Wigton </span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">R</span>
										</div>
										<div class="list-body">
											<span class="message-author"> Rolland Webber </span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">C</span>
										</div>
										<div class="list-body">
											<span class="message-author"> Claire Mapes </span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">M</span>
										</div>
										<div class="list-body">
											<span class="message-author">Melita Faucher</span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">J</span>
										</div>
										<div class="list-body">
											<span class="message-author">Jeffery Lalor</span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">L</span>
										</div>
										<div class="list-body">
											<span class="message-author">Loren Gatlin</span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
							<li><a href="chat.html">
									<div class="list-item">
										<div class="list-left">
											<span class="avatar">T</span>
										</div>
										<div class="list-body">
											<span class="message-author">Tarah Shropshire</span> <span
												class="message-time">12:28 AM</span>
											<div class="clearfix"></div>
											<span class="message-content">Lorem ipsum dolor sit
												amet, consectetur adipiscing</span>
										</div>
									</div>
							</a></li>
						</ul>
					</div>
					<div class="topnav-dropdown-footer">
						<a href="chat.html">See all messages</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>