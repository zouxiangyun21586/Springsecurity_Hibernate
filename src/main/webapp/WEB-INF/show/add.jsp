<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script>
<jsp:useBean id="command" class="com.yr.handly.UserHandly"
	scope="request"></jsp:useBean>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加页面</title>
<style>
a {
	text-decoration: none;
}

a {
	color: #000
}
</style>
</head>
<body>
	<c:if test="${!empty requestScope.error }">
		<form action="/Springmvc_spring_hibernate/user" method="post" align="center" name="formsub"  onsubmit="return check()">
			<font size="5" color="red"> 您所填写的信息有误,请重新填写</font><br /> <br />
			<h5>
				请输入账号:<input type="text" id="account" name="account" />
			</h5>
			<form:errors path="account"></form:errors>
			<h5>
				请输入用户名:<input type="text" id="name" name="name" />
			</h5>
			<form:errors path="username"></form:errors>
			<h5>
				请输入密码:<input type="password" id="password" name="password" />
			</h5>
			<form:errors path="password"></form:errors>
			<h5>
				请再次输入密码:<input type="password1" id="password1" name="password1" />
			</h5>
			<form:errors path="password"></form:errors>
			<input type="submit" value="提交" /> <input type="reset" value="重置" />
			<button>
				<a href="javascript:window.location.href='login.jsp'" />返回
			</button>
		</form>
	</c:if>
	<c:if test="${empty requestScope.error}">
		<form action="/Springmvc_spring_hibernate/user" method="post" align="center" name="formsub" onsubmit="return check()">
			<h5>
				请输入账号:<input type="text" id="account" name="account" />
			</h5>
			<form:errors path="account"></form:errors>
			<h5>
				请输入用户名:<input type="text" id="name" name="name" />
			</h5>
			<form:errors path="username"></form:errors>
			<h5>
				请输入密码:<input type="password" id="password" name="password" />
			</h5>
			<form:errors path="password"></form:errors>
			<h5>
				请再次输入密码:<input type="password" id="password1" name="password1" />
			</h5>
			<form:errors path="password1"></form:errors>
			<input type="submit" value="提交"> <input type="reset"
				value="重置" />
			<button>
				<a href="javascript:window.location.href='login.jsp'" />返回
			</button>
		</form>
	</c:if>
</body>
<script type="text/javascript">
	
	function check(){
		var username = document.formsub.name.value;
		var account = document.formsub.account.value;
		var password = document.formsub.password.value;
		var password1 = document.formsub.password1.value;
		if (username==""|| password==""||password1==""||account==""){
			alert("任意信息不能为空，请重新填写！");
			return false;
		}else if(username.length>12){
			alert("用户名不能超过12个字符,请重新输入！");
			return false;
		}else if(password.length<4){
			alert("密码不能小于4个字符,请重新输入！");
			return false;
		}else if (password!=password1){
			alert("两次密码输入不一致,请重新输入!");
			return false;
 		}else{
 			alert("添加成功!!");
			return true;
		}
	}
</script>
</html>