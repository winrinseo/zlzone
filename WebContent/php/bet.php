<?
 header("content-type:text/html; charset=utf-8");
session_start();

$id = $_SESSION[userid];
$time = $_POST[time];
$home = $_POST[home];
$homeb = $_POST[homeb];
$draw = $_POST[draw];
$awayb = $_POST[awayb];
$away = $_POST[away];
$bet = $_GET[bet];
$po = $_GET[num];
$hup = $_POST[hup];
$aup = $_POST[aup];



$doing = $_POST[doing];
$a = $myID;
$t = date("m/d H:i");




include "dbconnect.php";




$query2 = "select * from member where id= '$id'";  
$result2 = mysql_query($query2 , $connect);
$data2 = mysql_fetch_array($result2);  



if($data2[point] < $bet){
	echo "
		<script>
		window.alert('포인트가 부족하거나 잘못된 요청입니다.');
	 history.back(1);
	 </script>
		 ";
		 exit;
}
else {
	echo "
	<script>
	window.alert('배팅되었습니다');
	location.replace('../html/mybet.html');
	</script>
	";
	
}
if($po == 1) {
	$pointresult = $bet * $homeb;
$sql4 = "insert into bet(id , bet , time , home , homeb , darw , hup, aup,   away , awayb , result , point , pointresult , doing , bettime)
values('$id' , '$po' , '$time' ,  '$home' , '$homeb' ,   '$draw' , '$hup' , '$aup' ,  '$away' , '$awayb'  ,'$doing' , '$bet' , '$pointresult' , '$doing' , '$t' )"; 
mysql_query("set names utf8");
mysql_query($sql4, $connect);
}
if($po == 2) {
	$pointresult = $bet * $draw;
$sql4 = "insert into bet(id , bet , time , home , homeb , darw ,   hup , aup, away , awayb , result , point , pointresult , doing , bettime)
values('$id' , '$po' , '$time' ,  '$home' , '$homeb' ,   '$draw' , '$hup' , '$aup' , '$away' , '$awayb'  , '$doing' , '$bet' , '$pointresult' , '$doing', '$t')"; 
mysql_query("set names utf8");
mysql_query($sql4, $connect);
}
if($po == 3) {
	$pointresult = $bet * $awayb;
$sql4 = "insert into bet(id , bet , time , home , homeb , darw ,  hup, aup, away , awayb , result , point , pointresult , doing , bettime )
values('$id' , '$po' , '$time' ,  '$home' , '$homeb' ,   '$draw' , '$hup' , '$aup' , '$away' , '$awayb'  , '$doing' , '$bet' , '$pointresult' , '$doing' , '$t')"; 
mysql_query("set names utf8");
mysql_query($sql4, $connect);
}

$query = "UPDATE member SET point = point-'{$bet}' where id = '$id' " ;
$result = mysql_query($query , $connect);




$sql3 = "select * from member where id = '$id' ";
$result3 = mysql_query($sql3,$connect);
$row = mysql_fetch_array($result3);

setcookie("b" , $row['point'] , 0 , "/");



mysql_close($connect);
?>
