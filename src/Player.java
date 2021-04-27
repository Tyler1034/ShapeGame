import java.awt.*;

public class Player extends Shape{

    public Player(Color color, int x, int y, int width, int height){
        super(color, x, y, width, height);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);

    }

}
