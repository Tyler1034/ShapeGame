import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {


    ArrayList<Shape> actors = new ArrayList<>();
    ArrayList<Shape> bullets = new ArrayList<>();
    ArrayList<Shape> missiles = new ArrayList<>();

    final int _E_WIDTH = 50;
    final int _E_HEIGHT = 20;
    final int _P_WIDTH = 20;
    final int _P_HEIGHT = 50;
    int pLives = 3;

    int h_space;

    boolean firstTime = true;

    //final int NUMENEMIES = STATS._NUMENEMIES;


    Timer timer;

    public Board() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        timer = new Timer(1000 / 60, this);
        STATS.setLevel(0);

    }

    public void setup() {
        h_space = getWidth()/STATS._NUMENEMIES;
       // int x_position = (int)((h_space/2.0) - (_E_WIDTH/2.0));
        if(firstTime){
            actors.add(0, new Player(Color.BLUE, 400, 500, _P_WIDTH, _P_HEIGHT));
        }


        for(int i = 0; i < STATS._NUMENEMIES; i++) {
            for (int j = 0; j < STATS._NUMENEMIES; j++)
                actors.add(new Enemy(Color.RED, i* h_space, j * 40, _E_WIDTH, _E_HEIGHT));
            }
        firstTime = false;
            timer.start();

        }

    public void setPlayerPos(int x, int y){
        actors.get(0).setPosition(x, y);
    }

    public void addBullet(){
        bullets.add(new Bullet(Color.GREEN, actors.get(0).x, actors.get(0).y, 10));
    }

    public void addMissile(){

        for(int i = 1; i < actors.size(); i++){
            int rand = (int)(Math.random()*1000);
            if(rand < 5){
                System.out.println("bullet added to array");
                missiles.add(new Bullet(Color.yellow, actors.get(i).x, actors.get(i).y, 10));
            }
        }

    }

    public void checkCollisions() {

        //if bullets collide with enemies


        for (Shape bullet : bullets) {
            for (Shape enemy : actors) {

                if (bullet.collidesWidth(enemy) && enemy instanceof Enemy) {
                    enemy.setRemove(true);
                    bullet.setRemove(true);
                }
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).isRemove()) {
                bullets.remove(i);

            }
        }
        for (int i = 1; i < actors.size(); i++) {
            if (actors.get(i).isRemove()) {
                actors.remove(i);
            }
        }

        for(int i = 0; i < missiles.size(); i ++){
            if(missiles.get(i).collidesWidth(actors.get(0))){
                pLives--;
                missiles.get(i).setRemove(true);

                if(pLives == 2){
                    actors.get(0).setColor(Color.cyan);
                }else if(pLives == 1){
                    actors.get(0).setColor(Color.magenta);
                }else if(pLives == 0){
                    timer.stop();
                }
            }
        }
        for(int i = 0; i < missiles.size(); i ++){
            if(missiles.get(i).isRemove()){
                missiles.remove(i);
            }
        }

        if(actors.size() - 1 == 0){
            STATS.setLevel(1);
            setup();
        }
    }

        public void paintComponent (Graphics g){
            super.paintComponent(g);

            for (Shape actor : actors) {
                actor.paint(g);
            }

            for (Shape bullet : bullets) {
                bullet.paint(g);
            }

            for(Shape missile:missiles){
                missile.paint(g);
            }
        }


    @Override
    public void actionPerformed(ActionEvent e) {

        checkCollisions();
        addMissile();

        for(int i = 1; i < actors.size(); i ++){
            Shape actor = actors.get(i);
            actor.move(actor.init_x,actor.init_x + h_space, actor.init_y, 0,true, false);
        }

        for(int i = 0; i < bullets.size(); i ++){
            Shape bullet = bullets.get(i);
            bullet.dy = 8;
            bullet.move(bullet.x, bullet.x, getHeight(), 0, false, true);
        }
        for(int i = 0; i < missiles.size(); i ++){
            Shape missile = missiles.get(i);

            missile.dy = -8;
            missile.move(missile.x, missile.x, getHeight(),0, false, true);
        }
        repaint();
    }
}
