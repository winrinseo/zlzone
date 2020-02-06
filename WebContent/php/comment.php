<?php

session_start();
include "dbconnect.php";
/*if($_SESSION[ss_id]==""){
	echo "<script>alert('로그인 후 들어오세요');location.href='login.php';</script>";
	exit;
}*/


$level=$_SESSION[userlevel];
$id = $_SESSION[userid];
$time = date("m/d H:i");
$sql = "insert into comment set 
parent_num = '{$_POST[num]}',
content='{$_POST[content]}',
indate='$time',
id = '$id',
level = '$level'
";
if($result= mysql_query($sql, $connect)) {
	echo "<script>location.href='../html/view.html?num={$_POST[num]}';</script>";
}else{
	echo "<script>alert('등록실패');history.back();</script>";
}
?>