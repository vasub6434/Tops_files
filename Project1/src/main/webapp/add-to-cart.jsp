<%@page import="com.dao.CartDao"%>
<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.Product"%>
<%@page import="com.bean.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%@ include file="header.jsp" %>

<%
	int pid=Integer.parseInt(request.getParameter("pid"));
	int id=u.getId();
	Cart c=new Cart();
	c.setPid(pid);
	c.setId(id);
	Product p=ProductDao.getProduct(pid);
	c.setProduct_price(p.getProduct_price());
	c.setProduct_qty(1);
	c.setTotal_price(p.getProduct_price());
	CartDao.AddtoCart(c);
	List<Cart> list=CartDao.getCartByUser(u.getId());
	session.setAttribute("cart_count", list.size());
	response.sendRedirect("cart.jsp");
%>
