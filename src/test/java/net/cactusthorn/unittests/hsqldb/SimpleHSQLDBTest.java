package net.cactusthorn.unittests.hsqldb;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.hsqldb.cmdline.SqlFile;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class SimpleHSQLDBTest {

    static Connection CON;

    @BeforeClass
    public static void startUpClass() throws Exception {

        CON = DriverManager.getConnection("jdbc:hsqldb:mem:testDB", "sa", "");

        Path path = Paths.get(SimpleHSQLDBTest.class.getResource("TestDB.sql").toURI());
        try (BufferedReader buf = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            SqlFile script = new SqlFile(buf, "", System.out, StandardCharsets.UTF_8.toString(), false,
                    (java.net.URL) null);
            script.setConnection(CON);
            script.execute();
        }
    }

    @AfterClass
    public static void shutDownClass() throws SQLException {
        CON.close();
    }

    @Test
    public void test() throws SQLException {
        SomethingToDoWithJDBC std = new SomethingToDoWithJDBC();
        assertEquals("VALUE1", std.getCol1(CON));
    }

}
