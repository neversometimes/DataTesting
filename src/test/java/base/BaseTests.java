package base;

import java.sql.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTests {

    public static Connection conx = null;
    public static ResultSet results;
    public static Statement statement;

    // general SQL statement operation
    public void executeReadQuery(String query) throws SQLException {
        initiateConnection();
        statement = conx.createStatement();
        results = statement.executeQuery(query);
    }


    // for SQL INSERT, UPDATE and DELETE statements
    public void executeUpdateQuery(String query) throws SQLException {
        initiateConnection();
        statement = conx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.executeUpdate(query);
    }

    @BeforeTest
    // connect to sample MySQL db ""
    public void initiateConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "horNET_7";
        try {
            conx = DriverManager.getConnection(dbURL, username, password);
            if (conx != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @AfterTest
    public void closeConnection() throws SQLException {
        results.close();
        statement.close();
        conx.close();
    }
}
