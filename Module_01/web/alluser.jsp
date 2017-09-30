<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2017/9/5
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.List"%>
<%@page import="com.bean.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users Show Here</title>

    <link rel="stylesheet" type="text/css" href="Bootstrap/css/bootstrap.min.css">
    <script src="JQuery/jquery-3.2.0.min.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>

    <style type="text/css">
        body{
            padding-top: 100px;
        }
        .row{
            padding-top:5px;
            padding-bottom:5px;
        }
    </style>

    <script type="text/javascript">

    </script>

</head>
<body>
    <%List<User> users = (List<User>)request.getAttribute("users");%>

    <div class="container">
        <div class="row">
            <div class="col-md-1 col-md-offset-2">
                <span class="label label-info"><strong>Current</strong></span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-1 col-md-offset-3">
                <span class="glyphicon glyphicon-user"></span>:
                <h3><%=session.getAttribute("username")%></h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <table class="table table-striped table-bordered table-hover">
                    <caption>User Tables</caption>
                    <thead>
                    <tr>
                        <th>&nbsp;&nbsp;UserId</th>
                        <th>&nbsp;&nbsp;UserName</th>
                        <th>&nbsp;&nbsp;Password</th>
                        <th>&nbsp;&nbsp;Options</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%for(User user : users){%>
                            <tr>
                                <td><%=user.getUser_id() %></td>
                                <td><%=user.getUsername() %></td>
                                <td><%=user.getPassword() %></td>
                                <td><a href="DeleteUserServlet?username=<%=user.getUsername() %>">delete</a></td>
                            </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <hr>
                <p><a href="index.jsp?username=<%=session.getAttribute("username")%>">Back To Index</a></p>
            </div>
        </div>
    </div>
</body>
</html>