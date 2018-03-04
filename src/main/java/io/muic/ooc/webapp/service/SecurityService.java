/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.database.MySQLDatabase;
import io.muic.ooc.webapp.database.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    MySQLDatabase database = new MySQLDatabase();
    
    private Map<String, User> userCredentials;

    private void updateUserCresedentials(){
        userCredentials = database.getUsers();
    }
    
    public boolean isAuthorized(HttpServletRequest request) {
        updateUserCresedentials();
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
       return (username != null && userCredentials.containsKey(username));
    }
    
    public boolean authenticate(String username, String password, HttpServletRequest request) {
        updateUserCresedentials();
        String passwordInDB = userCredentials.get(username).getPassword();// hashed password

        boolean isMatched = PasswordHasingService.verifyPassword(passwordInDB, password);
        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
}
