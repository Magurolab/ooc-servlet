package io.muic.ooc.webapp.database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MySQLDatabase {
    private static String dbURL = "jdbc:mysql://localhost/oocServlet?useSSL=false";
    private static String dbUser = "poon.";
    private static String dbPass = "why";

    Connection conn = null;
    private PreparedStatement preparedStatement;
    Map<String,User> users;
    public MySQLDatabase(){
        users = new HashMap<>();

    }

    private Connection getConnnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

        System.out.println("Connection is NULL: " + (conn == null));
        return conn;
    }

    public Map<String,User> updateUsers(){
        ResultSet resultSet;

        try{
            String query = "SELECT username, password,firstname, id FROM Users;" ;
            preparedStatement = getConnnection().prepareStatement(query);
            resultSet= preparedStatement.executeQuery();
            User user;
            while (resultSet.next()){

                user = users.get(resultSet.getString("username"));
                if(user == null) { // init that user
                    user = new User(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("firstname"),
                            resultSet.getInt("id")
                    );
                    users.put(user.getUsername(), user);
                }else{
                    if(user.isUpdateNeeded()){
                        user.setFirstname(resultSet.getString("firstname"));
                        user.setPassword(resultSet.getString("password"));
                        user.updated();
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  users;
    }
    public Map<String,User> getUsers(){
        return updateUsers();
    }


    public static void main(String[] args) {
        MySQLDatabase db = new MySQLDatabase();
        Map<String, User> tmp = db.getUsers();
        for (String username: tmp.keySet()) {
            System.out.println(username);
            System.out.println(tmp.get(username).getPassword());

        }
    }

}
