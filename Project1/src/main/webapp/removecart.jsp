<%@page import="com.bean.Cart"%>
<%@page import="com.dao.CartDao"%>
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
	CartDao.removeCart(c);
	List<Cart> list=CartDao.getCartByUser(u.getId());
	session.setAttribute("cart_count", list.size());
	response.sendRedirect("index.jsp");
%>
