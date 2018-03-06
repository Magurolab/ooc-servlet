<%--
  Created by IntelliJ IDEA.
  User: poon.
  Date: 6/3/18
  Time: 01:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add user</title>
</head>

<body>
<center>
    <h2> You are Editing User: ${targetUser} </h2>
    <br>
    <p>${error}</p>

    <form action="/edit" method="post">

        Password:<br/>
        <input type="password" name="password">
        <br/>
        Firstname:<br/>
        <input type="text" name="firstname">
        <br> <br>
        <input type="submit" value="Confirm">

    </form>
    <form action="/" method="='get">
        <input type="submit" value="Cancel">
    </form>


</center>

</body>
</html>