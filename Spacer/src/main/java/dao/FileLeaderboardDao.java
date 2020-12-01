package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

public class FileLeaderboardDao implements LeaderboardDao {
    private String sql;
    private String url;
    
    public FileLeaderboardDao(String file) throws Exception {
        this.url = "jdbc:sqlite:" + file;
        Connection conn = this.connect();
        try {
            sql = "CREATE TABLE IF NOT EXISTS Scores (id INTEGER PRIMARY KEY, name TEXT, score INT)";
            Statement sment = conn.createStatement();
            sment.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public Connection connect() {
        Connection conn = null;
        try {
           conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    @Override
    public void addScore(String name, int score) {
        try {
            Connection conn = this.connect();
            sql = "INSERT INTO Scores(name, score) VALUES(?,?)";
            PreparedStatement pSment = conn.prepareStatement(sql);
            pSment.setString(1, name);
            pSment.setInt(2, score);
            pSment.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    @Override
    public LinkedHashMap<String, Integer> getTopTen() {
        LinkedHashMap<String, Integer> scores = new LinkedHashMap<>();
        try {
            Connection conn = this.connect();
            sql = "SELECT name, score FROM Scores ORDER BY score DESC LIMIT 10";
            Statement sment = conn.createStatement();
            ResultSet rSet = sment.executeQuery(sql);
            while (rSet.next()) {
                scores.put(rSet.getString("name"), rSet.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return scores;
    }
}
