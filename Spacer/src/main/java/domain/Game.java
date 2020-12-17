package domain;

import java.util.ArrayList;

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
    
    private int score;
    private boolean endGame;
    
    private String left;
    private String right;
    
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
    
    public void update(long currentNanoTime, ArrayList<String> input) {
        curTime = currentNanoTime / 1000000000;
        float deltaTime = (float) (currentNanoTime - prevTime) / 1000000000;
        timeElapsed = (currentNanoTime - startTime) / 1000000000;
        timeBetweenShots = curTime - lastShotTime;
        timeBetweenEnemies = curTime - lastEnemyTime;
        
        playerMovement(deltaTime, input);
        
        if (input.contains("SPACE") && timeBetweenShots > 0.5) {
            addBullet();
            lastShotTime = curTime;
        }
        
        if (timeElapsed < 10) {
            if (timeBetweenEnemies > 1) {
                addEnemy();
                lastEnemyTime = curTime;
            }
        } else if (timeElapsed < 20) {
            if (timeBetweenEnemies > 0.5) {
                addEnemy();
                lastEnemyTime = curTime;
            }
        } else if (timeElapsed < 30) {
            if (timeBetweenEnemies > 0.1) {
                addEnemy();
                lastEnemyTime = curTime;
            }
        } else if (timeElapsed >= 30) {
            if (timeBetweenEnemies > 0.1) {
                addEnemy();
                addEnemy();
                lastEnemyTime = curTime;
            }
        }
        
        destroyBullets();
        destroyEnemies();
        updateBullets(deltaTime);
        updateEnemies(deltaTime);
        
        prevTime = currentNanoTime;
    }
    
    public void playerMovement(float deltaTime, ArrayList<String> input) {
        if (input.contains(left)) {
            player.moveLeft(deltaTime);
        }
        
        if (input.contains(right)) {
            player.moveRight(deltaTime);
        }
    }
    
    public void addBullet() {
        bullets.add(new Bullet(player.getPositionX() + 28, player.getPositionY()));
    }
    
    public void addEnemy() {
        enemies.add(new Enemy());
    }
    
    public void destroyBullets() {
        deleteBullets.forEach((b) -> {
            bullets.remove(b);
        });
    }
    
    public void destroyEnemies() {
        deleteEnemies.forEach((e) -> {
            enemies.remove(e);
        });
    }
    
    public void updateEnemies(float deltaTime) {
        for (Enemy e : enemies) {
            e.update(deltaTime);            
            if (e.outOfBounds()) {
                deleteEnemies.add(e);
                endGame = true;
            }
        }
    }
    
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
}