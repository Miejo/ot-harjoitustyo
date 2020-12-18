package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileLeaderboardDaoTest {
        
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    File leaderboardFile;
    LeaderboardDao dao;
    
    @Before
    public void setUp() throws Exception {
        leaderboardFile = tempFolder.newFile("leaderboard.db");
        dao = new FileLeaderboardDao(leaderboardFile.getAbsolutePath());
    }
    
    @Test
    public void databaseCreatedCorrectly() throws Exception {
        Connection conn = dao.connect();
        String sql = "SELECT name FROm sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'";
        Statement sment = conn.createStatement();
        ResultSet rSet = sment.executeQuery(sql);
        String tables = "";
        while (rSet.next()) {
            tables = tables + rSet.getString("name");
        }
        assertEquals("Scores", tables);
    }
    
    @Test
    public void addingScoreWorks() throws Exception {
        dao.addScore("Test", 100);
        Connection conn = dao.connect();
        String sql = "SELECT name, score FROM Scores";
        Statement sment = conn.createStatement();
        ResultSet rSet = sment.executeQuery(sql);
        String scores = "";
        while (rSet.next()) {
            scores = scores + rSet.getString("name") + "|" + rSet.getInt("score");
        }
        assertEquals("Test|100", scores);
    }
    
    @Test
    public void gettingTopTenCorrectly() throws Exception {
        dao.addScore("Test1", 1);
        dao.addScore("Test2", 2);
        dao.addScore("Test3", 0);
        ArrayList<String> scores = dao.getTopTen();
        assertEquals("Test2|2", scores.get(0));
        assertEquals("Test1|1", scores.get(1));
        assertEquals("Test3|0", scores.get(2));
    }
    
    @After
    public void tearDown() {
        leaderboardFile.delete();
    }
}
