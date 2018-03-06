package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.database.MySQLDatabase;
import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.service.UserManagementService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveServlet extends HttpServlet implements Routable {
    private SecurityService securityService;
    private String targetUser;
    private String currentUser;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean authorized = securityService.isAuthorized(request);
        if(authorized){
            RequestDispatcher rd = request.getRequestDispatcher("confirmRemove.jsp");
            targetUser = request.getParameter("targetUser");
            currentUser = request.getParameter("currentUser");
            request.setAttribute("targetUser", targetUser);
            request.setAttribute("currentUser", currentUser);
            rd.include(request, response);

        }else{
            response.sendRedirect("/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserManagementService userManagementService = new UserManagementService();
        System.out.println(currentUser);
        System.out.println(targetUser);
        if(!targetUser.equals(currentUser)){
            userManagementService.removeUser(targetUser);
        }
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
