package net.cactusthorn.unittests.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SomethingToDoWithJDBC {

    public String getCol1(Connection connection) throws SQLException {

        try (PreparedStatement statment = connection.prepareStatement("select COL1 from TESTTABLE");
                ResultSet result = statment.executeQuery()) {

            result.next();
            return result.getString("COL1");
        }
    }

}
