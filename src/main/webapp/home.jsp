<%@ page import="io.muic.ooc.webapp.database.User" %>
<%@ page import="java.util.List" %>
<%@ page import="io.muic.ooc.webapp.database.MySQLDatabase" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<body>
    <h2>Welcome, ${currentUser}</h2>

    <table class="table table-hover table-dark" align="center" style="border: 2px solid whitesmoke">
        <thead>
        <tr>

            <th scope="col">Username</th>
            <th scope="col">Firstname</th>
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

        <%
            }
        %>
        </tbody>
    </table>

</body>
</html>
