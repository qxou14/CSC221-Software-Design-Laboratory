package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setTitle("CS 221 Final Project");
        BorderPane root = new BorderPane();
        Canvas cv = new Canvas(700,500);
        GraphicsContext gc = cv.getGraphicsContext2D();



        int width = 250;
        int height = 250;
       MyPieChart chart = new MyPieChart(cv.getWidth()/2-width/2,cv.getHeight()/2-height/2,width,height);

      chart.dataArray();
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
