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
    private long timeBetweenShots;
    private long lastShotTime;
    private long timeBetweenEnemies;
    private long lastEnemyTime;
    
    private int score;
    private boolean endGame;
    
    public Game() {
        initGame();
    }
    
    private void initGame() {
        player = new Player();
        bullets = new ArrayList<>();
        deleteBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        deleteEnemies = new ArrayList<>();
        lastShotTime = 0;
        lastEnemyTime = 0;
        score = 0;
        prevTime = System.nanoTime();
        endGame = false;
    }
    
    public void update(long currentNanoTime, ArrayList<String> input) {
        curTime = currentNanoTime / 1000000000;
        float deltaTime = (float) (currentNanoTime - prevTime) / 1000000000;
        timeBetweenShots = curTime - lastShotTime;
        timeBetweenEnemies = curTime - lastEnemyTime;
        
        if (input.contains("LEFT")) {
            player.moveLeft(deltaTime);
        }
        
        if (input.contains("RIGHT")) {
            player.moveRight(deltaTime);
        }
        
        if (input.contains("SPACE") && timeBetweenShots > 0.5) {
            bullets.add(new Bullet(player.getPositionX() + 28, player.getPositionY()));
            lastShotTime = curTime;
        }
        
        if (timeBetweenEnemies > 1) {
            enemies.add(new Enemy());
            lastEnemyTime = curTime;
        }
        
        for (Bullet b : deleteBullets) {
            bullets.remove(b);
        }

        for (Enemy e : deleteEnemies) {
            enemies.remove(e);
        }

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

        for (Enemy e : enemies) {
            e.update(deltaTime);            
            if (e.outOfBounds()) {
                deleteEnemies.add(e);
                endGame = true;
            }
        }
        
        prevTime = currentNanoTime;
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
}