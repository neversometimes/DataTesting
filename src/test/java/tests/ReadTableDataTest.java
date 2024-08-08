package tests;

import java.sql.*;
import org.testng.annotations.Test;
import base.BaseTests;

import static org.testng.Assert.assertEquals;

@Test (dependsOnGroups = "a", groups = "b")
public class ReadTableDataTest {

    // this test depends on test_tbl having been created and populated with data
    public void verifySingleRecord() throws SQLException {

        String inSQL = "SELECT * FROM test_tbl WHERE id = '9'";

        try (Connection conx = BaseTests.connectDB();
             Statement statement = conx.createStatement();
             ResultSet results = statement.executeQuery(inSQL)) {

            while (results.next()) {  // expect single result record
                assertEquals(results.getString("name"), "helga schwartzkopf");
                assertEquals(results.getString("email"), "helga167@flugel.com");
                assertEquals(results.getString("country"), "DEU");
                assertEquals(results.getString("password"), "$chweinI5tGUT");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // this test is not dependent on test_tbl being created
    public void verifyJoinedTableQueryData() throws SQLException {
        //  query sample database for video rental data:
        //  --> Return the last name and total of the person that has rented the most videos

        String inSQL = "SELECT last_name, COUNT(rental_id) AS total " +
                       "FROM customer AS c " +
                       "JOIN rental as r " +
                       "ON c.customer_id=r.customer_id " +
                       "GROUP BY last_name " +
                       "ORDER BY total DESC " +
                       "limit 1" ;

        try (Connection conx = BaseTests.connectDB();
             Statement statement = conx.createStatement();
             ResultSet results = statement.executeQuery(inSQL)) {

            while (results.next()) {  // expect single result record
                assertEquals(results.getString("last_name"), "HUNT");
                assertEquals(results.getString("total"), "46");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
