<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%for(bettingDTO m : list){ %>
<form action="./betting.ap " method="post" name="form1">
	<td width="12%" height="23" align="center"><span class="bet_day"><%=m.getTime %><input type="hidden" name="time" value="<%=m.getTime %>" id="time">
	</span><span class="lightbrown"></span></td>
	<td width="32%" class="disable">

		<table class="noSelected2" width="100%" border="0" cellspacing="0"
			cellpadding="0" id="id1_18562429" align="left" style="cursor: hand;">
			<tbody>
				<tr>
					<td width="80%" align="left"
						style="padding-left: 5px; font-weight: bold;" height="23" id="h"
						name="h"><%=m.getTeam1 %><input type="hidden" name="home" value="<%=m.getTeam1 %>"
						id="home"></td>
					<td width="20%" align="right"
						style="padding-right: 5px; font-weight: bold;" id="hb" height="23"><%=m.getTeam1_count %><input
						type="hidden" name="homeb" value="<%=m.getTeam1_count %>" id="homeb">


						<button id="join1-btn" type="submit" value="1" border="0"
							class="ui_btn_red" onclick="A()">
							배팅</a></td>

				</tr>

			</tbody>
		</table>
	</td>
	<td width="12%" class="disable">

		<table align="center" class="noSelected2">


			<tbody>
				<tr>
					<td align="center" id="id0_18562429_betting"
						style="font-weight: bold;"><%=m.getTeam2_count %><input type="hidden"
						name="draw" value="<%=m.getTeam2_count %>" id="draw"><br>
					<button id="join1-btn" type="submit" value="2" border="0"
							class="ui_btn_gray" onclick="B()">
							배팅</a></a><span id="id0_18562429_team"></span></td>
				</tr>
			</tbody>
		</table>
	</td>




	<td width="32%" class="disable">
		<table id="id2_18562429" width="100%" border="0" cellspacing="0"
			cellpadding="0" align="left" class="noSelected2"
			style="cursor: hand;">
			<tbody>
				<tr>
					<td width="20%" align="left"
						style="padding-left: 5px; font-weight: bold;"
						id="id2_18562429_team" height="23" bgcolor=""><%=m.getTeam2_count %> <input
						type="hidden" name="awayb" value="<%=m.getTeam2_count %>" id="awayb">
					<button id="join1-btn" type="submit" value="3" border="0"
							class="ui_btn_red" onclick="C()">
							배팅</a></td>
					<td width="80%" align="right"
						style="padding-right: 5px; font-weight: bold;"
						id="id2_18562429_betting" height="23" bgcolor=""><%=m.getTeam2 %><input
						type="hidden" name="away" value="<%=m.getTeam2 %>" id="away"></td>
				</tr>
			</tbody>
		</table>
	</td>
	<td width="80" align="center" class="batt_ing">배팅가능</td>
	<input type="hidden" name="doing" value="진행중" id="doing">
	</tr>
</form>
<%} %>