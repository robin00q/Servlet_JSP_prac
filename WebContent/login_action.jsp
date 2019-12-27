<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="net.slipp.user.User"%>
<%@ page import="javax.servlet.RequestDispatcher"%>

<%@ page import="net.slipp.db.Database"%>
<%@ page import="net.slipp.user.UserNotFoundException"%>
<%@ page import="net.slipp.user.PasswordMismatchException"%>

<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");

	try{
		User.login(userId, password);
		session.setAttribute("userId", userId);
	
		response.sendRedirect("/");
	} catch (UserNotFoundException e) {
		request.setAttribute("errorMessage", "존재하지 않는 사용자입니다.");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");	
		rd.forward(request, response);
	} catch (PasswordMismatchException e) {
		request.setAttribute("errorMessage", "비밀번호가 틀립니다. 다시 로그인하세요.");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");	
		rd.forward(request, response);
	}
	
%>