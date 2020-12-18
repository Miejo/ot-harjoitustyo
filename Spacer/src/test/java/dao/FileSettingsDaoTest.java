package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileSettingsDaoTest {
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    File settingsFile;
    SettingsDao dao;
    
    @Before
    public void setUp() throws Exception {
        settingsFile = tempFolder.newFile("settings.txt");
        try (FileWriter file = new FileWriter(settingsFile.getAbsolutePath())) {
            file.write("username:Tester\n");
            file.write("color:red\n");
            file.write("controls:WASD\n");
        }
        dao = new FileSettingsDao(settingsFile.getAbsolutePath());
    }
    
    @Test
    public void settingsReadCorrectly() {
        HashMap<String, String> settings = dao.getSettings();
        assertEquals("Tester", settings.get("username"));
        assertEquals("red", settings.get("color"));
        assertEquals("WASD", settings.get("controls"));
    }
    
    @Test
    public void newSettingsCreatedSuccessfully() throws Exception {
        HashMap<String, String> settings = new HashMap<>();
        settings.put("username", "TesterTester");
        settings.put("color", "white");
        settings.put("controls", "arrow");
        dao.create(settings);
        SettingsDao newDao = new FileSettingsDao(settingsFile.getAbsolutePath());
        settings = newDao.getSettings();
        assertEquals("TesterTester", settings.get("username"));
        assertEquals("white", settings.get("color"));
        assertEquals("arrow", settings.get("controls"));
    }
    
    @After
    public void tearDown() {
        settingsFile.delete();
    }
}
