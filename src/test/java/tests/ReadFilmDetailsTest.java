package tests;

import base.BaseTests;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class ReadFilmDetailsTest extends BaseTests {

    @Test
    public void verifyFilmDetails() throws SQLException {

        executeReadQuery("select * from film where film_id = '99';");

        while (results.next()) {  //iterates row by row from SQL query - expect only single result record
            assertEquals(results.getString("title"), "BRINGING HYSTERICAL");
            assertEquals(results.getString("rental_rate"), "2.99");
            assertEquals(results.getString("length"), "136");
            assertEquals(results.getString("rating"), "PG");
        }
    }


}
