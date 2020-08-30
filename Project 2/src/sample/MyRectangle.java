package sample;

import javafx.scene.canvas.GraphicsContext;


public class MyRectangle extends MyShape
{
    private double width;
    private double height;
    private double perimeter;
    private double area;
    static Points p;

    MyRectangle(double x, double y, double width, double height)
    {
        super(x,y);
        Points p = new Points(super.x , super.y); //point of upper left
        this.p = p;


        this.width = width;
        this.height= height;
    }

    public double getWidth()
    {
        return width;
    }
    public double getHeight()
    {
        return height;
    }

    public double getPerimeter()
    {
        perimeter = getHeight()+getHeight()+getWidth()+getWidth();
        return perimeter;
    }

    public double getArea()
    {
        area = (getHeight()*getWidth());
        return area;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
    public void setPerimeter(int newperimeter)
    {

        double scale = newperimeter/perimeter;
        perimeter = newperimeter;

        width = width*scale;
        height = height*scale;
    }

    public void setArea(int newarea)
    {
        double scale = newarea/area;
        area = newarea;

        if(width<height)
        {
            width = width*scale;
        }
        else
        {
            height = height*scale;
        }


    }
    public String toString()
    {
        return "The width for this rectangle is"+getWidth()+". The height is "+getHeight()+ ". The perimeter is "+getPerimeter()+". The area is "+getArea()+". And the upperleft coordinates are "+super.x+","+super.y+".";
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(C.getColor());
        gc.fillRect(x,y,width,height);
    }

    @Override
    public Points getPoint() {
        return p;
    }

    @Override
    public void setPoint(double x, double y) {
        super.x = x;
        super.y = y;
    }

    @Override
    public void moveTo(double x, double y)
    {
        super.x += x;
        super.y += y;
    }

    @Override
    public double distanceTo(double x, double y, double width, double height) { //return the diagonal of rectangle

        double leftUpperX = x;
        double leftUpperY = y;
        double rightdownX = x+width;
        double rightdownY = y+height;

        return Math.sqrt((leftUpperX - rightdownX)*(leftUpperX - rightdownX)+(leftUpperY-rightdownY)*(leftUpperY-rightdownY));
    }
    @Override
    public MyRectangle getMyBoundingBox() { //bounding rectangle for rectangle is the rectangle itself.
        return new MyRectangle(super.x,super.y,width,height);
    }


    @Override
    public boolean doOverlap(MyOval oval) {
        MyRectangle rectangle = new MyRectangle(super.x,super.y,width,height);

        double minX = rectangle.getX();
        double minY = rectangle.getY();
        double largestX = rectangle.getX()+rectangle.getWidth(); // this is the largest x coordinates
        double largestY = rectangle.getY()+rectangle.getHeight(); // this is the largest y coordinates

        double centerX = oval.x + (oval.axisX())/2; //center point
        double centerY = oval.y + (oval.axisY())/2;


        double leftX = centerX - (oval.axisX())/2; //y doesnt matter
        double rightX = centerX+(oval.axisX())/2; //y doesnt matter
        double topY = centerY- (oval.axisY())/2; // x doesnt matter
        double botY = centerY +(oval.axisY())/2;// x doesnt matter
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
    public boolean doOverlap(MyRectangle rect) {//max A x has to be = to min B x to be overlap

        MyRectangle original = new MyRectangle(super.x,super.y,width,height);

        //original
        double minX = original.getX();
        double minY = original.getY();
        double maxX = original.getX()+width; // biggest number of x
        double maxY = original.getY()+height; // biggerst number of y

        //what we input
        double minX1 = rect.getX();
        double minY1 = rect.getY();
        double maxX1 = rect.getX()+rect.getWidth(); //lower x for 2nd
        double maxY1 = rect.getY()+ rect.getHeight(); // lower y for 2nd

        if(minX1 > maxX || minX > maxX1) //when one is on the left side of another
        {
            return false;
        }

        if(minY1 > maxY || minY > maxY1) //when one is bot of another
        {
            return false;
        }

        return true;
    }


}
