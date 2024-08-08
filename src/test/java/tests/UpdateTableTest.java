package tests;

import java.sql.*;
import org.testng.annotations.Test;
import base.BaseTests;

import static org.testng.Assert.assertEquals;

@Test (dependsOnGroups = "b", groups = "c")
public class UpdateTableTest {

    // assumes test_tbl has been created and populated with data
    public void addRecordToTable() throws SQLException {
        String addRecQuery = "INSERT INTO test_tbl " +
                "(name, email, country, password) VALUES " +
                "('harry houdini', 'harryh@magicshop.com', 'USA', 'pp@@zzW3Rdd')";

        try (Connection connection = BaseTests.connectDB();
             Statement statement = connection.createStatement()) {
            statement.execute(addRecQuery);
            System.out.println("New record added ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String chkAddSQL = "SELECT * FROM test_tbl WHERE name = 'harry houdini'";
        try (Connection conx = BaseTests.connectDB();
             Statement statement = conx.createStatement();
             ResultSet results = statement.executeQuery(chkAddSQL)) {
            while (results.next()) {  // expect single result record
                assertEquals(results.getString("name"), "harry houdini");
                assertEquals(results.getString("email"), "harryh@magicshop.com");
                assertEquals(results.getString("country"), "USA");
                assertEquals(results.getString("password"), "pp@@zzW3Rdd");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // assumes single, new record has been added to test_tbl
    public void deleteRecordFromTable() throws SQLException {
        String delRecQuery = "DELETE FROM test_tbl WHERE name = 'harry houdini'";
        try (Connection conx = BaseTests.connectDB();
             Statement statement = conx.createStatement()) {
             statement.execute(delRecQuery);
             System.out.println("Record deleted ok!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // verify the record actually got deleted
        String chkDelSQL = "SELECT * FROM test_tbl WHERE name = 'harry houdini'";
        try (Connection conx = BaseTests.connectDB();
             Statement statement = conx.createStatement();
             ResultSet results = statement.executeQuery(chkDelSQL)) {
            while (results.next()) {  // expect single result record
                assertEquals(results.getString("name"), "null");
                assertEquals(results.getString("email"), "null");
                assertEquals(results.getString("country"), "null");
                assertEquals(results.getString("password"), "null");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
