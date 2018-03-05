package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.database.MySQLDatabase;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveServlet extends HttpServlet implements Routable {
    private SecurityService securityService;
    String targetUser;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean authorized = securityService.isAuthorized(request);
        if(authorized){
            RequestDispatcher rd = request.getRequestDispatcher("confermRemove.jsp");
//            targetUser = (String) request.getSession().getAttribute("targetUser");
            targetUser = request.getParameter("targetUser");
            request.setAttribute("targetUser", targetUser);
//            System.out.println(targetUser);
            rd.include(request, response);

        }else{
            response.sendRedirect("/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MySQLDatabase db = new MySQLDatabase();
        db.removeUser(targetUser);
        resp.sendRedirect("/");

    }

    @Override
    public String getMapping() {
        return "/remove";
    }
    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
