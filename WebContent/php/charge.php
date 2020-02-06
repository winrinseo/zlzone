<?
 header("content-type:text/html; charset=utf-8");
include "dbconnect.php";

session_start();


$a = $_GET[point];
$id = $_SESSION[userid];
$level = $_SESSION[userlevel];

$sql9 = "select * from member where id= '$id'";
$result9 = mysql_query($sql9 , $connect);
$data9 = mysql_fetch_array($result9);

$last = $data9[lastp];
$lastp = $data9[charge];
$indate=date("d");
$indate1=date("d");


if($level == 1 ){
	if($a > 10000){
echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
	}
		if( $lastp > 10000){
echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
	}
else if ($lastp < 10001 && $level ==2){
$query10 = "UPDATE member SET charge = charge + '{$a}' where id = '$id'";
$result10 = mysql_query($query10 , $connect);

$sql12 = "select * from member where id= '$id'";
$result12 = mysql_query($sql12 , $connect);
$data12 = mysql_fetch_array($result12);




if($data12[charge] < 10001){
$query = "UPDATE member SET point = point+'{$a}' where id = '$id' " ;
	$result = mysql_query($query , $connect);
			$b = 10000;
			$c = $b - $data12[charge];
			$query112 = "UPDATE member SET pointlimit = '$c' where id = '$id'";
			$result222 = mysql_query($query112,$connect);
		echo "
		<script>
		window.alert('충전완료');
	 location.href='../html/home.html';
	 </script>
		 ";
}
else{
	$query10 = "UPDATE member SET charge = charge - '{$a}' where id = '$id'";
    $result10 = mysql_query($query10 , $connect);
	echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
}

}

}


if($level == 2 ){
	if($a > 50000){
echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
	}
		if( $lastp > 50000){
echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
	}
else if ($lastp < 50001 && $level ==2){
$query10 = "UPDATE member SET charge = charge + '{$a}' where id = '$id'";
$result10 = mysql_query($query10 , $connect);

$sql12 = "select * from member where id= '$id'";
$result12 = mysql_query($sql12 , $connect);
$data12 = mysql_fetch_array($result12);




if($data12[charge] < 50001){
$query = "UPDATE member SET point = point+'{$a}' where id = '$id' " ;
	$result = mysql_query($query , $connect);
			$b = 50000;
			$c = $b - $data12[charge];
			$query112 = "UPDATE member SET pointlimit = '$c' where id = '$id'";
			$result222 = mysql_query($query112,$connect);
		echo "
		<script>
		window.alert('충전완료');
	 location.href='../html/home.html';
	 </script>
		 ";
}
else{
	$query10 = "UPDATE member SET charge = charge - '{$a}' where id = '$id'";
    $result10 = mysql_query($query10 , $connect);
	echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
}

}

}



if($level == 3 ){
	if($a > 100000){
echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
	}
		if( $lastp > 100000){
echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
	}
else if ($lastp < 100001 && $level ==3){
$query10 = "UPDATE member SET charge = charge + '{$a}' where id = '$id'";
$result10 = mysql_query($query10 , $connect);

$sql12 = "select * from member where id= '$id'";
$result12 = mysql_query($sql12 , $connect);
$data12 = mysql_fetch_array($result12);




if($data12[charge] < 100001){
$query = "UPDATE member SET point = point+'{$a}' where id = '$id' " ;
	$result = mysql_query($query , $connect);
				$b = 100000;
			$c = $b - $data12[charge];
			$query112 = "UPDATE member SET pointlimit = '$c' where id = '$id'";
			$result222 = mysql_query($query112,$connect);


		echo "
		<script>
		window.alert('충전완료');
	 location.href='../html/home.html';
	 </script>
		 ";
}
else{
	$query10 = "UPDATE member SET charge = charge - '{$a}' where id = '$id'";
    $result10 = mysql_query($query10 , $connect);
	echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
}

}

}


if($level == 4 ){
	if($a > 1000000){
echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
	}
		if( $lastp > 1000000){
echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
	}
else if ($lastp < 1000001 && $level ==4){
$query10 = "UPDATE member SET charge = charge + '{$a}' where id = '$id'";
$result10 = mysql_query($query10 , $connect);

$sql12 = "select * from member where id= '$id'";
$result12 = mysql_query($sql12 , $connect);
$data12 = mysql_fetch_array($result12);




if($data12[charge] < 1000001){
$query = "UPDATE member SET point = point+'{$a}' where id = '$id' " ;
	$result = mysql_query($query , $connect);
			$b = 1000000;
			$c = $b - $data12[charge];
			$query112 = "UPDATE member SET pointlimit = '$c' where id = '$id'";
			$result222 = mysql_query($query112,$connect);
		echo "
		<script>
		window.alert('충전완료');
	 location.href='../html/home.html';
	 </script>
		 ";
}
else{
	$query10 = "UPDATE member SET charge = charge - '{$a}' where id = '$id'";
    $result10 = mysql_query($query10 , $connect);
	echo "		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
}

}

}






