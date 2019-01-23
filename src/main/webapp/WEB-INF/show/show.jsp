<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示页面</title>
<style>

	#userShow {  
		display:none;  
		border:1em solid pink;
		height:50%;  
		width:24%;  
		position:absolute;/*让节点脱离文档流,我的理解就是,从页面上浮出来,不再按照文档其它内容布局*/  
		top:20%;/*节点脱离了文档流,如果设置位置需要用top和left,right,bottom定位*/  
		left:35%;  
		z-index:2;/*个人理解为层级关系,由于这个节点要在顶部显示,所以这个值比其余节点的都大*/  
		background: white;  
	} 
	
	#userOver {  
		width: 100%;  
		height: 100%;  
		opacity:0.3;/*设置背景色透明度,1为完全不透明,IE需要使用filter:alpha(opacity=80);*/  
		filter:alpha(opacity=80);  
		display: none;  
		position:absolute;  
		top:0;  
		left:0;  
		z-index:1;  
		background: silver;  
	}
	
	a{ text-decoration:none;}
	/* css注释： 鼠标经过热点文字被加下划线 */ 
	a:hover{ text-decoration:underline;}
	/* a 标签字体颜色为 黑色 */
	a{color:#000}
</style>
</head>
<body>

	<p align="center">
		<%-- <a href="javascript:window.location.href='<%=request.getContextPath() %>/show/login.jsp'">注销登录</a> --%>
		<a href="${pageContext.request.contextPath}/logout">注销登录</a>
		<a href="javascript:window.location.href='<%=request.getContextPath() %>/show/add.jsp'">添加用户</a>
	</p>

	<div align="center">
		<table border="1">
			<tr>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th>&nbsp;账号&nbsp;</th>
				<th>&nbsp;用户名&nbsp;</th>
				<th>&nbsp;密码&nbsp;</th>
				<th>&nbsp;操作&nbsp;</th>
			</tr>
			<tbody id="lo"></tbody>
		</table>
		
		<a href="javascript:void(0)" onclick="init(1)">首页</a>
		<a href="javascript:void(0)" onclick="init(2)" id="shang">上页</a>
		<a href="javascript:void(0)" onclick="init(3)" id="xia">下页</a>
		<a href="javascript:void(0)" onclick="init(4)">尾页</a>
		<select id="pageSizeId" onchange="init(5)">
			<option value="2">2</option>
			<option value="4">4</option>
			<option value="10">10</option>
			<option value="20">20</option>
		</select>
		<br>
		总记录数：<span id="pageSizeCountId"></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		总页数：<span id="pageCountId"></span>       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		当前页：<span id="pageId"></span>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	</div>
		
	<form action="" method="POST" id="userDel">
		<input type="hidden" name="_method" value="DELETE" />
	</form>
	
	<div id="userShow">
		<div align="right">
			<button>
				<a href="javascript:user_hide()">&nbsp;X&nbsp;</a>
			</button>
		</div> 
		<div align="center">
			<form action="<%=request.getContextPath() %>/user" method="post">
				<p align="left">修改用户</p>
				<input type="hidden" name="id" id="uid" />
				<input type="hidden" name="account" id="uacc" />
				用户名: <input name="name" type="text" id="uname"/><br/>
				密&nbsp;&nbsp;&nbsp;码:<input name="password" type="text" id="upass"/><br/>
				<input name="sub" type="submit" id="sub"/>
				<input type="hidden" name="_method" value="PUT" />
			</form>
		</div>
		</div>  
	<div id="userOver"></div>
	
	</form>
</body>
<script type="text/javascript">

	function aa()
	{
		document.getElementById("formId").submit();
	}

	function init(mark){
		var pageSize = "";
		var page = "";
		if(mark == 1){
			pageSize = $("#pageSizeId").val();
			page = 1;
		}else if(mark == 2){
			pageSize = $("#pageSizeId").val();
			var pageCount = $("#pageId").text();
			page = (parseInt(pageCount)-1);
			
		}else if(mark == 3){
			pageSize = $("#pageSizeId").val();
			var pageCount = $("#pageId").text();
			page = (parseInt(pageCount)+1);
		}else if(mark == 4){
			pageSize = $("#pageSizeId").val();
			page = $("#pageCountId").text();
		}else if(mark == 5){
			pageSize = $("#pageSizeId").val();
			page = 1;
		}
		
		$.ajax({
			type: "get",  // 请求方式(post或get)
			async:false,  //默认true(异步请求),设置为false(同步请求)
			url:"<%=request.getContextPath() %>/user", // 发送请求的地址
			scriptCharset: 'utf-8',
			data:{pageSize:pageSize,page:page},
			dataType:"json",
			success:function(a){
				alert(a);
				$("#lo").empty();
				
				var user = eval("("+a+")"); // 将传过来的值转为json格式
				var abc = "";
				var dd = "${dlt}";
				var cc = "${upd}";
				var list = user.t;
				
				$("#pageSizeCountId").text(user.pageSizeCount);
				$("#pageCountId").text(user.pageCount);
				$("#pageId").text(user.page);
				$("#pageSizeId").val(user.pageSize);
				
				if(user.page == 1){
					$("#shang").hide();
				}else{
					$("#shang").show();
				}
				
				
				if(user.page == user.pageCount){
					$("#xia").hide();
				}else{
					$("#xia").show();
				}
				
				for(var i in list){
				 	abc +=  "<tr><td>"+list[i].id+"</td>"+
				 			"<td>"+list[i].account+"</td>"+
				 			"<td>"+list[i].name+"</td>"+
				 			"<td>"+list[i].password+"</td>"+
					 		"<td><a href='javascript:user_show("+list[i].id+")' >修改用户</a> &nbsp;"+
					 		"<a href='javascript:void(0)' class='del' id='<%=request.getContextPath() %>/user/"+list[i].id+"'>删除用户</a> &nbsp;"+
					 		"</td></tr>";
				}
				$("#lo").append(abc);
				loader();
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
	        }
		});
	}
		
	/* 页面加载完出现 */
	$(document).ready(function(){
		
		init();
		loader();
	});

	
	function loader(){
		$(".del").click(function() {
			if(confirm("确认删除吗?")){
				var dlt = $(this).attr("id"); // attr() 方法设置或返回被选元素的属性值
				$("#userDel").attr("action", dlt).submit();
				return false;
			}
		});
	}
	
	var us = document.getElementById('userShow');
	var uo = document.getElementById('userOver');
    function user_show(id)
    {  
    	$.ajax({
			type: "get",  // 请求方式(post或get)
			async:false,  //默认true(异步请求),设置为false(同步请求)
			url:"<%=request.getContextPath() %>/get", // 发送请求的地址
			scriptCharset: 'utf-8',
			dataType:"json",
			data:{"id":id},
			success:function(aa){
				var ujson = eval("("+aa+")"); // 将传过来的值转为json格式

				
				$("#uid").val(ujson.id);
				$("#uname").val(ujson.name);
				$("#uacc").val(ujson.account); // val(aa) 赋值
				$("#upass").val(ujson.password); // 赋值
			}
		});
        us.style.display = "block";
        uo.style.display = "block";
    }  
    function user_hide()
    {  
        us.style.display = "none";
        uo.style.display = "none";
    }
    
</script>
</html>