<%@ page import="io.muic.ooc.webapp.database.User" %>
<%@ page import="java.util.List" %>
<%@ page import="io.muic.ooc.webapp.database.MySQLDatabase" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<body>
<center>
    <h2>Welcome, ${currentUser}</h2>

    <div style="width:400px;">
        <div style="float: left; width: 130px;padding-left: 80px">
            <form action="/adduser" method="get">
                <input type="submit" value="Add User">

            </form>
        </div>
        <div style="float: right; width: 130px;padding-right: 60px">
            <form action="/logout" method="get">
                <input type="submit" value="Logout">
            </form>
        </div>
    </div>
</center>

    <table class="table table-hover table-dark" align="center" style="border: 2px solid whitesmoke">
        <thead>
        <tr>

            <th scope="col">Username</th>
            <th scope="col">Firstname</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <%
            MySQLDatabase db = new MySQLDatabase();
            List<User> lstUsers = db.getUsersList();
            Collections.sort(lstUsers);
            for (User user : lstUsers) {
        %>
            <tr style="color:black">

                <td><%= user.getUsername() %>

                </td>
                <td><%= user.getFirstname() %>
                </td>
                <td>
                    <form action="/remove" method="get">
                        <input type="hidden" name="targetUser" value="<%=user.getUsername()%>"/>
                        <button type="submit" class="btn btn-outline-primary">Remove</button>
                    </form>
                </td>


        <%
            }
        %>
        </tbody>
    </table>

</body>
</html>
