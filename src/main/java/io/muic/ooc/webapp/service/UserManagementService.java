package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.database.MySQLDatabase;

import javax.servlet.http.HttpServletRequest;

public class UserManagementService {
    MySQLDatabase database = new MySQLDatabase();

    public boolean addUser(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");

        if(!database.getUsersMap().keySet().contains(username)) {
            database.createNewUser(username, password, firstname);
            return true;
        }else
            return false;
    }
    public void removeUser(String targetUser){

        database.removeUser(targetUser);
    }

    public void editUser(HttpServletRequest request, String targetUser){

        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");

        database.updateUserInfoWithSameUsername(targetUser, password, firstname);


    }
    public boolean isInDatabase(HttpServletRequest request){
        return database.getUsersMap().keySet().contains(request.getParameter("username"));
    }


}
