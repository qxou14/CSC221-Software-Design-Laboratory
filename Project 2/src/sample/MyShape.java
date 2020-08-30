package sample;

import javafx.scene.canvas.GraphicsContext;



public abstract class MyShape  implements MyShapePosition
{
    protected double x;
    protected double y;
    protected static MyColor C;
    MyShape(double x , double y)
    {
        this.x = x;
        this.y = y;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }

    public MyColor getColor()
    {
        return C;
    }

    public void setX(int newX)
    {
        x = newX;
    }
    public void setY(int newY)
    {
        y = newY;
    }
    public void setColor(MyColor C)
    {this.C = C;}


    public String toString()
    {
        return x+""+y;
    }

    public abstract void draw(GraphicsContext gc);

    public abstract Points getPoint();
    public void setPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public abstract double distanceTo(double x, double y, double x1, double y2);
    public abstract MyRectangle getMyBoundingBox();
    public abstract boolean doOverlap(MyOval oval);
    public abstract boolean doOverlap(MyRectangle rect);

}
