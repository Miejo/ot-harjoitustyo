package dao;

import java.util.HashMap;

public interface SettingsDao {
    void create(HashMap<String, String> settings) throws Exception;
    HashMap<String, String> getSettings();
}
