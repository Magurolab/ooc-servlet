package io.muic.ooc.webapp.database;

import io.muic.ooc.webapp.service.PasswordHasingService;

import java.sql.*;

import java.util.*;

public class MySQLDatabase {
    //my local
//    private static String dbURL = "jdbc:mysql://localhost/oocServlet?useSSL=false";
//    private static String dbUser = "poon.";
//    private static String dbPass = "why";
    private static String dbURL = "jdbc:mysql://localhost/oocServlet?useSSL=false";
    private static String dbUser = "poon";
    private static String dbPass = "poon12345";

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

    public Map<String,User> getUsersMap(){
        return updateUsers();
    }

    public List<User> getUsersList(){
        users = updateUsers();
        List<User> data = new ArrayList<>();
        for(String user : users.keySet()){
            data.add(users.get(user));
        }
        return data;

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

    public void updateUserInfoWithSameUsername(String username, String password, String firstname){

        String qry = "UPDATE Users SET firstname = ? ,password = ? WHERE username = ?;";
        String hashPassword = PasswordHasingService.gethashPassword(password);
        try {
            preparedStatement = getConnnection().prepareStatement(qry);

            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, hashPassword);
            preparedStatement.setString(3,username);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }




    private void printUsers(){
        Map<String, User> tmp = this.getUsersMap();
        System.out.println("printing users");
        System.out.println(tmp.keySet());
        System.out.println();
    }

    public static void main(String[] args) {
        // user: poon, 1234
        MySQLDatabase db = new MySQLDatabase();
//        db.createNewUser("ply","1234","plython");
//        db.createNewUser("boat","1234","autokill");
//        db.createNewUser("k2","1234","gem");
//        db.createNewUser("atomic", "1234","sunUltraSpark");
        db.removeUser("");

    }

}
