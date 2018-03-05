<%--
  Created by IntelliJ IDEA.
  User: poon.
  Date: 5/3/18
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add user</title>
    </head>

    <body>
    <center>
        <h2>Register</h2>
        <p>${error}</p>
        <form action="/adduser" method="post">
            Username:<br/>
            <input type="text" name="username"/>
            <br/>
            Password:<br/>
            <input type="password" name="password">
            <br/>
            Firstname:<br/>
            <input type="text" name="firstname">
            <br> <br>
            <input type="submit" value="Create">

        </form>
        <form action="/" method="='get">
            <input type="submit" value="Cancel">
        </form>

    </center>

    </body>
</html>
