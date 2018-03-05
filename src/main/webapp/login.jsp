<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<anyxmlelement xmlns:c="http://java.sun.com/jsp/jstl/core" />--%>
<html>
    <body>
        <center>
            <h2>Login</h2>
            <p>${error}</p>
            <form action="/login" method="post">
                Username:<br/>
                <input type="text" name="username"/>
                <br/>
                Password:<br/>
                <input type="password" name="password">
                <br><br>
                <input type="submit" value="Submit">
            </form>
        </center>
    </body>
</html>
