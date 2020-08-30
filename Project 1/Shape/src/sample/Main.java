package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CS 221 Shape Exercise");
        BorderPane root = new BorderPane();
        Canvas cv = new Canvas(700,500);
        GraphicsContext gc = cv.getGraphicsContext2D();

        MyPolygon polygon1 = new MyPolygon(cv.getHeight()/2,cv.getWidth()/2,6,120); //outside polygon
        polygon1.setColor(MyColor.GREY);
        polygon1.setLineColor(MyColor.BLACK);
        polygon1.draw(gc);

        MyCircle circle1 = new MyCircle((cv.getWidth()/2)-100,cv.getHeight()/2-100,200,200); //bigger circle
        circle1.setColor(MyColor.YELLOW);
        circle1.draw(gc);

        MyPolygon polygon2 = new MyPolygon(cv.getHeight()/2,cv.getWidth()/2,6,98); //mid polygon
        polygon2.setColor(MyColor.LIGHTGREEN);
        polygon2.setLineColor(MyColor.LIGHTGREEN);
        polygon2.draw(gc);

        MyCircle circle2 = new MyCircle(cv.getWidth()/2-85, cv.getHeight()/2-85,170,170); //smaller circle
        circle2.setColor(MyColor.LIGHTRED);
        circle2.draw(gc);

        MyPolygon polygon3 = new MyPolygon(cv.getHeight()/2,cv.getWidth()/2,6,83);
        polygon3.setColor(MyColor.LIGHTBLUE);
        polygon3.setLineColor(MyColor.LIGHTBLUE);
        polygon3.draw(gc);

        MyLine Crossline1 = new MyLine(0,0,cv.getWidth(),cv.getHeight());
        Crossline1.setColor(MyColor.BLACK);
        Crossline1.draw(gc);

        MyLine Crossline2 = new MyLine(cv.getWidth(),0,0,cv.getHeight());
        Crossline2.setColor(MyColor.BLACK);
        Crossline2.draw(gc);

        MyLine line1 = new MyLine(0,0,0,cv.getHeight());  //leftmost line
        line1.setColor(MyColor.BLACK);
        line1.draw(gc);

        MyLine line2 = new MyLine(0,0,cv.getWidth(),0);//top corner line
        line2.setColor(MyColor.BLACK);
        line2.draw(gc);

        MyLine line3 = new MyLine(cv.getWidth(),0,cv.getWidth(),cv.getHeight()); //rightmost line
        line3.setColor(MyColor.BLACK);
        line3.draw(gc);

        MyLine line4 = new MyLine(cv.getWidth(),cv.getHeight(),0,cv.getHeight());
        line4.setColor(MyColor.BLACK);
        line4.draw(gc);



        Scene scene = new Scene(root,700,500);
        root.getChildren().add(cv);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

enum MyColor
{
    RED(255,0,0),BLUE(0,0,255),GREEN(0,255,0),BLACK(0,0,0),WHITE(255,255,255),Ram(30,50,90),
    GREY(153,153,153),YELLOW(255,255,0),LIGHTGREEN(102,255,102),LIGHTBLUE(51,204,255), LIGHTRED(255,102,102);




    private Color c;
    private Color b;

    MyColor(int r, int g , int b)
    {
        c = Color.rgb(r,g,b);

    }


    public Color getColor()
    {
        return c;
    }
    public Color getLineColor(){return c;}

}
class MyShape
{
    protected double x;
    protected double y;
    protected static MyColor C;
    protected static MyColor B;
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

    public MyColor getLineColor()
    {
        return B;
    }
    public void setLineColor(MyColor B){this.B= B; }

    public String toString()
    {
        return x+""+y;
    }

    public void draw(GraphicsContext gc)
    { }



}

class MyLine extends MyShape
{
    private double length;
    private double angle;
    private double x2;
    private double y2;

    MyLine(double x1 ,double y1,double x2,double y2){
        super(x1,y1);
        this.x2 = x2;
        this.y2 = y2;
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
        gc.setLineWidth(6.0);
        gc.setStroke(C.getColor());
        gc.strokeLine(x,y,x2,y2);

    }

}



class MyPolygon extends MyShape
{
    private int NumberOfSide;
    private int radius;


    MyPolygon(double centerX, double centerY , int N,int r)
    {
        super(centerX,centerY);
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

    public String toString()
    {
        return "The side length of this polygon is "+getSide()+". The perimeter of this polygon is "+ getPerimeter()+ ". The area of this polygon is"+getArea()+". The interior angle is "+ getAngle()+".";

    }


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
        gc.setStroke(B.getLineColor());
        gc.strokePolygon(xArray,yArray,NumberOfSide);
    }

}


class MyCircle extends MyShape
{
    private double area;
    private double perimeter;
    private double radius;
    private double width; //width at the center of r
    private double height; // height at the center of r
    MyCircle(double x1, double y1, double w, double h)
    {
        super(x1,y1);
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


    public void draw(GraphicsContext gc)
    {
        gc.setFill(C.getColor());
        gc.fillOval(x,y,width,height);

    }
}


