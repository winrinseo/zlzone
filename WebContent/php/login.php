<?
include "dbconnect.php";
session_start();

$id = $_POST['id'];
$pw = md5($_POST['pw']);
$indate=date("d");
$indate1=date("d");


//로그인//
$query = "select * from member where id= '$id'";
$result = mysql_query($query , $connect);
$data = mysql_fetch_array($result);

//관리자승인//
$query1 = "select * from member where id= '$id'";  
$result1 = mysql_query($query1 , $connect);
$data1 = mysql_fetch_array($result1);  


//중복로그인//
$query2 = "select * from member where id= '$id'";  
$result2 = mysql_query($query2 , $connect);
$data2 = mysql_fetch_array($result2);  


if($data[id]!=$id){
	echo "
		<script>
		window.alert('아이디가 존재하지 않습니다.');
	 history.back(1);
	 </script>
		 ";
		 exit;
}


if($data[pw]!= $pw){
	echo "
		<script>
		window.alert('비밀번호가 틀렸습니다.');
	 history.back(1);
	 </script>
		 ";
		 exit;
}
if($data1[ok] == 0){
	echo "
		<script>
		window.alert('관리자 승인이 되지않았습니다.');
	 history.back(1);
	 </script>
		 ";
		 exit;
}

$sql4= "select last from member where id = '$id'";
$result5 = mysql_query($sql4 , $connect);
$data3 = mysql_fetch_array($result5);


if($data3[last] != $indate1){
	$sql6= "update  member set last = '$indate' where id = '$id'";
	$result5 = mysql_query($sql6 , $connect);
	$query99 = "UPDATE member SET charge = 0 where id = '$id'";
	$result99 = mysql_query($query99 , $connect);

if($data2[level] == 1){
	echo"
	<script>
	window.alert('레벨1님 일일 포인트 지급완료');
	</script>";
	$sql7= "update member set point = point+500 where id = '$id' ";
	$result6 = mysql_query($sql7 , $connect);
	$query99 = "UPDATE member SET charge = 0 where id = '$id'";
	$result99 = mysql_query($query99 , $connect);
	$sql12 = "select * from member where id= '$id'";
    $result12 = mysql_query($sql12 , $connect);
    $data12 = mysql_fetch_array($result12);
	$b = 10000;
	$c = $b - $data12[charge];
	$query112 = "UPDATE member SET pointlimit = '$c' where id = '$id'";
	$result222 = mysql_query($query112,$connect);
}

else if($data2[level] == 2){
	echo"
	<script>
	window.alert('레벨2님 일일 포인트 지급완료');
	</script>";
	$sql7= "update member set point = point+1000 where id = '$id' ";
	$result6 = mysql_query($sql7 , $connect);
	$query99 = "UPDATE member SET charge = 0 where id = '$id'";
	$result99 = mysql_query($query99 , $connect);
		$sql12 = "select * from member where id= '$id'";
    $result12 = mysql_query($sql12 , $connect);
    $data12 = mysql_fetch_array($result12);
	$b = 50000;
	$c = $b - $data12[charge];
	$query112 = "UPDATE member SET pointlimit = '$c' where id = '$id'";
	$result222 = mysql_query($query112,$connect);
}
else if($data2[level] == 3){
	echo"
	<script>
	window.alert('레벨2님 일일 포인트 지급완료');
	</script>";
	$sql7= "update member set point = point+1500 where id = '$id' ";
	$result6 = mysql_query($sql7 , $connect);
	$query99 = "UPDATE member SET charge = 0 where id = '$id'";
	$result99 = mysql_query($query99 , $connect);
	$sql12 = "select * from member where id= '$id'";
    $result12 = mysql_query($sql12 , $connect);
    $data12 = mysql_fetch_array($result12);
	$b = 100000;
	$c = $b - $data12[charge];
	$query112 = "UPDATE member SET pointlimit = '$c' where id = '$id'";
	$result222 = mysql_query($query112,$connect);
}
else if($data2[level] == 4){
	echo"
	<script>
	window.alert('VIP님 일일 포인트 지급완료');
	</script>";
	$sql7= "update member set point = point+2000 where id = '$id' ";
	$result6 = mysql_query($sql7 , $connect);
	$query99 = "UPDATE member SET charge = 0 where id = '$id'";
	$result99 = mysql_query($query99 , $connect);
	$sql12 = "select * from member where id= '$id'";
    $result12 = mysql_query($sql12 , $connect);
    $data12 = mysql_fetch_array($result12);
	$b = 1000000;
	$c = $b - $data12[charge];
	$query112 = "UPDATE member SET pointlimit = '$c' where id = '$id'";
	$result222 = mysql_query($query112,$connect);
}


}


mysql_set_charset("utf8" , $connect);
$sql3 = "select * from member where id = '$id' ";
$result3 = mysql_query($sql3,$connect);
$row = mysql_fetch_array($result3);


$_SESSION[userid] = $row['id'];
$_SESSION[username] = $row['name'];
$_SESSION[point] = $row['point'];
$_SESSION[userlevel] = $row['level'];

/*
setcookie("dothome" , $id , 0 , "/");

setcookie("point" , $point , 0 , "/"); 
setcookie("a" , $row['name'] , 0 , "/");
setcookie("b" , $row['point'] , 0 , "/");
setcookie("c" , $row['level'] , 0 , "/");
setcookie("d" , $row['id'] , 0 , "/");
setcookie("p" , $row['pointlimit'] , 0 , "/");
*/

mysql_close($connect);


?>
<script>
location.href="../html/home.html"
</script>