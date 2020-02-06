<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/session.jsp" %>
<%
	if (adm.equals("")) {
%>

<!--  로그인 안됨-->
<script>
	alert('허가되지않은 사용자입니다.');
	history.back();
</script>

<%	} %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>	ZI-ZONE </title>


<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/button.css?3" > 




</head> 


<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" oncontextmenu="return false" ondragstart="return false" onselectstart="return false">






<style>
a:link    {color:#000000;text-decoration:none;}
a:visited {color:#000000;text-decoration:none;}
a:active  {color:#000000;text-decoration:none;}
a:hover  {color:#000000;text-decoration:none;}

#join-btn { width: 60px; height: 22px; line-height: 25px; text-align:center;  text-decoration:none; }
#join-btn:hover {  bottom: 39px !important; top:inherit; opacity:1.0;}
</style>

<%@ include file="../admin_inc/admin_menu.jsp" %>
<%@ include file="../include/body_footer.jsp" %>

