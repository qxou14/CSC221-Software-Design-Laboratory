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
        primaryStage.setTitle("CS 221 Pie Chart");
        BorderPane root = new BorderPane();
      Canvas cv = new Canvas(700,500);
        GraphicsContext gc = cv.getGraphicsContext2D();


       HistogramAlphaBet r = new HistogramAlphaBet();
        System.out.println("Total characters: "+ r.getTotalChar());
        System.out.println("sorted map: "+r.sortedMap());





        int width = 250;
        int height = 250;
     MyPieChart chart = new MyPieChart(cv.getWidth()/2-width/2,cv.getHeight()/2-height/2,width,height,6);
     chart.draw(gc);

        Scene scene = new Scene(root,700,500);
        root.getChildren().add(cv);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }


}
