package io.muic.ooc.webapp.database;

import io.muic.ooc.webapp.service.PasswordHasingService;

import java.sql.*;
import java.util.*;

public class MySQLDatabase {
    private static String dbURL = "jdbc:mysql://localhost/oocServlet?useSSL=false";
    private static String dbUser = "poon.";
    private static String dbPass = "why";

    Connection connection = null;
    private PreparedStatement preparedStatement;
    Map<String,User> users = new HashMap<>();

    private Connection getConnnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

        return connection;
    }

    public Map<String,User> updateUsers(){
        ResultSet resultSet;
        Set<String> currentUserPool = new HashSet<>();
        User user;

        try{
            String query = "SELECT username, password,firstname FROM Users;" ;
            preparedStatement = getConnnection().prepareStatement(query);
            resultSet= preparedStatement.executeQuery();

            while (resultSet.next()){

                user = users.get(resultSet.getString("username"));
                if(user == null) { // init that user
                    user = new User(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("firstname")
                    );
                }
                users.put(user.getUsername(), user);

                currentUserPool.add(resultSet.getString("username"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        Set<String> tmp = new HashSet<>(users.keySet());
        tmp.removeAll(currentUserPool);
        for (String s: tmp) {
            users.remove(s);
        }


        return  users;
    }

    public Map<String,User> getUsers(){
        return updateUsers();
    }


    public boolean createNewUser(String username,String password, String firstname) {
        connection = getConnnection();
        String hashedPassword = PasswordHasingService.gethashPassword(password);

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Users(username, password, firstname) VALUES(?,?,?);");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, firstname);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean removeUser(String username){
        connection = getConnnection();
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE username = ?;");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    private void printUsers(){
        Map<String, User> tmp = this.getUsers();
        System.out.println("printing users");
        System.out.println(tmp.keySet());
        System.out.println();
    }

    public static void main(String[] args) {
        MySQLDatabase db = new MySQLDatabase();

        db.createNewUser("poon","1234","Poonnarat");

    }

}