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

<iframe name="exeFrame" width="0" height="0" frameborder="0"></iframe>
<table class="result" width="100%" border="0" cellspacing="0" cellpadding="0">
          	  <tbody><tr>
            	<td align="center" class="sub_back">
                    <table width="1147" border="0" cellspacing="0" cellpadding="0">
                        <tbody><tr>
                            <td valign="top">
<table width="1134" border="0" cellspacing="0" cellpadding="0" align="center">
  <tbody><tr>
    <td height="38" align="left" valign="bottom">
	</td>
		</td>
	<td align="left" ><h1 style="padding-left:42%; padding-top:20px;">경기등록</h1></td>
  </tr>
 </tbody>
</table>


 <table class="result1" width="100%" border="0" cellspacing="0" cellpadding="0">
          	  <tbody><tr>
            	<td align="center" class="sub_back">
                    <table width="1147" border="0" cellspacing="0" cellpadding="0">
                        <tbody><tr>
                            <td valign="top">

<table width="1150" border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td height="38" align="left" valign="bottom">
	</td>

  </tr>
 </tbody>
</table>
<form action="betbet_ok.ad" method="post">
<table width="1134" border="0" cellspacing="0" cellpadding="0" class="boardWrite" style="margin-top:15px;" align="center">
	<tbody><tr>
		<th width="100">경기 1팀</th>
		<th width="100" class="title"><input type="text" name="fight1" id="title" class="input6"></th>
		<th width="100">경기 2팀</th>
		<th width="100" class="title"><input type="text" name="fight2" id="title" class="input6"></th>
	</tr>
	<th width="100"></th>
	<tr height="80px">
		<th width="100">경기시간</th>
		<td width="100" class="title">년<input type="text" name="years" id="years" class="in"></td>
		<td width="100" class="title">월<input type="text" name="months" id="months" class="in"></td>
		<td width="100" class="title">일<input type="text" name="days" id="days" class="in"></td>
		<td width="100" class="title">시<input type="text" name="times" id="times" class="in"></td>
	</tr>
	<tr>
		
	</tr>


</tbody></table>
<table align="center">
<td>
<button id="join1-btn"  type="submit" value="1" border="0" class="ui_btn_red" >등록</td></td>
</table>						  
 </form>

<%@ include file="../include/body_footer.jsp" %>

