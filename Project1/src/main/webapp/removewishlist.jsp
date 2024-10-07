<%@page import="java.util.List"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%@ include file="header.jsp" %>

<%
	int pid=Integer.parseInt(request.getParameter("pid"));
	int id=u.getId();
	Wishlist w=new Wishlist();
	w.setPid(pid);
	w.setId(id);
	WishlistDao.removeWishlist(w);
	List<Wishlist> list=WishlistDao.getWishlistByUser(u.getId());
	session.setAttribute("wishlist_count", list.size());
	
	response.sendRedirect("index.jsp");
%>
