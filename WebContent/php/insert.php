<?php
// 설정
session_start();
header("content-type:text/html; charset=utf-8");
$id = $_SESSION[username];
$title = $_POST[title];
$time = date("m/d H:i");
$board = $_POST[board];
$level = $_SESSION[userlevel];
if($name !=""){
$ext = array_pop(explode('.', $name));


}
include "dbconnect.php";

$sql = "insert into board set
name = '$id', 
title = '$title',
board = '$board',
time = '$time',
level = '$level'
";
mysql_query("set name utf8");
$result = mysql_query($sql, $connect);
if($result == true) {
	echo "<script>alert('등록완료'); location.href='../html/board.html';</script>";
} else {
	echo "<script>alert('등록실패!!');history.back();</script>";
}

mysql_close($connect);
?>