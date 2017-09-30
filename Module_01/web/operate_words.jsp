<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2017/9/5
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operate Words</title>

    <link rel="stylesheet" type="text/css" href="Bootstrap/css/bootstrap.min.css">
    <script src="JQuery/jquery-3.2.0.min.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            var username = "<%=session.getAttribute("username")%>";
            if(username=="null"){
                alert("Please Login First");
                window.location.href = "login.jsp";
            }
            else alert(username);

        })
    </script>

</head>
<body>

</body>
</html>
