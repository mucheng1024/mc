<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>ergisterSuccess.jsp</title>
	</head>
	<body>
	注册成功！<br><br>
	<input type="button" value="返回登录" onclick="goback()"><br>
	name=${person.username }<br>
	password=${person.password }<br>
	sex=${person.sex}
	
<script language="javascript">
function goback(){
	location.href='${pageContext.request.contextPath}/login.jsp'
}
</script>
</body>
</html>