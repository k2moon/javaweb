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
<h1>Member Service v.Paging</h1>
<h2>
<a href="">Main</a> |
<a href="memberList.jsp">회원목록 - Paging</a> |

</h2>
<hr>