package tests;

import base.BaseTests;
import org.testng.annotations.Test;

import java.sql.SQLException;

//  The methods in the test group are ordered groups "a" through "c" to ensure test dependencies
//  and run order is maintained.  Group:
//      a. The test table is created and populated with data (using SQL CREATE)
//      b. Two read queries are executed and verified
//      c. Two update queries are executed and verified
//      d. The delete table method found in BaseTests (dependent on "c") is executed last as clean up.

public class CreateAndDeleteTableTest {

    @Test (groups = "a")
    public void createTestTable() throws SQLException {
       BaseTests.createTableWithData();
    }

    @Test (dependsOnGroups = "c")
    public void cleanUpTestTable() throws SQLException {
        BaseTests.deleteTable();
    }

}
