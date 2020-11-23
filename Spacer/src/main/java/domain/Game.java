package domain;

import java.util.ArrayList;

public class Game{
    
    private Player player;
    private ArrayList<String> input;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> deleteBullets;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> deleteEnemies;
    // private long prevNanoTime;
    
    public Game (ArrayList<String> input) {
        this.input = input;
        initGame();
    }
    
    private void initGame(){
        player = new Player();
        bullets = new ArrayList<>();
        deleteBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        deleteEnemies = new ArrayList<>();
        enemies.add(new Enemy());
    }
    
    public void update(long currentNanoTime, ArrayList<String> _input){
        
        this.input = _input;
        
        if (input.contains("LEFT")){
            player.moveLeft();
        }
        if (input.contains("RIGHT")){
            player.moveRight();
        }
        if (input.contains("SPACE")){
            bullets.add(new Bullet(player.getPositionX(), player.getPositionY()));
        }

        for (Bullet b : deleteBullets) {
            bullets.remove(b);
        }

        for (Enemy e : deleteEnemies) {
            enemies.remove(e);
        }

        for (Bullet b : bullets) {
            b.update();
            if (b.outOfBounds()) {
                deleteBullets.add(b);
            }
            for (Enemy e :enemies){
                if (b.getCollisionBox().intersects(e.getCollisionBox())) {
                    deleteBullets.add(b);
                    deleteEnemies.add(e);
                }
            }
        }

        for (Enemy e : enemies) {
            e.update();            
            if (e.outOfBounds()) {
                deleteEnemies.add(e);
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
}