/*
if($level > 0)
{ 
	if($level == 1 && $a > 10000 || $last == $indate1)
		{
		echo "
		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
		}
		else if($level == 1 && $a < 10001 )
	{
		
			$query10 = "UPDATE member SET charge = charge + '{$a}' where id = '$id'";
			$result10 = mysql_query($query10 , $connect);
			$sql12 = "select * from member where id= '$id'";
			$result12 = mysql_query($sql12 , $connect);
			$data12 = mysql_fetch_array($result12);

		 if( $level ==1 && $data12[charge] > 10000){
		$query1 = "UPDATE member SET lastp = '$indate' where id = '$id'";
		$result = mysql_query($query1 , $connect);
		$query99 = "UPDATE member SET charge = 0 where id = '$id'";
		$result99 = mysql_query($query99 , $connect);
		 }
        $query = "UPDATE member SET point = point+'{$a}' where id = '$id' " ;
		
		$result = mysql_query($query , $connect);
		
		echo "
		<script>
		window.alert('충전완료');
	 location.href='../html/home.html';
	 </script>
		 ";

	}
		$sql12 = "select * from member where id= '$id'";
		$result12 = mysql_query($sql12 , $connect);
		$data12 = mysql_fetch_array($result12);

if($level == 2 && $data12[charge] > 50000 || $last == $indate1)
		{
		echo "
		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
		}

		else if($level == 2 && $a < 500001)
	{
		$query10 = "UPDATE member SET charge = charge + '{$a}' where id = '$id'";
		$result10 = mysql_query($query10 , $connect);
		$sql12 = "select * from member where id= '$id'";
		$result12 = mysql_query($sql12 , $connect);
		$data12 = mysql_fetch_array($result12);
		 if( $level ==2 && $data12[charge] > 49999){
		$query1 = "UPDATE member SET lastp = '$indate' where id = '$id'";
		$result = mysql_query($query1 , $connect);
		$query99 = "UPDATE member SET charge = 0 where id = '$id'";
		$result99 = mysql_query($query99 , $connect);
		 }
        $query = "UPDATE member SET point = point+'{$a}' where id = '$id' " ;
		
		$result = mysql_query($query , $connect);
		
		echo "
		<script>
		window.alert('충전완료');
	 location.href='../html/home.html';
	 </script>
		 ";
	}
	else if($level == 3 && $a > 100000 || $last == $indate1)
		{
		echo "
		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
		}
		else if($level == 3 && $a < 100001)
	{
		$query10 = "UPDATE member SET charge = charge + '{$a}' where id = '$id'";
		$result10 = mysql_query($query10 , $connect);
		$sql12 = "select * from member where id= '$id'";
		$result12 = mysql_query($sql12 , $connect);
		$data12 = mysql_fetch_array($result12);
		 if( $level ==3 && $data12[charge] > 100000){
		$query1 = "UPDATE member SET lastp = '$indate' where id = '$id'";
		$result = mysql_query($query1 , $connect);
		$query99 = "UPDATE member SET charge = 0 where id = '$id'";
		$result99 = mysql_query($query99 , $connect);
		 }
        $query = "UPDATE member SET point = point+'{$a}' where id = '$id' " ;
		
		$result = mysql_query($query , $connect);
		
		echo "
		<script>
		window.alert('충전완료');
	 location.href='../html/home.html';
	 </script>
		 ";
		
	}
	else if($level == 4 && $a > 1000000 || $last == $indate1)
		{
		echo "
		<script>
		window.alert('일일 충전한도초과입니다');
	    history.back(1);
	    </script>
		 ";
		 exit;
		}
		else if($level == 4 && $a < 1000001)
	{
		$query10 = "UPDATE member SET charge = charge + '{$a}' where id = '$id'";
			$result10 = mysql_query($query10 , $connect);
		 if( $level ==4 && $lastp == 1000000){
		$query1 = "UPDATE member SET lastp = '$indate' where id = '$id'";
		$result = mysql_query($query1 , $connect);
		$query99 = "UPDATE member SET charge = 0 where id = '$id'";
		$result99 = mysql_query($query99 , $connect);
		 }
        $query = "UPDATE member SET point = point+'{$a}' where id = '$id' " ;
		
		$result = mysql_query($query , $connect);
		
		echo "
		<script>
		window.alert('충전완료');
	 location.href='../html/home.html';
	 </script>
		 ";
	}
}

*/


			
			
			



$sql3 = "select * from member where id = '$id' ";
$result3 = mysql_query($sql3,$connect);
$row = mysql_fetch_array($result3);
setcookie("p" , $row['pointlimit'] , 0 , "/");
setcookie("b" , $row['point'] , 0 , "/");

mysql_close($connect);

?>
