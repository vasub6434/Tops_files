<%@page import="com.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User u=null;
	if(session!=null)
	{
		if(session.getAttribute("u")!=null)
		{
			u=(User)session.getAttribute("u");
		}
	}
 %>
<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>Karma Shop</title>
	<!--
		CSS
		============================================= -->
	<link rel="stylesheet" href="css/linearicons.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/themify-icons.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/owl.carousel.css">
	<link rel="stylesheet" href="css/nice-select.css">
	<link rel="stylesheet" href="css/nouislider.min.css">
	<link rel="stylesheet" href="css/ion.rangeSlider.css" />
	<link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css" />
	<link rel="stylesheet" href="css/magnific-popup.css">
	<link rel="stylesheet" href="css/main.css">
</head>

<body>

	<!-- Start Header Area -->
	<header class="header_area sticky-header">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light main_box">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="index.jsp"><img src="img/logo.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto">
							<li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
							<li class="nav-item"><a class="nav-link" href="product.jsp">Product</a></li>
							
							<li class="nav-item submenu dropdown">
								<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
								 aria-expanded="false">Blog</a>
								 <%
								 	if(u==null)
								 	{
								 		
								 	
								 %>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link" href="signup.jsp">Sign up</a></li>
									
								</ul>
							</li>
							<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
							<%
								 	}
								 	else
								 	{
							%>
							<li style="margin-right : 20px;margin-top : 10px;"><a href="changepassword.jsp">change password</a></li>
							<li style="margin-right : 20px;margin-top : 10px;"><a href="wishlist.jsp" >Wishlist(<%=session.getAttribute("wishlist_count") %>)</a></li>
							<li style="margin-top : 10px;"><a href="logout.jsp">Logout(<%=u.getFname() %>)</a></li>
							<li style="margin-down : 40px; margin-left : 50px;"><a href="profile.jsp"><img src="profile_picture/<%=u.getProfile_picture() %>" style="width : 50px;height : 50px;border-radius : 50%;"></a></li>
							<%
								 	}
							%>
							
							
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="nav-item"><a href="cart.jsp" class="cart"><span><%=session.getAttribute("cart_count") %> </span></a></li>
							<li class="nav-item">
								<button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		
	</header>
	<!-- End Header Area -->

</body>

</html>