<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>login.jsp</title>
	</head>
	<body>
		<div align="right">
			<h5>当前在线人数：...</h5>
		</div>
	  	<div align="center">
	  		<br>
			<H1>欢迎来到黒木爷的聊天室</H1>
			<form name="form1" method="post" action="${pageContext.request.contextPath}/controller/login.action">
			<table width="90%">
				<tr>
					<td width="100%" height="40" align="center" colspan="2">
						账号密码登录
					</td>
				</tr>
				<tr>
					<td width="50%" height="30" align="center">
						账号:
						<input type="text" name="person.username">
						<input type="button" value="注册账号" onclick="redirect()">
					</td>
				</tr>
				<tr>
					<td width="50%" height="30" align="center">
					            密码:
						<input type="password" name="person.password">
						<input type="button" value="找回密码">
					</td>
				</tr>
				<tr>
					<td width="100%" height="40" align="center" colspan="2">
						<input type="submit" name="sub" value="登录" style="width: 200px; height: 30px;" >
					</td>
				</tr>
			</table>
		</form>
	  	</div>

<script language="javascript">

function redirect(){
	location.href='${pageContext.request.contextPath}/register.jsp'
}

</script>
	</body>
</html>