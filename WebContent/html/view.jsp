<%@page import="com.ryan.dao.BoardListDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ page import ="java.util.ArrayList" %>
<%@ page import ="com.ryan.dao.MemberDAO" %>
<%@ page import ="com.ryan.dao.BoardListDAO" %>
<%@ page import ="com.ryan.dto.viewDTO" %>
<%@ page import ="com.ryan.dto.*" %>
<%
	// 세션값 받아오기 (object인 값을 강제로 문자열로 변환)
	String id = (String) session.getAttribute("id");
	String name = (String) session.getAttribute("name");
int point = (int) session.getAttribute("point");
int level = (int) session.getAttribute("level");
	if (name == null) {
%>

<!--  로그인 안됨-->
<script>
	alert('로그인을 하세요');
	history.back();
</script>

<%	} %>

<%
 String num = request.getParameter("num");

 int n = Integer.parseInt(num);
 viewDTO dto = BoardListDAO.boardView(n);
 ArrayList<viewDTO> dto1 = BoardListDAO.commentView(n);
 
//viewDTO dto = new viewDTO();




%>

<%
	// 페이지 숫자 클릭 ?get방식
	String p = request.getParameter("p");
	
	
	
	// 전체 갯수
	int cnt = BoardListDAO.commentCount(n);
	//System.out.println(cnt);
	//System.out.println("전체: " + cnt);
	
	// 한 페이지당 10개씩 뿌리기
	int exit = 5; // 한 페이지에 뿌리고 싶은 갯수
	int totalpage = cnt / exit; 
	totalpage = totalpage +1 ;

	
	
	// System.out.println("페이지 " + totalpage);
	// ipage * 10 = ? ~ limit
	// System.out.println("페이지 시작점" + (totalpage * exit));
	int ipage = 0; //첫 페이지
	if(p != null){
		ipage = Integer.parseInt(p);
		ipage = ipage - 1; // 페이지로 넘어오는 숫자는 1부터라서
							// 디비에 넘겨야 할 숫자는 0부터 
	}
	int start = ipage * exit;
	
	ArrayList<viewDTO> list = BoardListDAO.selectComment(n,start , exit);
	
	
	%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title> ZI-ZONE </title>

<meta charset="euc-kr">

<link rel="stylesheet" href="./css/table.css">


