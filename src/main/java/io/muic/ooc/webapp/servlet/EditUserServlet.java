package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.service.UserManagementService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends HttpServlet implements Routable {
   private SecurityService securityService;
   private String targetUser;
   private String currentUser;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if(authorized){
            RequestDispatcher rd = request.getRequestDispatcher("/edit.jsp");
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
        String error;
        if (!StringUtils.isBlank(req.getParameter("password")) && !StringUtils.isBlank(req.getParameter("firstname"))) {
            userManagementService.editUser(req, targetUser);
            resp.sendRedirect("/");
        }else {
            error = "Some information is missing.";
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("/edit.jsp");
            rd.include(req, resp);
        }
    }

    @Override
    public String getMapping() {
        return "/edit";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
