<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>chatRoom.jsp</title>
	</head>
	<body>

	  	<div align="center">
	  	<br>
		<form name="form1" method="post" action="">
			<table width="80%" border="1">
				
				<tr>
					<td align="left" width="30%" style="height: 30px">
						当前在线人数：
					</td>
					<td rowspan="2">
						正在与xxx聊天
					</td>
				</tr>
				
				<tr>
					<td align="left" style="height: 30px">
						在线人员列表
					</td>
				</tr>
				
				<tr>
					<td align="left" rowspan="2" style="height: 380px">
						<input type="button" value="aa"><br>
						<input type="button" value="bb">
					</td>
					<td align="left" style="height: 250px">
						<textarea id="dialog" style="width: 500px; height: 230px"></textarea>
					</td>
				</tr>
				
				<tr>
					<td align="right" style="height: 130px;">
						<textarea id="sendText" style="width: 505px; height: 90px"></textarea><br>
						<input type="button" value="发送" onclick="sendtext()">
					</td>
				</tr>
				
			</table>
		</form>
	  	</div>

<script language="javascript">

function sendtext(){
	var text=document.getElementById("sendText").value;
	//alert(text);
	var parent=document.getElementById("dialog");
	var child=document.createTextNode(text);
	parent.appendChild(child);
	document.getElementById("sendText").value="";
}

</script>
	</body>
</html>