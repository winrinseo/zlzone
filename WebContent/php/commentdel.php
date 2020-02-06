<?php
 header("content-type:text/html; charset=utf-8");
include "dbconnect.php";

session_start();
$sql="delete  from comment where num = '$_GET[num]'";

if($result=mysql_query($sql , $connect) ) {
echo "<script>alert('삭제완료!');
	location.href='../html/board.html'; </script>";
}
else{
	echo "<script>alert('삭제실패!');
	history.back(); </script>";
}

?>
