<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<style> 
	a{ text-decoration:none;}
	/* css注释： 鼠标经过热点文字被加下划线 */ 
	a:hover{ text-decoration:underline;}
	a{color:#000}
</style> 
</head>
<body>
	<c:if test="${!empty requestScope.error }"> <!-- 不为空时进入 -->
		${error}
		<form action="<%=request.getContextPath()%>/login_ssj">
			<!-- springsecurity 默认用户: j_username	默认密码: j_password -->
			<h5>请输入用户名:<input type="text" id="account" name="account"/></h5><form:errors path="account"></form:errors>
			<h5>请输入密码:<input type="password" id="password" name="password"/></h5><form:errors path="password"></form:errors>
			<input type="submit" value="登录	" />  &nbsp;&nbsp;&nbsp;
			<input type="reset" value=" 重置 " />
		</form>
	</c:if>
	<c:if test="${!empty requestScope.addlogin || empty requestScope.error}"> <!-- 添加成功时进入或者没有错误时进入 -->
		<form action="<%=request.getContextPath()%>/login_ssj" id="lod">
			<h5>请输入用户名:<input type="text" id="account" name="account"/></h5><form:errors path="account"></form:errors>
			<h5>请输入密码:<input type="password" id="password" name="password"/></h5><form:errors path="password"></form:errors>
			<input type="submit" value="登录	" />  &nbsp;&nbsp;&nbsp;
			<input type="reset" value=" 重置 " />
		</form>
	</c:if>
	
	<a href="add.jsp">添加用户</a>
</body>
<script type="text/javascript">
	$("lod").load(function(){
		alert("添加成功");
	});
</script>
</html>