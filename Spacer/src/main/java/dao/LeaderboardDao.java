package dao;

import java.sql.Connection;
import java.util.LinkedHashMap;

public interface LeaderboardDao {
    Connection connect() throws Exception;
    void addScore(String name, int score) throws Exception;
    LinkedHashMap<String, Integer> getTopTen() throws Exception;
}
