<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>register.jsp</title>
	</head>
	<body>
		<div align="left">
			<h3>注册账号</h3>
			<hr />
		</div>
	  	<div align="center">
	  		<br>
			<form name="form1" method="post" action="${pageContext.request.contextPath}/controller/register.action">
			<table width="90%">
				<tr>
					<td width="50%" height="30" align="right">
						昵称
					</td>
					<td width="50%" height="30" align="left">
						<input type="text" id="username" name="person.username">
					</td>
				</tr>
				<tr>
					<td width="50%" height="30" align="right">
					            密码
					</td>
					<td width="50%" height="30" align="left">
					    <input type="password" id="password1" name="person.password">
					</td>
				</tr>
				<tr>
					<td width="50%" height="30" align="right">
					            确认密码
					</td>
					<td width="50%" height="30" align="left">
					    <input type="password" id="password2" name="" onblur="validate()">
					</td>
				</tr>
				<tr>
					<td width="50%" height="30" align="right">
					            性别
					</td>
					<td width="50%" height="30" align="left">
					    <input type="radio" id="sex1" name="person.sex" value="male" onchange="print()">男
					    <input type="radio" id="sex2" name="person.sex" value="female" onchange="print()">女
					</td>
				</tr>
				<tr>
					<td width="70%" height="40" align="center" colspan="2">
						<input type="button" value="" style="VISIBILITY: hidden;width:200px; height: 30px;">
						<input type="submit" id="sub" name="" value="提交注册" 
						style="width: 200px; height: 30px;" >
					</td>
				</tr>
			</table>
		</form>
	  	</div>
	  	
<script language="javascript">

function validate(){
	var pass1=document.getElementById("password1").value;
	var pass2=document.getElementById("password2").value;
	
	if(pass1 != pass2){
		alert("两次输入的密码不一致！");
	}		
}

function print(){
	var objs = document.getElementsByName("person.sex");
	var str;
	for(var i=0;i<objs.length;i++)   
	{   
	  if(objs[i].checked)   
	  {   
	   if(objs[i].value=="male")   
	   {   
	   	str="male";
	   }   
	   else if(objs[i].value=="female")   
	   {   
	   	str="female"
	   }
	  }
	}
	alert(str);
}

function getInfo(){
	
}
</script>
	</body>
</html>