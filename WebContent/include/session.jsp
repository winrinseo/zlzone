<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="com.ryan.dao.MemberDAO" %>
<%@ page import ="com.ryan.dto.*" %>
<%@ page import ="com.ryan.dao.BetListDAO" %>
<%
	// 세션값 받아오기 (object인 값을 강제로 문자열로 변환)
	String name = (String) session.getAttribute("name");
int point = (int) session.getAttribute("point");
int level = (int) session.getAttribute("level");
String id = (String) session.getAttribute("id");
String adm = (String) session.getAttribute("adm");
	if (name == null) {
%>

<!--  로그인 안됨-->
<script>
	alert('로그인을 하세요');
	history.back();
</script>

<%	} %>