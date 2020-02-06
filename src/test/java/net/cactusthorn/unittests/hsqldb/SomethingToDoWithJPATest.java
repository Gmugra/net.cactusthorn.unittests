package net.cactusthorn.unittests.hsqldb;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hsqldb.cmdline.SqlFile;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SomethingToDoWithJPATest {

    static EntityManagerFactory factory;

    @BeforeClass
    public static void startUpClass() throws Exception {
        Path path = Paths.get(SomethingToDoWithJPATest.class.getResource("TestDB.sql").toURI());
        try (BufferedReader buf = Files.newBufferedReader(path, StandardCharsets.UTF_8);
                Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:testDB", "sa", "")) {

            SqlFile script = new SqlFile(buf, "", System.out, StandardCharsets.UTF_8.toString(), false,
                    (java.net.URL) null);
            script.setConnection(connection);
            script.execute();
        }
        factory = Persistence.createEntityManagerFactory("TEST_DB");
    }
    
    @AfterClass
    public static void shutDownClass() {
        factory.close();
    }

    @Test
    public void test() throws SQLException {
        SomethingToDoWithJPA std = new SomethingToDoWithJPA(factory);
        assertEquals("VALUE1", std.getCol1());
    }
}
