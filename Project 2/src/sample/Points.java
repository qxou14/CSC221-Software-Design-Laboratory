package sample;

public class Points
{
    private double x;
    private double y;
    Points(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    void setX(double x)
    {
        this.x = x;
    }

    void setY(double y)
    {
        this.y = y;
    }
    double getX()
    {
        return x;
    }
    double getY()
    {
        return y;
    }

    public String toString() //returning string of x and y
    {
        return x+" "+y;
    }
}