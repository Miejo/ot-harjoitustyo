package dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface LeaderboardDao {
    Connection connect() throws Exception;
    void addScore(String name, int score) throws Exception;
    ArrayList<String> getTopTen() throws Exception;
}
