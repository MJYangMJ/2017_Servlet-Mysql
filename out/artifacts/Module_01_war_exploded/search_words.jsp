<%@ page import="java.util.List" %>
<%@ page import="com.bean.ClassesBean" %>
<%@ page import="com.bean.CharacterBean" %>
<%@ page import="com.bean.User" %>
<%@ page import="com.bean.WordBean" %>
<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2017/9/5
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Words</title>

    <link rel="stylesheet" type="text/css" href="Bootstrap/css/bootstrap.min.css">
    <script src="JQuery/jquery-3.2.0.min.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>

    <%List<CharacterBean> characterList = (List<CharacterBean>)session.getAttribute("characterList");%>
    <%List<ClassesBean> classesList = (List<ClassesBean>)session.getAttribute("classesList");%>

    <%List<WordBean> wordList = (List<WordBean>)request.getAttribute("wordList");%>

    <script type="text/javascript">
        $(document).ready(function () {
            var username = "<%=session.getAttribute("username")%>";
            if(username=="null"){
                alert("Please Login First");
                window.location.href = "login.jsp";
            }
//            else alert("Current Account:"+username);

        })
        $(document).ready(function () {
            var pages = "<%=session.getAttribute("pages")%>"-0;
//            pages = 11;
            if(pages%10!=0){
                var temp = (pages/10+"").indexOf(".");
                pages = (pages/10+"").substr(0,temp)-0+1;
            }

            $(".pagination").append("<li><a href='SearchWordsByPageServlet?page=1'>&laquo;</a></li>");
            for (var i=1;i<=pages+0;i++){
                var hrefstr = "SearchWordsByPageServlet?page="+i;
                $(".pagination").append("<li><a href='"+hrefstr+"'>"+i+"</a></li>");
                ;
            }
            i = i-1;
            var hrefstr = "SearchWordsByPageServlet?page="+i;
            $(".pagination").append("<li><a href='"+hrefstr+"'>&raquo;</a></li>");

        })

        $(document).ready(function () {
            var totalFrenq = 0;
            var wordlist = new Array();
            var i = 0;
            <%for (WordBean word:wordList){%>
                var temp = "<%=word.getFrequency()%>";

                wordlist[i] = temp-0;
                totalFrenq += wordlist[i];
                i++;
//                alert(wordlist[i]);
            <%}%>
            for (i=0;i<wordlist.length;i++){
//                $(".progress-bar:eq('"+i+"')").html(wordlist[i]);
//                $(".progress-bar:eq('"+i+"')").attr("aria-valuenow",parseInt(10,wordlist[i]/totalFrenq*100)+"%");
//                $(".progress-bar:eq('"+i+"')").attr("style","width:"+parseInt(10,wordlist[i]/totalFrenq*100)+"%;");
                $(".progress-bar:eq('"+i+"')").html(wordlist[i]/totalFrenq*100+"%")
                    .attr("aria-valuenow",wordlist[i]/totalFrenq*100+"%")
                    .attr("style","width:"+wordlist[i]/totalFrenq*100+"%;");
            }
            $(".progress-bar").attr("aria-valuemax",totalFrenq);
        })
    </script>



</head>
<body>



    <div class="navbar navbar-inverse" role="navigation">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-responsive-collapse">
                <!--data-target's value must be nav's classname or idname,if not the nav will not show but icon-bar-->
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">W-Search</a>
        </div>
        <div class="collapse navbar-collapse navbar-responsive-collapse" id="navbar-responsive-collapse">
            <ul class="nav navbar-nav">

            </ul>
        </div>
    </div>

    <div style="margin: 70px;">
        <h3>This is search_words page.Now you can search words</h3>
        <h4>Hello&nbsp;<%=session.getAttribute("username")%>&nbsp;</h4>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form action="SearchWordsServlet" class="form-inline" role="form" method="post">
                    <div class="form-group">
                        <label for="character">character</label>
                        <select class="form-group" id="character" name="characterInfo">
                            <option>*</option>
                            <%for(CharacterBean character : characterList){%>
                                <option><%=character.getCharacterInfo()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="classes">classes</label>
                        <select class="form-group" id="classes" name="classesInfo">
                            <option>*</option>
                            <%for (ClassesBean classes : classesList){%>
                                <option><%=classes.getClassesInfo()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group checkbox">
                        <label for="transform">transform</label>
                        <input class="form-control" type="checkbox" id="transform" name="transform" checked>
                    </div>
                    <div class="form-group hidden">
                        <input value="1">
                    </div>
                    <div class="pull-right">
                        <div id="word_input" class="form-group">
                            <input class="form-control" type="text" name="wordInfo" placeholder="Input the word">
                            <span class="glyphicon glyphicon-search"></span>
                        </div>
                        <button id="submit" type="submit" class="btn btn-primary form-control" style="padding-left: 20px;">Search</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="word_result" class="table table-responsive table-striped table-bordered">
                    <caption>Result Table</caption>
                    <thead>
                        <tr>
                            <th>WordId</th>
                            <th>Word</th>
                            <th>Paraphrase</th>
                            <th>Example</th>
                            <th>Frequency</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (WordBean word : wordList){%>
                            <tr>
                                <td><%=word.getWordId()%></td>
                                <td><%=word.getWordinfo()%></td>
                                    <%--<td><s:property value="characterId"/></td>--%>
                                    <%--<td><s:property value="classesId"/></td>--%>
                                <td><%=word.getParaphrase()%></td>
                                <td><%=word.getExample()%></td>
                                <%--<td><%=word.getFrequency()%></td>--%>
                                <td>
                                    <div class="progress active">
                                        <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar"
                                             aria-valuenow="" aria-valuemin="0" aria-valuemax="" style="">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        <%}%>
                    </tbody>
                </table>
                <ul class="pagination pull-right">
                    <%--<li><a href="">&laquo;</a></li>--%>
                    <%--<li><a href="">1</a></li>--%>
                    <%--<li><a href="">2</a></li>--%>
                    <%--<li><a href="">3</a></li>--%>
                    <%--<li><a href="">4</a></li>--%>
                    <%--<li><a href="">5</a></li>--%>
                    <%--<li><a href="">&raquo;</a></li>--%>
                </ul>

            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <a href="index.jsp">Back To Index</a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-md-offset-5">
                <h5>No More To Show</h5>
            </div>
        </div>
    </div>
</body>
</html>
