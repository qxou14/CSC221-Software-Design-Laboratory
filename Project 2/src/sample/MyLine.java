package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public class MyLine extends MyShape
{
    private double length;
    private double angle;
    private double x2;
    private double y2;
    static Points P; // using the points class i created
    static Points P1;

    MyLine(double x1 ,double y1,double x2,double y2){
        super(x1,y1);  //store values into x and y from super class
        this.x2 = x2;
        this.y2 =y2;
        Points P = new Points(super.x,super.y); // calling the super  class's variable
        this.P = P;

        Points P1 = new Points(x2,y2);
        this.P1 = P1;
    }
    public double getLength()
    {
        length = Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
        return length;
    }

    public double get_xAngle()
    {
        angle = Math.toDegrees(Math.atan((y2-y)/(x2-x)));
        return angle;
    }
    public String toString()
    {
        return "This line has a length of: " + getLength()+ " and an angle of: " +angle+"\u00B0.";
    }
    public void draw(GraphicsContext gc)
    {
        gc.setLineWidth(1.0);
        gc.setStroke(C.getColor());
        gc.strokeLine(x,y,x2,y2);

    }

    public Points getPoint(double z) // we can enter any number here to get the 1st point.
    {
        return P;
    }
    //overloading
    @Override
    public Points getPoint() //without enter any number we can get the second point
    {
        return P1;
    }

    @Override
    public void moveTo(double x,double y)
    {
        x2 += x;
        y2 += y;
    }

    @Override
    public void setPoint(double x,double y)
    {
        x2 = x;
        y2 = y;
    }

    @Override
    public double distanceTo(double x, double y, double x1, double y2)
    {
        return length;

    }

    @Override
    public MyRectangle getMyBoundingBox() //we need a rectangle that has left upper coordinates with the heigth and width equal to x2 and y2
    {
        return new MyRectangle(super.x,super.y,x2,y2);
    }



    @Override
    public boolean doOverlap(MyOval oval) {
        MyLine line = new MyLine(super.x,super.y,x2,y2);

        double i = 10;
        while(i>0) //x2 is the 2nd x coordinate
        {
            if(Math.round((line.x2)/i) == Math.round(oval.getX()))
            {
                return true;

            }
            i = i-0.1;
        }
        return false;



    }

    @Override
    public boolean doOverlap(MyRectangle rect) {
        MyLine line = new MyLine(super.x,super.y,x2,y2);
        double i = 10;
        while(i>0)
        {
            if(Math.round(x2/i) == Math.round(rect.getX()))
            {
                return true;
            }

            i = i-0.1;

        }

        return false;
    }
}
