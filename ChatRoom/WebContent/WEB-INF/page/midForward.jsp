<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <%-- <meta http-equiv="refresh" content="2;url=${pageContext.request.contextPath}/login.jsp" /> --%>
	  <title>midForward.jsp</title>
	</head>
	<body>
		登录成功，5秒后进入主页面......
		<span id="s"></span><br>
		name=${person.username}<br>
		password=${person.password}<br>
		
<script type="text/javascript">
function Redirect(){
	var url="${pageContext.request.contextPath}/chatRoom.jsp";
	window.location=url;
}
var i = 6; 
function shownum(){ 
i=i-1;
document.all.s.innerHTML=i+"秒"; 
}
timer=setInterval('shownum()', 1000);
timer=setTimeout('Redirect()',6000);

</script>

	</body>
</html>