<link rel="stylesheet" type="text/css" href="./css/hartz.css">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/button.css?3"> 
<style>
.pagination{display:-ms-flexbox;display:flex;padding-left:0;list-style:none;border-radius:.25rem}.page-link{position:relative;display:block;padding:.5rem .75rem;margin-left:-1px;line-height:1.25;color:#007bff;background-color:#fff;border:1px solid #dee2e6}.page-link:hover{z-index:2;color:#0056b3;text-decoration:none;background-color:#e9ecef;border-color:#dee2e6}.page-link:focus{z-index:2;outline:0;box-shadow:0 0 0 .2rem rgba(0,123,255,.25)}.page-item:first-child .page-link{margin-left:0;border-top-left-radius:.25rem;border-bottom-left-radius:.25rem}.page-item:last-child .page-link{border-top-right-radius:.25rem;border-bottom-right-radius:.25rem}.page-item.active .page-link{z-index:1;color:#fff;background-color:#007bff;border-color:#007bff}

.justify-content-end{-ms-flex-pack:end!important;justify-content:flex-end!important}.justify-content-center{-ms-flex-pack:center!important;justify-content:center!important}

</style>
</head>


<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" oncontextmenu="return false" ondragstart="return false" onselectstart="return false">



<style>


a:link    {color:#000000;text-decoration:none;}
a:visited {color:#000000;text-decoration:none;}
a:active  {color:#000000;text-decoration:none;}
a:hover  {color:#000000;text-decoration:none;}


#join-btn { width: 60px; height: 22px; line-height: 25px; text-align:center;  text-decoration:none; }
#join-btn:hover {  bottom: 39px !important; top:inherit; opacity:1.0;}

#join1-btn { width: 75px; height: 23px; line-height: 20px; text-align:center;  text-decoration:none; }



.pattern-header {
	width:100%;
	margin-top:15px;
	text-align: center;
    border-bottom: none;
    background:black;
    margin-bottom:10px;
 }

.pattern-header th,.pattern-header td {
    text-align: center;
    height: 35px;
    line-height: 25px;
    font-size: 12px;
    color: #fff;
    padding: 0px;
}

.charge {
    padding: 0px;
	text-align : center;
	
}

.charge tr,.charge td {
	padding : 0px;
	margin : 10px;
}


.board td{text-align:center; color:#ffffff; height:32px; background:url(/images/board_line.png) repeat-x bottom #151515; font-weight:bold; border:1px solid #302a2a;}
.board td.baner{height:4px;line-height:4px;}
.board td.no{}
.board td.subject{text-align:left; padding-left:4px; padding-right:4px;color:#ffffff;}
.board td.subject a:link {color:#8b8b8b;}
.board td.subject a:hover {color:#8b8b8b;}
.board td.subject a:active {color:#8b8b8b;}
.board td.subject a:visited {color:#8b8b8b;}
.board td.subjectL{text-align:left; padding-left:4px; padding-right:4px;color:#ffffff;}
.board td.subjectL a:link {color:#ffffff;}
.board td.subjectL a:hover {color:#ffffff;}
.board td.subjectL a:active {color:#ffffff;}
.board td.subjectL a:visited {color:#ffffff;}
.board td.subjectA{text-align:left; padding-left:4px; padding-right:4px;color:#00bfff;}
.board td.subjectA a:link {color:#00bfff;}
.board td.subjectA a:hover {color:#00bfff;}
.board td.subjectA a:active {color:#00bfff;}
.board td.subjectA a:visited {color:#00bfff;}
.board td.write{color:#ffffff; text-align:left; padding-left:6px;}
.board td.regdate{color:#ffffff;}
.board td.hit{color:#8b8b8b;}


</style>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td align="center">
     <table width="1190" border="0" cellspacing="0" cellpadding="0">
      <tbody><tr>
        <td valign="top">
        	
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tbody><tr>
                <td height="12"></td>
              </tr>
              <tr>
                <td height="155" align="center" background="./images/top.png">
                
                    <table width="1127" border="0" cellspacing="0" cellpadding="0">
                      <tbody><tr>
                        <td rowspan="2" valign="bottom"><a href="./home.ap"><img src="./images/LOGO1.gif" border="0" style="cursor:pointer;"></a></td>
                        <td height="30" align="right" valign="bottom">
                        
                            <table border="0" cellspacing="0" cellpadding="0">
                              <tbody><tr>
                                
                              </tr>
                            </tbody></table>
                        
                        </td>
                      </tr>
                      <tr>
                        <td width="730" height="70" valign="bottom">
                        
                             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tbody><tr>
                              <tbody><tr>
                                <td><a href="./cross.ap"><img src="./images/menu_out_00.png" onmouseover='this.src="./images/menu_on_00.png"' onmouseout='this.src="./images/menu_out_00.png"' name="menu00"></a></td>
								<td><a href="./result.ap"><img src="./images/menu_out_03.png" onmouseover='this.src="./images/menu_on_03.png"' onmouseout='this.src="./images/menu_out_03.png"' name="menu01"></a></td>
								<td><a href="./board.ap"><img src="./images/menu_out_02.png" onmouseover='this.src="./images/menu_on_02.png"' onmouseout='this.src="./images/menu_out_02.png"' name="menu02"></a></td>
								<td><a href="./charge.ap"><img src="./images/menu_out_01.png" onmouseover='this.src="./images/menu_on_01.png"' onmouseout='this.src="./images/menu_out_01.png"' name="menu03"></a></td>
                              </tr>
                            </tbody></table>
                        
                        </td>
                      </tr>
                    </tbody></table>
                
                </td>
              </tr>
              <tr>
                <td height="36" align="center" background="./images/infobar.png"  >
                
<table width="97%" border="0" cellspacing="0" cellpadding="0">
                  <tbody><tr>
                    <td class="color_write"><img src="./images/level/<%=level%>.gif" align="absmiddle">&nbsp;<span class="color_a"><%=name%></span> <span class="color_yellow">님</span>   
					&nbsp;&nbsp;&nbsp;
					<span class="color_yellow">포인트 : </span> <span class="color_a"><%=point%></span>&nbsp;<span class="color_yellow">P </span></td>
                    <td align="right" class="white_link">  <a href="./mybet.ap" id="join-btn" class="ui_btn_gray">배팅내역</a> <a href="./html/logout.jsp" id="join-btn" class="ui_btn_gray">로그아웃</a></td>
                  </tr>
                </tbody></table>
                
                </td>
              </tr>
            </tbody>
	</table> <iframe name="exeFrame" width="0" height="0" frameborder="0"></iframe>




	




            <table class="result" width="100%" border="0" cellspacing="0" cellpadding="0">
          	  <tbody><tr>
            	<td align="center" class="sub_back">
                    <table width="1134" border="0" cellspacing="0" cellpadding="0">
                        <tbody><tr>
                            <td valign="top">

<table width="1134" border="0" cellspacing="0" cellpadding="0"  align="center">
  <tbody><tr>
    <td height="38" align="left" valign="bottom">
	</td>
	<td align="left" ><img src="./images/title/title_free.png"></td>
  </tr>
 </tbody>
</table>











	
            <table class="result1" width="100%" border="0" cellspacing="0" cellpadding="0">
          	  <tbody><tr>
            	<td align="center" class="sub_back">
                    <table width="1147" border="0" cellspacing="0" cellpadding="0">
                        <tbody><tr>
                            <td valign="top">

<table width="1150" border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td height="38" align="left" valign="bottom">
	</td>

  </tr>
 </tbody>
</table>



<table width="99%" border="0" cellspacing="0" cellpadding="0"  align="center">
	<tbody><tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody><tr>
					<td height="4"></td>
				</tr>
			</tbody></table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewTop"  align="center">
				<tbody><tr>
					<th width="200">작성자</th>
					<td><strong> <%=dto.getName() %></strong></td>
					<th width="200">등록일</th>
					<td><strong> <%=dto.getTime() %></strong></td>
					</tr>
					<tr>
					<th width="200">제목</th>
					<td colspan="3"><strong> <%=dto.getTitle() %></strong></td>
					</tr>
					<tr>
					<th width="200" height="400">내용</th>
					<td valign="top" align="left" colspan="3"> <%=dto.getBoard() %></td>

				</tr>
</table>
<tr>
		<td align="center" style="padding: 15px 0 10px 0">
		
		<script type="text/javascript">
		function Del(){
			var answer = confirm("삭제하시겠습니까?")
			if (answer){
				location.href="./del.ap?num=<%=num %>";
			}
		}
		function bb(){
			location.href="edit.ap?num=<%= n %>";
			}
			
		</script> 
		
		
		
			<%  if( name.equals(dto.getName()) ) { %>
	
			<img src="./images/btnModify.png" onclick="bb()"; style="cursor:pointer">
			<img src="./images/btnDelete.png" onclick="Del()" style="cursor:pointer">
	<% } %>
		</td>
	
	</tr>


<form  method="post" action="./comment.ap">
<input type="hidden" name="board_num" value="<%=num %>">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="commentForm"  style="margin-top:25px;"  align="center">
	<tbody align="center" ><tr>
		<td width="80" class="title">댓글</td>
		<td width="794" class="conntent"><textarea name="body" id="inputContent" rows="3" class="text2" style="width:98%;"></textarea></td>
		<td width="100" align="btn"><button type="submit" class="btn btn-success">등록</button></td>
	</tr>
</tbody></table>
</form>
<table>
	<tbody><tr>
	<td height="10"></td>
	</tr>
</tbody></table>

<p>댓글 수(<%=cnt %>)</p>




<table border="0" cellspacing="0" cellpadding="0" width="100%" class="commentView" align="center">

<tbody>
<%for(viewDTO m : list){ %>
<tr>
	<td width="159" valign="center" class="write"  >&nbsp;&nbsp;<img src="./images/level/<%=m.getLevel() %>.gif" border="0" align="absmiddle"> <%= m.getName() %></td>
	<td width="646" class="content" valign="top" align="left"><font color="#ffffff"> <%=m.getBoard() %></font><br><br></td>
	<td width="161" valign="center" class="regdate"><font color="#ffffff"><%=m.getTime() %></font>&nbsp;&nbsp;
	

	<% if(name.equals(m.getName())) { %>
	
	<a href="./commentdel.ap?num=<%=m.getCommentnum() %>">
	
	<img src="./images/pop_close.png" border="0" style="cursor:pointer; vertical-align:middle;"></td></a>
<% } %>

	<script>

// 	function Del1(){
<%-- 			location.href="./commentdel.ap?num=<%=m.getNum() %> "; --%>
// 	}

</script>
</tr>
<%} %>





</tbody></table>

	 


</form>






</tbody>
		

  
    
  </tbody>
</table>


<div style="text-align:right">
<a href="./board.ap" class="btn btn-outline-danger">뒤로</a>
</div>

<nav>
	<ul class="pagination  justify-content-center">
	
 <%for (int i=1; i<=totalpage; i++) { %>
 	<% if( i != ipage + 1){ %>
 	<li class="page-item"><a class="page-link" href="view.ap?num=<%=n %>&p=<%=i%>"><%= i%></a></li>
  	<% }  else {%>
  	
 <li class="page-item active"><a class="page-link" href="#"><%= i%></a></li>
	<% } %>
	<% } %>
	
	</ul>
</nav>






 </table>
 </table>
 </table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tbody><tr>
                <td height="6"></td>
              </tr>
              <tr>
                <td height="36" align="center" background="./images/copy_back01.gif" class="white_linkS"><span class="color_writeS"><a>배병준</a>    &nbsp;&nbsp;I&nbsp;&nbsp;    <a>김광우</a>    &nbsp;&nbsp;I&nbsp;&nbsp;    <a>서우린</a>    &nbsp;&nbsp;I&nbsp;&nbsp;    <a>지to존</a>    &nbsp;&nbsp;I&nbsp;&nbsp;    <a>ZI-ZONE</a>
              </tr>
              <tr>
                <td height="50" align="center" background="./images/copy_back02.gif" class="color_blackB">Copyright(c)START All rights reserved.</td>
              </tr>


            </tbody>
			</table>
			
            
          </td>
         
       </tr>
    </tbody></table>
   </td>
  </tr>
</tbody></table>

</body></html>
