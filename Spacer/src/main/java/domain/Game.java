package domain;

import java.util.ArrayList;
/**
 * Luokka huolehtii pelin päälogiikasta ja käyttää muita logiikkaluokkia.
 */
public class Game {
    
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> deleteBullets;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> deleteEnemies;
    
    private long curTime;
    private long prevTime;
    private long startTime;
    private long timeBetweenShots;
    private long lastShotTime;
    private long timeBetweenEnemies;
    private long lastEnemyTime;
    private long timeElapsed;
    private float deltaTime;
    
    private int score;
    private boolean endGame;
    
    private String left;
    private String right;
    
    /**
     * Konstruktori, joka kutsuu initGame metodia, joka alustaa pelin.
     * 
     * @param controls Asetuksista controls, joko "WASD" tai "arrow"
     */
    
    public Game(String controls) {
        initGame(controls);
    }
    
    private void initGame(String controls) {
        player = new Player();
        bullets = new ArrayList<>();
        deleteBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        deleteEnemies = new ArrayList<>();
        startTime = System.nanoTime();
        lastShotTime = 0;
        lastEnemyTime = 0;
        prevTime = startTime;
        timeElapsed = 0;
        score = 0;
        endGame = false;
        left = "LEFT";
        right = "RIGHT";
        if (controls.equals("arrow")) {
            left = "A";
            right = "D";
        }
    }
    /**
     * Game loop, huolehtii pelin päivittämisestä, kutsutaan kerran framessa.
     * 
     * @param currentNanoTime handle metodista saatava tämän hetken aika
     * @param input Käyttäjän syöte
     */
    public void update(long currentNanoTime, ArrayList<String> input) {
        curTime = currentNanoTime / 1000000000;
        deltaTime = (float) (currentNanoTime - prevTime) / 1000000000;
        timeElapsed = (currentNanoTime - startTime) / 1000000000;
        timeBetweenShots = curTime - lastShotTime;
        timeBetweenEnemies = curTime - lastEnemyTime;
        
        playerMovement(deltaTime, input);
        
        if (input.contains("SPACE") && timeBetweenShots > 0.5) {
            addBullet();
            lastShotTime = curTime;
        }
        
        spawnEnemies();
        
        destroyBullets();
        destroyEnemies();
        updateBullets(deltaTime);
        updateEnemies(deltaTime);
        
        prevTime = currentNanoTime;
    }
    
    /**
     * Metodi kutsuu player-luokan move-metodeja, riippuen käyttäjän syötteestä
     * 
     * @param deltaTime Kahden update-syklin väliin kuluva aika
     * @param input Käyttäjän syöte
     */
    
    public void playerMovement(float deltaTime, ArrayList<String> input) {
        if (input.contains(left)) {
            player.moveLeft(deltaTime);
        }
        
        if (input.contains(right)) {
            player.moveRight(deltaTime);
        }
    }
    
    /**
     * Metodi lisää ammuksista kirjaa pitävään ArrayList:iin uusi Bullet-olio.
     */
    
    public void addBullet() {
        bullets.add(new Bullet(player.getPositionX() + 28, player.getPositionY()));
    }
    
    /**
     * Metodi lisää vihollisista kirjaa pitävään ArrayList:iin uusi Enemy-olio.
     */
    public void addEnemy() {
        enemies.add(new Enemy());
    }
    
    /**
     * Metodi poistaa ammuksista kirjaa pitävästä ArrayList:sta Bullet-oliot,
     * jotka on tarkoitus poistaa.
     */
    public void destroyBullets() {
        deleteBullets.forEach((b) -> {
            bullets.remove(b);
        });
    }
    
    /**
     * Metodi poistaa vihollisista kirjaa pitävästä ArrayList:sta Enemy-oliot,
     * jotka on tarkoitus poistaa.
     */
    public void destroyEnemies() {
        deleteEnemies.forEach((e) -> {
            enemies.remove(e);
        });
    }
    
    /**
     * Metodi kutsuu jokaisen Enemy-olion update metodia ja
     * tarkistaa onko olio ruudun ulkopuolella.
     * @param deltaTime Kahden update-syklin väliin kuluva aika
     */
    public void updateEnemies(float deltaTime) {
        for (Enemy e : enemies) {
            e.update(deltaTime);            
            if (e.outOfBounds()) {
                deleteEnemies.add(e);
                endGame = true;
            }
        }
    }
    
    /**
     * Metodi kutsuu jokaisen Bullet-olion update metodia ja
     * tarkistaa onko olio ruudun ulkopuolella sekä ammuksen ja vihollisen mahdollisen törmäyksen.
     * @param deltaTime Kahden update-syklin väliin kuluva aika
     */
    public void updateBullets(float deltaTime) {
        for (Bullet b : bullets) {
            b.update(deltaTime);
            if (b.outOfBounds()) {
                deleteBullets.add(b);
            }
            for (Enemy e :enemies) {
                if (b.getCollisionBox().intersects(e.getCollisionBox())) {
                    deleteBullets.add(b);
                    deleteEnemies.add(e);
                    score++;
                }
            }
        }
    }
    
    /**
     * Metodi hoitaa vihollisten tulemisen ja pelin vaikeutumisen.
     */
    
    public void spawnEnemies() {
        if (timeElapsed < 10 && timeBetweenEnemies > 1) {
            addEnemy();
            lastEnemyTime = curTime;
        } else if (timeElapsed < 20 && timeBetweenEnemies > 0.5) {
            addEnemy();
            lastEnemyTime = curTime;
        } else if (timeElapsed < 30 && timeBetweenEnemies > 0.1) {
            addEnemy();
            lastEnemyTime = curTime;
        } else if (timeElapsed >= 30 && timeBetweenEnemies > 0.1) {
            addEnemy();
            addEnemy();
            lastEnemyTime = curTime;
        }
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    
    public int getScore() {
        return score;
    }
    
    public boolean getEndGame() {
        return endGame;
    }
    
    public long getTimeElapsed() {
        return timeElapsed;
    }
    
    public float getDeltaTime() {
        return deltaTime;
    }
}