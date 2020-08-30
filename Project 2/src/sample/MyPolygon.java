package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyPolygon extends MyShape
{
    private int NumberOfSide;
    private int radius;
    static Points p;


    MyPolygon(double centerX, double centerY , int N,int r)
    {
        super(centerX,centerY);
        Points p = new Points(super.x , super.y);
        this.p = p;
        NumberOfSide = N;
        radius = r;
    }


    public double getArea(){ // a = 1/2xperimeter x apothem    apothem = s/2tan(180/n)

        double apothem = getSide()/(2*Math.tan(180/NumberOfSide));
        double area = (getPerimeter() * apothem) / 2;
        return area;

    }

    public double getPerimeter(){
        double perimeter = NumberOfSide * getSide();
        return perimeter;
    }
    public double getAngle()
    {//interior
        double angle = ((NumberOfSide - 2) * 180) / NumberOfSide;
        return angle;

    }

    public double getSide(){
        double sideLength = 2 * radius * Math.sin(180 / NumberOfSide);
        return sideLength;


    }

    @Override
    public String toString()
    {
        return "The side length of this polygon is "+getSide()+". The perimeter of this polygon is "+ getPerimeter()+ ". The area of this polygon is"+getArea()+". The interior angle is "+ getAngle()+".";

    }


    @Override
    public void draw(GraphicsContext gc)
    {
        double[] xArray = new double[NumberOfSide];
        double[] yArray = new double[NumberOfSide];
        gc.setFill(C.getColor());

        gc.setLineWidth(3.0);


        for(int i = 0 ; i < NumberOfSide ; i++)
        {
            double yCoordinate = x+radius*Math.cos(2*Math.PI*i/NumberOfSide);
            double xCoordinate = y+radius*Math.sin(2*Math.PI*i/NumberOfSide);
            xArray[i] = xCoordinate;
            yArray[i] = yCoordinate;
        }

        gc.fillPolygon(xArray,yArray,NumberOfSide);
    }

    @Override
    public Points getPoint() {
        return p;
    }

    @Override
    public void setPoint(double x, double y)
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
    public double distanceTo(double x, double y, double x2, double y2) { //distance to center
       return Math.sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y));
    }

    @Override
    //imagine this as a square
    public boolean doOverlap(MyOval oval) {
        double polygonLeftX = super.x - radius;
        double polygonLeftY = super.y - radius;


        double x = oval.getX()+oval.axisX()/2; //how we get the center x
        double y = oval.getY()+oval.axisY()/2; // how we get the center y


        MyOval oval1 = new MyOval(polygonLeftX,polygonLeftY,radius,radius);

        double x1 = oval1.getX()+oval1.axisX()/2;
        double y1 = oval1.getX()+oval1.axisY()/2;

        double distance = Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));
        double widthSum = oval.axisX()/2 + oval1.axisX()/2; //this is the sum of the width - radius we can say.
        double heightSum = oval.axisY()/2 + oval1.axisY()/2;

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
    public boolean doOverlap(MyRectangle rectangle) {
        MyOval oval = new MyOval(super.x,super.y,radius,radius);

        double minX = rectangle.getX();
        double minY = rectangle.getY();
        double largestX = rectangle.getX()+rectangle.getWidth(); // this is the largest x coordinates
        double largestY = rectangle.getY()+rectangle.getHeight(); // this is the largest y coordinates

        double centerX = oval.x + oval.axisX()/2; //center point
        double centerY = oval.y + oval.axisY()/2;


        double leftX = centerX - oval.axisX()/2; //y doesnt matter
        double rightX = centerX+oval.axisX()/2; //y doesnt matter
        double topY = centerY- oval.axisX()/2; // x doesnt matter
        double botY = centerY +oval.axisY()/2;// x doesnt matter

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
    public MyRectangle getMyBoundingBox() { // the radius of polygon is the same, so we can use a squre to bound it, h=w= r;
        double polygonLeftX = super.x - radius;
        double polygonRightY = super.y - radius;
        return new MyRectangle(polygonLeftX,polygonRightY,radius,radius);
    }
}
