import java.awt.*;

public class Bullet extends Shape{

    public Bullet(Color color, int x, int y, int diameter){
        super(color, x, y, diameter, diameter);


    }

    public void paint(Graphics g){

        g.setColor(color);
        g.fillOval(x, y, width, height);
    }


}
