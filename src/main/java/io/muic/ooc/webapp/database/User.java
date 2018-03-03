package io.muic.ooc.webapp.database;

public class User {

    private String username;
    private String password;
    private String firstname;
    private int id; //Primary key
    private boolean needUpdateFlag;

    public User(String username, String password, String firstname, int id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        needUpdateFlag = false;
    }

    public int getId() {
        return id;
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