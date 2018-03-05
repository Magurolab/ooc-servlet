/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.database.MySQLDatabase;
import io.muic.ooc.webapp.database.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    MySQLDatabase database = new MySQLDatabase();
    
    private Map<String, User> userCredentials;

    private void updateUserCresedentials(){
        userCredentials = database.getUsersMap();
    }
    
    public boolean isAuthorized(HttpServletRequest request) {
        updateUserCresedentials();
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
       return (username != null && userCredentials.containsKey(username));
    }
    
    public int authenticate(String username, String password, HttpServletRequest request) {
        updateUserCresedentials();
        User userInDB = userCredentials.get(username);// hashed password
        if(userInDB == null)
            return 0;// that user is not in database.
        String passwordInDB = userInDB.getPassword();// hashed password

        boolean isMatched = PasswordHasingService.verifyPassword(passwordInDB, password);
        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return 1;// match
        } else {
            return 2;// not match
        }
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
}
