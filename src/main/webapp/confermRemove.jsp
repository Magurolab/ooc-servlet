<%--
  Created by IntelliJ IDEA.
  User: poon.
  Date: 5/3/18
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Remove maybe?</title>
    </head>
    <body>
    <center>
        <h2>Are you sure you want to remove This user: ${targetUser} ? </h2>
        <div style="width:400px;">
            <div style="float: left; width: 130px;padding-left: 80px">
                <form action="/remove" method="post" >
                    <input type="submit" value="Yes" style="display: inline">
                </form>
            </div>

            <div style="float: right; width: 130px;padding-right: 60px">
                <form action="/" method="get">
                    <input type="submit" value="No" style="display: inline">
                </form>
            </div>
        </div>

    </center>

    </body>
</html>
