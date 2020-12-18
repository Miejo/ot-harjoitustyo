package domain;

import dao.FileLeaderboardDao;
import dao.LeaderboardDao;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class LeaderboardTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    File leaderboardFile;
    FileLeaderboardDao dao;
    Leaderboard leaderboard;
    
    @Before
    public void setUp() throws Exception {
        leaderboardFile = tempFolder.newFile("leaderboard.db");
        dao = new FileLeaderboardDao(leaderboardFile.getAbsolutePath());
        leaderboard = new Leaderboard(dao);
    }
    
    @Test
    public void addingScoresWorksCorrectly() {
        leaderboard.addScore("Test1", 1);
        leaderboard.addScore("Test2", 2);
        leaderboard.addScore("Test3", 0);
        ArrayList<String> scores = leaderboard.getTopTen();
        assertEquals("Test2|2", scores.get(0));
        assertEquals("Test1|1", scores.get(1));
        assertEquals("Test3|0", scores.get(2));
    }
    
    @After
    public void tearDown() {
        leaderboardFile.delete();
    }
}
