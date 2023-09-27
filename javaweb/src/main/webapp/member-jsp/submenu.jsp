<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	function deleteConfirm() {
		const input = confirm("회원을 탈퇴할까요?");
		alert(input);
		if (input) location.href = "deleteProc.jsp";
		else return;
	};
	
</script>
<%@include file="../topmenu.jsp" %>
<h1>Member Service v.JSP</h1>
<h2>
<a href="">Main</a> |
<a href="memberList.jsp">회원목록</a> |

<% if (session.getAttribute("id") == null) { %>
	<a href="join.jsp">회원가입</a> |
	<a href="login.jsp">로그인</a> |
	
<% } else { %>	
	<a href="update.jsp">정보수정</a> |
	<a href="#" onclick="deleteConfirm();">회원탈퇴</a> |
	<a href="logout.jsp">로그아웃</a> | <br>
	<%=session.getAttribute("name") %>(<%=session.getAttribute("id") %>) 로그인 중
<% } %>
</h2>
<hr>