package org.example.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class usersDao {

    public static users searchUser(String surname) throws SQLException {
        String selectStm = "SELECT * FROM person WHERE last_name = '" + surname + "'";

        Connection connection = DBconnector.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(selectStm);
        users user1 = getUsersfromResultSet(resultSet);
        return user1;

    }

    private static users getUsersfromResultSet(ResultSet rs) throws SQLException
    {
        users user = new users();
        while (rs.next()) {

            user.setPerson_id(rs.getInt("person_id"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
        }
        return user;
    }

    public static void insertUser(String name, String lastname, String email, String password) throws SQLException, ClassNotFoundException {
        String updateStmt =  "INSERT INTO person( first_name, last_name, email, password) VALUES ('"+name+"', '"+lastname+"','"+email+"','"+password+"')";
        try {
            DBconnector.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    public static void deleteUsserId (String userId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM person WHERE person_id = '"+userId+ "'";

        //Execute UPDATE operation
        try {
            DBconnector.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void updateUserEmail (String surname, String userEmail) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt = "UPDATE person SET email = '" + userEmail + "' WHERE last_name = '" + surname + "'";
        //Execute UPDATE operation
        try {
            DBconnector.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }

//    public static ObservableList<users> searchUsers () throws SQLException, ClassNotFoundException {
//        //Declare a SELECT statement
//        String selectStmt = "SELECT * FROM person";
//        //Execute SELECT statement
//        try {
//            //Get ResultSet from dbExecuteQuery method
//            ResultSet resultSet = DBconnector.dbExecuteQuery(selectStmt);
//            //Send ResultSet to the getEmployeeList method and get employee object
//            ObservableList<users> usersList = getUsetsList(resultSet);
//            //Return employee object
//            return usersList;
//        } catch (SQLException e) {
//            System.out.println("SQL select operation has been failed: " + e);
//            //Return exception
//            throw e;
//        }
//    }
//    private static ObservableList<users> getUsetsList(ResultSet rs) throws SQLException, ClassNotFoundException {
//        //Declare a observable List which comprises of Employee objects
//        ObservableList<users> userList = FXCollections.observableArrayList();
//        while (rs.next()) {
//            users user = new users();
//            user.setPerson_id(rs.getInt("person_id"));
//            user.setFirst_name(rs.getString("first_name"));
//            user.setLast_name(rs.getString("last_name"));
//            user.setEmail(rs.getString("email"));
//            //Add employee to the ObservableList
//            userList.add(user);
//        }
//        //return empList (ObservableList of Employees)
//        return userList;
//    }

    }

}
