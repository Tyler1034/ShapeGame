import java.awt.*;

public class Shape {

    int width, height, x, y, init_x, init_y;
    boolean remove = false;
    double dx = 2, dy = 2;
    Color color;

    public Shape(Color color, int x, int y, int width, int height){
        this.color = color;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        init_x = x;
        init_y = y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)(x + dx), (int)(y + dy), width, height);
    }

    public boolean collidesWidth(Shape other){

        return this.getBounds().intersects(other.getBounds());

    }

    public void setRemove(boolean input){
        remove = input;
    }

    public boolean isRemove(){
        return remove;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void paint(Graphics g){

    }

    public void move(int minWidth, int maxWidth, int minHeight, int maxHeight, boolean horizontal, boolean vertical){

        double nextTop = y + dy;
        double nextBottom = y + height + dy;
        double nextLeft = x + dx;
        double nextRight = x + width + dx;

        if(nextLeft <= minWidth || nextRight >= maxWidth){
            dx *= -1;
        }

        if(nextTop <= minHeight || nextBottom >= maxHeight){
            dy *= -1;
        }

       if(horizontal){
           x+=dx;
       }

       if(vertical){
            y+=dy;
        }


    }
}
