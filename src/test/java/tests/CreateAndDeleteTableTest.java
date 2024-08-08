package tests;

import base.BaseTests;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;


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
