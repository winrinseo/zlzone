<?php
 header("content-type:text/html; charset=utf-8");
include "dbconnect.php";
$a = $_GET[num];
session_start();
$sql="delete  from board where num = '$a'";

if($result=mysql_query($sql , $connect) ) {
echo "<script>alert('삭제했습니다.');
	location.href='../html/board.html'; </script>";
}
else{
	echo "<script>alert('삭제실패!');
	history.back(); </script>";
}

?>
