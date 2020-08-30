package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyCircle extends MyShape
{
    private double area;
    private double perimeter;
    private double radius;
    private double width; //width at the center of r
    private double height; // height at the center of r
    static Points p;
    MyCircle(double x1, double y1, double w, double h)
    {
        super(x1,y1);
        Points p = new Points(super.x,super.y);
        this.p = p;

        width = w;
        height = h;
    }
    public double getArea()
    {
        area = Math.PI*radius*radius;
        return area;
    }
    public double getPerimeter()
    {
        perimeter = Math.PI*2*radius;
        return perimeter;
    }

    public double getRadius()
    {
        radius = width;
        return radius;
    }
    @Override
    public String toString()
    {
        return "The radius is "+getRadius()+ ". The perimeter is "+getPerimeter()+ ". The area is "+getArea()+".";
    }


    @Override
    public void draw(GraphicsContext gc)
    {
        gc.setFill(C.getColor());
        gc.fillOval(x,y,width,height);

    }

    @Override
    public Points getPoint() {
        return p;
    }

    @Override
    public void setPoint(double x , double y)
    {
        super.x = x;
        super.y = y;
    }

    @Override
    public void moveTo(double x, double y) {
        super.x += x;
        super.y += y;

    }

    @Override
    public double distanceTo(double x, double y, double x2, double y2) {
        return Math.sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y));
    }

    @Override
    public boolean doOverlap(MyOval oval) {
        double x = oval.getX()+oval.axisX()/2; //how we get the center x
        double y = oval.getY()+oval.axisY()/2; // how we get the center y

        MyCircle circle = new MyCircle(super.x,super.y,width,height);
        double x1 = circle.getX()+width; //center x
        double y1 = circle.getX()+height;

        double distance = Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));
        double widthSum = oval.axisX()/2 + circle.getRadius(); //this is the sum of the width - radius we can say.
        double heightSum = oval.axisY()/2 + circle.getRadius();

        if(distance < widthSum)
        {
            return true;
        }

        if(distance < heightSum)
        {
            return true;
        }

        return false;



    }

    @Override
    public boolean doOverlap(MyRectangle rect) {
        MyCircle circle = new MyCircle(super.x,super.y,width,height);

        double minX = rect.getX();
        double minY = rect.getY();
        double largestX = rect.getX()+rect.getWidth(); // this is the largest x coordinates
        double largestY = rect.getY()+rect.getHeight(); // this is the largest y coordinates

        double centerX = circle.getX() + circle.getRadius(); //center point
        double centerY = circle.getY() + circle.getRadius();


        double leftX = centerX - circle.getRadius(); //y doesnt matter
        double rightX = centerX+circle.getRadius(); //y doesnt matter
        double topY = centerY- circle.getRadius(); // x doesnt matter
        double botY = centerY +circle.getRadius();// x doesnt matter

        if(leftX > largestX) //circle is on the left side
        {
            return false;
        }
        if(rightX < minX) // circle on the right
        {
            return false;
        }

        if(topY > largestY) // circle on the bot
        {
            return false;
        }

        if(botY < minY) //circle on top
        {
            return false;
        }

        return true;
    }

    @Override
    public MyRectangle getMyBoundingBox() {
        return new MyRectangle(super.x, super.y, width, height);
    }
}

