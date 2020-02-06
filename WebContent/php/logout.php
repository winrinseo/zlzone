
<?
session_start();
/*
 setcookie("a","",0,"/");
 setcookie("b","",0,"/");
 setcookie("p","",0,"/");
*/
unset($_SESSION[userid]);
unset($_SESSION[username]);
unset($_SESSION[point]);
unset($_SEESION[userlevel]);



echo "
 <script language=\"javascript\">
 window.open('../index.html','_top');
 </script>";
?>

