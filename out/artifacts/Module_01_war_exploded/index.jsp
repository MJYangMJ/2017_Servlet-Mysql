<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2017/9/4
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Welcome to W-Search</title>

    <link rel="stylesheet" type="text/css" href="Bootstrap/css/bootstrap.min.css">
    <script src="JQuery/jquery-3.2.0.min.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>

    <style type="text/css">
        /*body{*/
            /*margin: 70px;*/
        /*}*/
        /*.navbar{*/
            /*margin: 0px;*/
        /*}*/
        .jumbotron{
            padding-top: 100px;
        }
        form div{
            padding-left: 50px;
        }
        ul.nav-tabs{
            width: 170px;
            margin-top: 70px;
            border-radius: 4px;
            border: 1px solid #ddd;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
        }
        ul.nav-tabs li{
            margin: 0;
            border-top: 1px solid #ddd;
        }
        ul.nav-tabs li:first-child{
            border-top: none;
        }
        ul.nav-tabs li a{
            margin: 0;
            padding: 8px 16px;
            border-radius: 0;
        }
        ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover{
            color: #fff;
            background: #0088cc;
            border: 1px solid #0088cc;
        }
        ul.nav-tabs li:first-child a{
            border-radius: 4px 4px 0 0;
        }
        ul.nav-tabs li:last-child a{
            border-radius: 0 0 4px 4px;
        }
        ul.nav-tabs.affix{
            top: 50px; /* Set the top position of pinned element */
        }
        h2{
            padding-top: 60px;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#logined").css("color","blue");
            $("#unlogined").css("color","red");
            $("#logined").parent().parent().append("<li><a href='login.jsp' style='color: #ff0000' data-toggle='tooltip'" +
                "data-placement='bottom' title='Click To Open Login Page'>Change Account</a></li>")
        })
        $(function () { $("[data-toggle='tooltip']").tooltip(); });
        $(document).ready(function () {
            var today = new Date();
            $("hr:last").append("<p id='time_set' class='pull-right' style='margin-bottom: 100px;margin-top: 30px;'>Current Time:"+today+"</p>");
        })
        setInterval(function get_Date(){
            var today = new Date();
            $("hr:last p").remove();
            $("hr:last").append("<p id='time_set' class='pull-right' style='margin-bottom: 100px;margin-top: 30px;'>Current Time:"+today+"</p>");
        },1000);
        function alertNoneFunction() {
            alert("This is not finished yet,be patient and wait!");
        }
        function showAllUsers() {
            var user = "<%=session.getAttribute("username")%>";
            if (user!="Yang"){
                alert("NOT CERTIFIED!!DENIED");
            }
            else $("#showall").attr("href","FindAllUserServlet");
        }
        <%--function notCertified() {--%>
            <%--var user = "<%=request.getAttribute("username")%>";--%>
        //can't operate if you didn't login.
        <%--}--%>
    </script>
