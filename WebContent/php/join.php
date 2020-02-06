<?
 header("content-type:text/html; charset=utf-8");
session_start();
include "dbconnect.php";

$id=$_POST['id'];
$pw=md5($_POST['pw']);
$pw2=md5($_POST['pw2']);
$name=$_POST['name'];
$phone=$_POST['phone'];
$day=$_POST['day'];
$point = 1500;

$query1 = "select * from member where id= '$id'";
mysql_query("set name utf8");
$result1 = mysql_query($query1 , $connect);
$data = mysql_fetch_array($result1);

$query12 = "select * from member where name= '$name'";
mysql_query("set name utf8");
$result12 = mysql_query($query12 , $connect);
$data12 = mysql_fetch_array($result12);


if($data[id] ==$id){
	echo "
		<script>
		window.alert('사용중인 아이디입니다.');
	 history.back(1);
	 </script>
		 ";
		 exit;
}
if($data12[name] ==$name){
	echo "
		<script>
		window.alert('사용중인 닉네임입니다.');
	 history.back(1);
	 </script>
		 ";
		 exit;
}

if($pw != $pw2 ){
	echo "
		<script>
		window.alert('비밀번호와 비밀번호확인 다릅니다.');
	 history.back(1);
	 </script>
		 ";
		 exit;
}

else
{ echo "
<script>
window.alert('회원가입 완료 되었습니다.');
location.replace('../index.html')
</script>
";
}

$query = "insert into member(id , pw , name , phone   , day , point)
values('$id' , '$pw' ,  '$name' , '$phone' ,   '$day' , '$point' )"; 
mysql_query("set name utf8");
mysql_query($query, $connect);



mysql_close($connect);

?>

<script>
</script>
