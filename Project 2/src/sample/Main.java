package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CS 221 Shape Exercise");
        BorderPane root = new BorderPane();
        Canvas cv = new Canvas(700,500);
        GraphicsContext gc = cv.getGraphicsContext2D();


        MyRectangle rectangle1 = new MyRectangle(cv.getWidth()/4,cv.getHeight()/4, 300,215); //biggest rectangle
        rectangle1.setColor(MyColor.YELLOW);
        System.out.println("point for biggest rectangle1: "+rectangle1.getPoint());
        System.out.println("A rectangle bound with rect1: "+rectangle1.getMyBoundingBox());
        rectangle1.draw(gc);

        System.out.println(" ");

        MyOval oval1 = new MyOval(cv.getWidth()/4,cv.getHeight()/4,300,215);
        oval1.setColor(MyColor.RED);
        System.out.println("point for biggest oval1: "+oval1.getPoint());
        System.out.println("Biggest oval overlap biggest triangle? "+ oval1.doOverlap(rectangle1));
        System.out.println("A rectangle bound oval1: "+oval1.getMyBoundingBox());
        oval1.draw(gc);

        System.out.println(" ");

        MyRectangle rectangle2 = new MyRectangle(cv.getWidth()/3.2,cv.getHeight()/3.2,213,152);
        rectangle2.setColor(MyColor.BLUE);
        System.out.println("point for rectangle2: "+rectangle2.getPoint());
        System.out.println("Rectangle 2 overlap with biggest rectangle? "+rectangle2.doOverlap(rectangle1));
        System.out.println("Rectangle 2 overlap with biggest oval ? "+ rectangle2.doOverlap(oval1));
        System.out.println("A rectangle bound with rect2: "+rectangle2.getMyBoundingBox());
        rectangle2.draw(gc);

        System.out.println(" ");

        MyOval oval2 = new MyOval(cv.getWidth()/3.2,cv.getHeight()/3.2,213,152);
        oval2.setColor(MyColor.LIGHTGREEN);
        System.out.println("point for oval2: "+oval2.getPoint());
        System.out.println("oval2 overlap with biggest rectangle? "+oval2.doOverlap(rectangle1));
        System.out.println("oval2 overlap with biggest oval? "+ oval2.doOverlap(oval1));
        System.out.println("A rectangle bound with oval2: "+oval2.getMyBoundingBox());
        oval2.draw(gc);

        System.out.println(" ");

        MyRectangle rectangle3 = new MyRectangle(cv.getWidth()/2.8,cv.getHeight()/2.8,150,108);
        rectangle3.setColor(MyColor.LIGHTBLUE);
        System.out.println("point for rectangle3 "+rectangle3.getPoint());
        System.out.println("rectangle3 overlap with biggest rectangle? "+rectangle3.doOverlap(rectangle1));
        System.out.println("rectangle3 overlap with biggest oval? "+ rectangle3.doOverlap(oval1));
        System.out.println("rectangle3 overlap with rectangle2? "+ rectangle3.doOverlap(rectangle2));
        System.out.println("rectangle3 overlap with oval2? "+ rectangle3.doOverlap(oval2));
        System.out.println("A rectangle bound with rect3: "+rectangle3.getMyBoundingBox());
        rectangle3.draw(gc);

        System.out.println(" ");

        MyOval oval3 = new MyOval(cv.getWidth()/2.8,cv.getHeight()/2.8,150,108);
        oval3.setColor(MyColor.LIGHTRED);
        System.out.println("oval3 overlap with biggest rectangle? "+oval3.doOverlap(rectangle1));
        System.out.println("oval3 overlap with biggest oval? "+ oval3.doOverlap(oval1));
        System.out.println("oval3 overlap with rectangle2? "+ oval3.doOverlap(rectangle2));
        System.out.println("oval3 overlap with oval2? "+ oval3.doOverlap(oval2));
        System.out.println("A rectangle bound with oval3: "+oval3.getMyBoundingBox());
        oval3.draw(gc);

        System.out.println(" ");

        MyLine Crossline1 = new MyLine(0,0,cv.getWidth(),cv.getHeight());
        Crossline1.setColor(MyColor.BLACK);
        System.out.println("crossing line overlap with biggest rectangle? "+Crossline1.doOverlap(oval1));
        System.out.println("crossing line overlap with biggest oval? "+Crossline1.doOverlap(rectangle1));
        System.out.println("crossing line overlap with smallest rectangle? "+Crossline1.doOverlap(rectangle3));
        System.out.println("crossing line overlap with smallest oval? "+Crossline1.doOverlap(oval3));
        System.out.println(Crossline1.getMyBoundingBox());
        Crossline1.draw(gc);

        System.out.println(" ");


        MyLine line1 = new MyLine(0,0,0,cv.getHeight());  //leftmost line
        line1.setColor(MyColor.BLACK);
        System.out.println("Does leftmost line overlap with rectangle1? "+line1.doOverlap(rectangle1));
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

