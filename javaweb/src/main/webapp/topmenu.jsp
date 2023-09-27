<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
%>
<h1>Java Web Application </h1>  
<h2>
<a href="<%=contextPath %>">Home</a> |
<a href="<%=contextPath %>/basic/index.jsp">Basic</a> |
<a href="<%=contextPath %>/member-jsp/main.jsp">Member v.JSP</a> |
<a href="<%=contextPath %>/member-dao/main.jsp">Member v.DAO</a> |
<a href="<%=contextPath %>/member-servlet/main.jsp">Member v.Servlet</a> |
<a href="<%=contextPath %>/member-ajax/main.jsp">Member v.AJAX</a> |
<a href="<%=contextPath %>/member-mvc/main.do">Member v.MVC</a> |
<a href="<%=contextPath %>/member-mvc-jstl/main.do">Member v.MVC-JSTL</a> |

</h2>
<hr>