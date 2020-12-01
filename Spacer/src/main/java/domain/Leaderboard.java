package domain;

import dao.FileLeaderboardDao;
import java.util.ArrayList;

public class Leaderboard {
    private FileLeaderboardDao leaderboard;
    
    public Leaderboard(FileLeaderboardDao leaderboard) {
        this.leaderboard = leaderboard;
    }

    public ArrayList<String> getTopTen() {
        ArrayList<String> leaderboardList = leaderboard.getTopTen();
        return leaderboardList;
    }
    
    public boolean addScore(String name, int score) {
        try {
            leaderboard.addScore(name, score);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
