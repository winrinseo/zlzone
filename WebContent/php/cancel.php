<?php
 header("content-type:text/html; charset=utf-8");
session_start();
include "dbconnect.php";

$sql1 = "select * from bet where num = '$_GET[num]'";
$result1 = mysql_query($sql1,$connect);
$data = mysql_fetch_array($result1);

$indate=date("m.d H:i");
$time = $data[time];

$point = $data[point];
$id = $data[id];
if($indate > $time) {
echo "
		<script>
		window.alert('경기가 진행중입니다.');
	 history.back(1);
	 </script>
		 ";
		 exit;
}


$sql2 = "UPDATE member SET point = point+'{$point}' where id = '$id' " ;
$result2 = mysql_query($sql2 , $connect);


$sql="delete  from bet where num = '$_GET[num]'";

if($result=mysql_query($sql , $connect) ) {
echo "<script>alert('배팅 취소했습니다.');
	location.href='../html/mybet.html'; </script>";
}
else{
	echo "<script>alert('삭제실패!');
	history.back(); </script>";
}

$sql3 = "select * from member where id = '$id' "; 
$result3 = mysql_query($sql3,$connect);
$row = mysql_fetch_array($result3);
setcookie("b" , $row['point'] , 0 , "/");
?>