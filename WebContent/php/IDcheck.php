<?

session_start();


include 'dbconnect.php';

$id = $_GET['id'];


$result = mysql_query( "select * from member where id = '$id' ", $connect);

$check = mysql_num_rows($result); // ������ id���� ����� ���� ����


if($check > 0)

{



echo "�̹� ������� ���̵� �Դϴ�.";

$_SESSION['distinct_check']=0;

}

else

{

echo "��� ������ ���̵��Դϴ�.";

$_SESSION['distinct_check']=1;

}

?>
