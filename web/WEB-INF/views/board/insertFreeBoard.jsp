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
                        <h4 class="page-title">Add Product</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <form>
                            <div class="form-group">
                                <label>Product ID</label>
                                <input class="form-control" type="text" value="PRO-0001" readonly="">
                            </div>
                            <div class="form-group">
                                <label>Product Name</label>
                                <input class="form-control" type="text">
                            </div>
                            <div class="form-group">
                                <label>Product Images</label>
                                <div>
                                    <input class="form-control" type="file">
                                    <small class="help-block">Max. file size: 50 MB. Allowed images: jpg, gif, png. Maximum 10 images only.</small>
                                </div>
                                <div class="row">
                                    <div class="col-md-4 col-sm-2 col-xs-4 col-lg-2">
                                        <div class="thumbnail product-thumbnail">
                                            <div class="thumb">
                                                <img src="assets/img/product/product-thumb-01.jpg" class="img-responsive" alt="">
                                            </div>
                                            <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-2 col-xs-4 col-lg-2">
                                        <div class="thumbnail product-thumbnail">
                                            <div class="thumb">
                                                <img src="assets/img/placeholder-thumb.jpg" class="img-responsive" alt="">
                                            </div>
                                            <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-2 col-xs-4 col-lg-2">
                                        <div class="thumbnail product-thumbnail">
                                            <div class="thumb">
                                                <img src="assets/img/placeholder-thumb.jpg" class="img-responsive" alt="">
                                            </div>
                                            <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-2 col-xs-4 col-lg-2">
                                        <div class="thumbnail product-thumbnail">
                                            <div class="thumb">
                                                <img src="assets/img/placeholder-thumb.jpg" class="img-responsive" alt="">
                                            </div>
                                            <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-2 col-xs-4 col-lg-2">
                                        <div class="thumbnail product-thumbnail">
                                            <div class="thumb">
                                                <img src="assets/img/placeholder-thumb.jpg" class="img-responsive" alt="">
                                            </div>
                                            <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-2 col-xs-4 col-lg-2">
                                        <div class="thumbnail product-thumbnail">
                                            <div class="thumb">
                                                <img src="assets/img/placeholder-thumb.jpg" class="img-responsive" alt="">
                                            </div>
                                            <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Product Category</label>
                                        <select class="select">
                                            <option>Select</option>
                                            <option>Electronics</option>
                                            <option>Fashion</option>
                                            <option>Books</option>
                                            <option>Toys</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Product Sub Category</label>
                                        <select class="select">
                                            <option>Select</option>
                                            <option>Televisions</option>
                                            <option>Headphones</option>
                                            <option>Clothing</option>
                                            <option>Shoes</option>
                                            <option>Toys & Games</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Quantity</label>
                                        <input class="form-control" type="text">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Price</label>
                                        <input class="form-control" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Product Description</label>
                                <textarea cols="30" rows="6" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Product Tags <small>(separated with a comma)</small></label>
                                <input type="text" placeholder="Enter your tags" data-role="tagsinput" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="display-block">Product Status</label>
                                <label class="radio-inline">
                                    <input name="status" checked="checked" type="radio"> Active
                                </label>
                                <label class="radio-inline">
                                    <input name="status" type="radio"> Inactive
                                </label>
                            </div>
                            <div class="m-t-20 text-center">
                                <button class="btn btn-primary btn-lg">Publish Product</button>
                            </div>
                        </form>
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
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">R</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author">Richard Miles </span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item new-message">
                                        <div class="list-left">
                                            <span class="avatar">J</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author">John Doe</span>
                                            <span class="message-time">1 Aug</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">T</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author"> Tarah Shropshire </span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">M</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author">Mike Litorus</span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">C</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author"> Catherine Manseau </span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">D</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author"> Domenic Houston </span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">B</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author"> Buster Wigton </span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">R</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author"> Rolland Webber </span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">C</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author"> Claire Mapes </span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">M</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author">Melita Faucher</span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">J</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author">Jeffery Lalor</span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">L</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author">Loren Gatlin</span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="chat.html">
                                    <div class="list-item">
                                        <div class="list-left">
                                            <span class="avatar">T</span>
                                        </div>
                                        <div class="list-body">
                                            <span class="message-author">Tarah Shropshire</span>
                                            <span class="message-time">12:28 AM</span>
                                            <div class="clearfix"></div>
                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="topnav-dropdown-footer">
                        <a href="chat.html">See all messages</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
        
        
    </div>
</body>

</html>