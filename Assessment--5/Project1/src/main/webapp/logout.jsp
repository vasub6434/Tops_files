<%
	session.removeAttribute("u");
	session.invalidate();
	request.setAttribute("msg", "user logout successfully");
	request.getRequestDispatcher("login.jsp").forward(request,response);
%>