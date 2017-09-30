<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%--<%@taglib uri="/struts-tags" prefix="s"%>--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	
	<title>Login</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="description" content="This is Yang page">
	
	<link rel="stylesheet" type="text/css" href="Bootstrap/css/bootstrap.min.css">
	<script src="JQuery/jquery-3.2.0.min.js"></script>
	<script src="Bootstrap/js/bootstrap.min.js"></script>
	<style type="text/css">
		body{
			padding-top: 70px;
		}
	</style>

	<script>
        $(function () { $("[data-toggle='tooltip']").tooltip(); });
        $(document).ready(function(){
            $("#username_input input").focus();
            $("#submit").attr("disabled","disabled");
        });
	</script>

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<h2>Welcome to W-Search</h2>
			</div>
		</div>
		<div class="row" style="padding-top:50px">
			<div class="well col-md-6 col-md-offset-3">
				<form class="form-horizontal" action="LoginServlet" method="post">
					<h2 class="">Login</h2>
					<h5 class="alert-danger">
						<% if(request.getAttribute("msg1")!=null){%>
						<%=request.getAttribute("msg1") %>
						<%} %>
						<% if(request.getAttribute("msg2")!=null){%>
						<%=request.getAttribute("msg2") %>
						<%} %>
					</h5>
					<table class="table table-bordered" style="positioning:center">
						<tr>
							<!-- 用户名的表单域 -->
							<td>
								<div class="input-group input-group-md" id="username_input">
									<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
									<input class="form-control" placeholder="input username" type="text" name="username" />
								</div>
							</td>
						</tr>
						<tr>
							<!-- 密码的表单域 -->
							<td>
								<div class="input-group input-group-md" id="password_input">
									<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
									<input class="form-control" placeholder="input password" type="password" name="password" />
								</div>
							</td>
						</tr>
						<tr align="center">
							<td colspan="2" align="right">
								<select class="pull-left btn btn-default" name="autoLogin" data-toggle="tooltip" data-placement="right" title="Auto Login Selection">
									<option value="0">No Auto Login</option>
									<option value="1">1 Day</option>
									<option value="7">1 Week</option>
									<option value="30">1 Month</option>
								</select>
								<input id="submit" class="btn btn-primary" type="submit" value="Login"/>
								<input class="btn btn-primary" type="button" value="Register" onclick="window.location.href='register.jsp'"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- 提交请求参数的表单 -->
		
		
	<script type="text/javascript" color="0,0,255" opacity='1.0' zIndex="-2" count="99" src="CSS/canvas-nest.min.js"></script>
	<script type="text/javascript">
        $("input:lt(2)").blur(function () {
            var $submit = $("#submit");
            $submit.removeAttr("disabled");
            if($("input[name='username']").val()==''||$("input[name='password']").val()==''){
                $submit.attr("disabled","disabled");
            }
        });
	</script>
</body>
</html>