</head>
<body data-spy="scroll" data-target="#myScrollspy">
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-responsive-collapse">
                <!--data-target's value must be nav's classname or idname,if not the nav will not show but icon-bar-->
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="javascript:void(0)" class="navbar-brand">W-Search</a>
        </div>
        <div class="collapse navbar-collapse navbar-responsive-collapse" id="navbar-responsive-collapse">
            <ul class="nav navbar-nav">
                <li>

                    <%if(session.getAttribute("username")!=null){%>
                    <a id="logined" href="#" data-toggle="modal" data-target="#info_modal">
                        <i class="glyphicon glyphicon-user"></i>
                        Current User:<%=session.getAttribute("username")%></a>
                    <%}else{%>
                    <a id="unlogined" href="login.jsp">Please Login First.</a>
                    <%}%>

                </li>
                <li><a href="#" data-toggle="modal" data-target="#mymodal">Contact</a></li>
                <li><a id="showall" href="javascript:void(0);" onclick="showAllUsers()">I'm Manager</a></li>
            </ul>
            <form class="navbar-form pull-right" role="search">
                <div class="form-group">
                    <input class="form-control" type="text" placeholder="Key Words"/>
                    <span class="glyphicon glyphicon-search white"></span>
                </div>
                <button type="submit" class="btn btn-default form-control"  disabled="disabled">Search</button>
            </form>
        </div>
    </div>
    <div class="modal fade" id="info_modal" tabindex="-1" role="dialog" aria-labelledby="mymodal" aria-hidden="true" data-backdrop="static" data-keyboard="true" data-show="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">Following is your information.</h4>
                </div>
                <div class="modal-body">
                    <p>Account:<%=request.getAttribute("username")%></p>
                    <p>Password:<%=request.getAttribute("password")%></p>
                    <p>Login_id:<%=request.getAttribute("user_id")%></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="mymodal" aria-hidden="true" data-backdrop="static" data-keyboard="true" data-show="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">Following are the details of this program</h4>
                </div>
                <div class="modal-body">
                    <p>14103419 杨梦杰</p>
                    <p>14103403 冯宇壮</p>
                    <p>14103421 张德佳</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="jumbotron">
            <h2>Welcome to w_Search</h2>
        </div>
        <div class="row">
            <div class="col-xs-3" id="myScrollspy">
                <ul class="nav nav-tabs nav-stacked" data-spy="affix" data-offset-top="150">
                    <li><a href="#section-1">Search Word</a></li>
                    <li><a href="#section-2">Browse Words Package</a></li>
                    <li><a href="#section-3">Personal Words Manage</a></li>
                    <li><a href="#section-4">Modify User Information</a></li>
                    <li><a href="#section-5" onclick="alertNoneFunction()">More Function Is Working Out!!</a></li>
                </ul>
            </div>
            <div class="col-xs-9">
                <hr>
                <h2 id="section-1">Search Word</h2>
                <div class="media">
                    <a class="pull-right" href="SearchWordsServlet?wordInfo=*&characterInfo=*&classesInfo=*&transform=1&page=1">
                        <img class="media-object" src="img/index_1.jpg" height="206" width="365"/>
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">In this part you can search the word exactly.</h4>
                        <p>The words showed up are from your personal words-list and dictionary.</p>
                    </div>
                </div>
                <hr>
                <h2 id="section-2">Browse Words Package</h2>
                <div class="media">
                    <a class="pull-left" href="search_packages.jsp">
                        <img class="media-object" src="img/index_1.jpg" height="206" width="365"/>
                    </a>
                    <div class="media-body" style="padding-left: 250px;">
                        <h4 class="media-heading">This part you will find all the package you have create.</h4>
                        <p>Search from the database to show up the package you got.</p>
                    </div>
                </div>
                <hr>
                <h2 id="section-3">Personal Words Manage</h2>
                <div class="media">
                    <a class="pull-right" href="AddWordServlet?time=1">
                        <img class="media-object" src="img/index_1.jpg" height="206" width="365"/>
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">Regulate the word package you have create,and the words which are in it.</h4>
                        <p>This is kind of hard to conduct.</p>
                    </div>
                </div>
                <hr>
                <h2 id="section-4">Modify User Information</h2>
                <div class="media">
                    <a class="pull-left" href="GetUserInfoServlet?username=<%=session.getAttribute("username")%>">
                        <img class="media-object" src="img/index_1.jpg" height="206" width="365"/>
                    </a>
                    <div class="media-body" style="padding-left: 250px;">
                        <h4 class="media-heading">Modify the password and username you got.</h4>
                        <p>Not duplicate with the one had been create.</p>
                    </div>
                </div>
                <hr>
                <h2 id="section-5" style="color: red;">More Function Is Working Out!!</h2>
                <div class="media">
                    <a class="pull-right" href="javascript:void(0);" onclick="alertNoneFunction()">
                        <img class="media-object" src="img/index_1.jpg" height="206" width="365"/>
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">Patience From Yang.</h4>
                        <p>Patience From Yang.</p>
                    </div>
                </div>
                <hr>
            </div>
        </div>
    </div>
</body>
</html>
