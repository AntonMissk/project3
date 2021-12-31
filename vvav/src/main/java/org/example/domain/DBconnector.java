package org.example.domain;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnector {
    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "Anton26rus");
        System.out.println("coonect to Database");

        return connection;
    }



    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        try {

            getConnection();

            stmt = getConnection().createStatement();

            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getConnection().close();
    }


}
