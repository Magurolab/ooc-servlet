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

public class AddUserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;
    private UserManagementService userManagementService = new UserManagementService();

    @Override
    public String getMapping() {
        return "/adduser";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!StringUtils.isBlank(req.getParameter("username"))
                && !StringUtils.isBlank(req.getParameter("password"))
                && !StringUtils.isBlank(req.getParameter("firstname"))){
            boolean addSucess = userManagementService.addUser(req);
            if(addSucess)
                resp.sendRedirect("/users");
            else{
                String error = "That user name is already exist.";
                req.setAttribute("error", error);
                RequestDispatcher rd = req.getRequestDispatcher("/adduser.jsp");
                rd.include(req, resp);
            }

        }else{
            String error = "Some information is missing.";
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("/adduser.jsp");
            rd.include(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if(authorized){

            RequestDispatcher rd = request.getRequestDispatcher("/adduser.jsp");
            rd.include(request, response);
        }else{
            response.sendRedirect("/login");
        }

    }
}
