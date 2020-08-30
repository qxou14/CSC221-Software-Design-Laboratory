package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape {
    private double width; //width at the center of r
    private double height; // height at the center of r
    static Points p;  // point of the center


    MyOval(double x1, double y1, double w, double h) {
        super(x1, y1);

        Points p = new Points(super.x,super.y);
        this.p = p;

        width = w;
        height = h;
    }

    public double getArea() {
        return Math.PI*width*height;
    }

    public double getPerimeter() {
        return Math.PI * 2*Math.sqrt((width*width+height*height)/2);
    }

    public double axisX()
    {

        return width*2;//width is at the center, so we need to mutiply by 2
    }
    public double axisY()
    {
        return height*2;
    }

    @Override
    public String toString() {
        return "The perimeter is " + getPerimeter() + ". The area is " + getArea() + ". The y axe's length "+axisY()+". The x axe's length"+axisX()+".";

    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(C.getColor());
        gc.fillOval(x, y, width, height);

    }

    @Override
    public Points getPoint() {
        return p;
    }

    @Override
    public void moveTo(double x, double y)
    {
        super.x += x;
        super.y += y;
    }

    @Override
    public void setPoint(double x, double y) {
        super.x = x;
        super.y = y;
    }

    @Override
    public double distanceTo(double x, double y, double x2, double y2) { //distance between two points

            return Math.sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y));

    }
    @Override
    public MyRectangle getMyBoundingBox() { //bounding rectangle for a oval will be having the same upperleft coordinates and equal width/height
        return new MyRectangle(super.x,super.y, width,height);
    }

    @Override
    public boolean doOverlap(MyOval oval) {
        // if the distance between two circles' center is greater than the sum of radius of both circles then circles  dont overlap

        double x = oval.getX()+oval.width; //how we get the center x
        double y = oval.getY()+oval.height; // how we get the center y

        MyOval oval1 = new MyOval(super.x,super.y,width,height);
        double x1 = oval1.getX()+width; //center x
        double y1 = oval1.getX()+height;

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
    public boolean doOverlap(MyRectangle rectangle)
    {

        MyOval oval = new MyOval(super.x,super.y,width,height);

        double minX = rectangle.getX();
        double minY = rectangle.getY();
        double largestX = rectangle.getX()+rectangle.getWidth(); // this is the largest x coordinates
        double largestY = rectangle.getY()+rectangle.getHeight(); // this is the largest y coordinates

        double centerX = oval.x + oval.width; //center point
        double centerY = oval.y + oval.height;


        double leftX = centerX - oval.width; //y doesnt matter
        double rightX = centerX+oval.width; //y doesnt matter
        double topY = centerY- oval.height; // x doesnt matter
        double botY = centerY +oval.height;// x doesnt matter

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


}

