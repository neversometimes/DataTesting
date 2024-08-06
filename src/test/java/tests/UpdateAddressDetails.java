package tests;

import base.BaseTests;

import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class UpdateAddressDetails extends BaseTests {

    @Test
    public void updateAddress() throws SQLException {


        executeQuery("select * from film where film_id = '99';");

        while (results.next()) {  //iterates by row from SQL query
            assertEquals(results.getString("title"), "BRINGING HYSTERICAL");
            assertEquals(results.getString("rental_rate"), "2.99");
            assertEquals(results.getString("length"), "136");
            assertEquals(results.getString("rating"), "PG");
        }
    }

}
