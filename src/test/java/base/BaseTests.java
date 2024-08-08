package base;

import java.sql.*;

public class BaseTests {

    private static String dbURL = "jdbc:mysql://localhost:3306/sakila";
    private static String username = "root";
    private static String password = "********";    // obfuscating since this will be public soon

    public static Connection connectDB() throws SQLException {
        return DriverManager.getConnection(dbURL, username, password);
    }

    public static void createTableWithData() throws SQLException {
        String testTableSchema =
        "CREATE TABLE IF NOT EXISTS test_tbl " +
                "(" + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(40), "
                + "email VARCHAR(100), "
                + "country VARCHAR(10), "
                + "password VARCHAR(25)"
                + ")";

        String tableData = "INSERT INTO test_tbl " +
                "(name, email, country, password) VALUES "
                + "('john doe', 'jdoe@barnstormers.com', 'AUS', 'rose6ud333'), "
                + "('jane sanders', 'janiac@starbucks.com', 'USA', 'br3@tht@kin6'), "
                + "('george peterson', 'georgepe@hotmail.com', 'IRL', 'hellzhighway12121'), "
                + "('lena white', 'lenawh@gmail.com', 'UK', 'salTY__dog$'), "
                + "('bob zychck', 'zychck@timhortons.com', 'CAN', 'mol$ons_rocKs!'), "
                + "('bill gates', 'billg@microsoft.com', 'USA', 'azuremember1975'), "
                + "('joe rogan', 'joerogan@rogan.com', 'USA', 'poDCAST_sw33tn35'), "
                + "('gern blanstan', 'gernblan@sveirge.gov', 'SWE', 'nandor%56RAAMto'), "
                + "('helga schwartzkopf', 'helga167@flugel.com', 'DEU', '$chweinI5tGUT')";

        try (Connection connection = connectDB();
             Statement statement = connection.createStatement()) {
            statement.execute(testTableSchema);
            System.out.println("Table created ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        insertRecords(tableData);
    }
    public static void insertRecords(String insertSQL) throws SQLException {

        try (Connection connection = connectDB();
             Statement statement = connection.createStatement()) {
            int rowsIn = statement.executeUpdate(insertSQL);
            System.out.println(rowsIn + " rows inserted ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTable() throws SQLException {
        String deleteTableSQL = "DROP TABLE test_tbl";

        try (Connection connection = connectDB();
             Statement statement = connection.createStatement()) {
            statement.execute(deleteTableSQL);
            System.out.println("Table deleted ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
