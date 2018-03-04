package io.muic.ooc.webapp.database;

public class User {

    private String username;//Primary key
    private String password;
    private String firstname;
    private boolean needUpdateFlag;

    public User(String username, String password, String firstname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        needUpdateFlag = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public boolean isUpdateNeeded(){
        return needUpdateFlag;
    }
    public void updated(){
        needUpdateFlag = false;
    }
    public void needUpdate(){
        needUpdateFlag = true;
    }


}