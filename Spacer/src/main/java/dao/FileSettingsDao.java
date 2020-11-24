package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class FileSettingsDao implements SettingsDao {
    private HashMap<String, String> settings;
    private String file;
    
    public FileSettingsDao(String file) throws Exception {
        this.file = file;
        settings = new HashMap<>();
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(":");
                settings.put(parts[0], parts[1]);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.write("username:username\n");
            settings.put("username", "username");
            writer.write("color:white\n");
            settings.put("color", "white");
            writer.write("controls:arrow");
            settings.put("controls", "arrow");
            writer.close();
        }
    }
    
    @Override
    public void create(HashMap<String, String> settings) {
        this.settings = settings;
        try {
            FileWriter writer = new FileWriter(new File(file));
            writer.write("username:" + settings.get("username") + "\n");
            writer.write("color:" + settings.get("color") + "\n");
            writer.write("controls:" + settings.get("controls"));
            writer.close();
            System.out.println("saved");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public HashMap<String, String> getSettings() {
        return settings;
    }
}
