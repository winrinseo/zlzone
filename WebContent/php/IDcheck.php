<?

session_start();


include 'dbconnect.php';

$id = $_GET['id'];


$result = mysql_query( "select * from member where id = '$id' ", $connect);

$check = mysql_num_rows($result); // 동일한 id값이 저장된 행의 개수


if($check > 0)

{



echo "이미 사용중인 아이디 입니다.";

$_SESSION['distinct_check']=0;

}

else

{

echo "사용 가능한 아이디입니다.";

$_SESSION['distinct_check']=1;

}

?>
