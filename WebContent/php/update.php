<?php
session_start();
 header("content-type:text/html; charset=utf-8");
include "dbconnect.php";

$sql="update board set
board ='$_POST[board]',
title = '$_POST[title]'
where num = '$_POST[num]'
";

if($result=mysql_query($sql , $connect) ) {
echo "<script>alert('수정완료!');
	location.href='../html/board.html'; </script>";
}
else{
	echo "<script>alert('수정실패!');
	history.back(); </script>";
}

?>
