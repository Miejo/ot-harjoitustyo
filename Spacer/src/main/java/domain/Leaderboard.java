package domain;

import dao.FileLeaderboardDao;
import java.util.ArrayList;

/**
 * Luokka dao:n ja ui:n välillä, joka huolehtii tulostaulusta.
 */
public class Leaderboard {
    private FileLeaderboardDao leaderboard;
    
    /**
     * Konstruktori, joka asettaa oikean dao:n
     * @param leaderboard Dao, joka käyttää oikeaa tietokantaa
     */
    public Leaderboard(FileLeaderboardDao leaderboard) {
        this.leaderboard = leaderboard;
    }
    
    /**
     * Metodi, joka palauttaa 10 parasta tulosta.
     * @return palauttaa ArrayList:nä 10 parasta tulosta.
     */
    public ArrayList<String> getTopTen() {
        ArrayList<String> leaderboardList = leaderboard.getTopTen();
        return leaderboardList;
    }
    
    /**
     * Metodi, joka lisää tulostietokantaan uuden tuloksen.
     * @param name Tuloksen tehneen pelaajan nimi
     * @param score Tulos
     * @return true, jos lisääminen onnistui, muutoin false
     */
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
