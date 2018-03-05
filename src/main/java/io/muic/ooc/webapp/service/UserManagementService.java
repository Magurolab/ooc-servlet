package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.database.MySQLDatabase;

import javax.servlet.http.HttpServletRequest;

public class UserManagementService {
    MySQLDatabase database = new MySQLDatabase();

    public void addUser(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        database.createNewUser(username,password,firstname);
    }

    public void removeUser(){

    }

}
