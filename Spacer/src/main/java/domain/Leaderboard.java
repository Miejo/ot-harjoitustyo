package domain;

import dao.FileLeaderboardDao;
import java.util.LinkedHashMap;

public class Leaderboard {
    private FileLeaderboardDao leaderboard;
    
    public Leaderboard(FileLeaderboardDao leaderboard) {
        this.leaderboard = leaderboard;
    }

    public LinkedHashMap<String, Integer> getTopTen(){
        LinkedHashMap<String, Integer> leaderboardMap = leaderboard.getTopTen();
        return leaderboardMap;
    }
    
    public boolean addScore(String name, int score){
        try {
            leaderboard.addScore(name, score);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
