package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import java.util.Map;
import java.util.Random;


public class MyPieChart{

    private double x;
    private double y;
    private double width;
    private double height;
    private int NumberOfStat; // this tells the program how many information we want to include in our piechart.
    HistogramAlphaBet H = new HistogramAlphaBet();



    public MyPieChart(double x, double y,double width, double height, int n)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        NumberOfStat = n;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public String toString() {
        return x + "" + y;
    }



    public void draw(GraphicsContext gc)
    {
        Random number = new Random();


        Color[] color = new Color[NumberOfStat];

        for(int j = 0; j<NumberOfStat;j++)
        {
            color[j] = Color.rgb(number.nextInt(250),number.nextInt(250),number.nextInt(250));
        }
// 255 is white color , i dont want that to happen


        float [] probability= new float[NumberOfStat];
        int z = 0;
        float totalProbability = 0;
        for (Map.Entry<Character,Integer> entry : H.sort.entrySet())
        {
            float key = entry.getValue();
            if(z<NumberOfStat)
            {
                probability[z] = key/H.getTotalChar();
                totalProbability += probability[z];
                z++;
            }

        }

        float everythingElse = 1-totalProbability;  // probability of everything else
        String stringOfEveryThing = String.valueOf(everythingElse);



        String[] stringValues = new String[NumberOfStat];
        for(int j = 0; j<NumberOfStat;j++)
        {
            stringValues[j] = String.valueOf(probability[j]);
        }



        char [] arrayOfKeys = new char[NumberOfStat];
        int i = 0;
        for (Map.Entry<Character,Integer> entry :H.sort.entrySet()) {
            char key = entry.getKey();
            if(i<NumberOfStat)
            {
                arrayOfKeys[i] = key;
                i++;
            }

        }

        String[] stringKeys = new String[NumberOfStat];
        for(int j = 0; j<NumberOfStat;j++)
        {
            stringKeys[j] = String.valueOf(arrayOfKeys[j]);
        }



        int startAngle = 90;
        for(int j = 0; j < NumberOfStat; j++)
        {
            gc.setFill(color[j]);
           gc.fillArc(x,y,width,height,startAngle,-(probability[j]*360), ArcType.ROUND);
            startAngle-= (probability[j]*360);
        }
        gc.setFill(Color.LIGHTPINK);
        //gc.setFill(Color.BLACK);
        gc.fillArc(x,y,width,height,startAngle,-(everythingElse*360),ArcType.ROUND);

        double centralAngle = 90;
        double r = height/2;
        double rEx = r + 30; //distance from the arc
       for(int j = 0; j<NumberOfStat;j++)
        {

            //x+r+rcost  y+r+rsint

            gc.setFill(Color.BLACK);
            gc.fillText(stringKeys[j],x+r+rEx*Math.cos(Math.toRadians(centralAngle)+r/2),y+r-rEx*Math.sin(Math.toRadians(centralAngle)+r/2));
            gc.fillText(stringValues[j],x+r+rEx*Math.cos(Math.toRadians(centralAngle)+r/2)+r/10,y+r-rEx*Math.sin(Math.toRadians(centralAngle)+r/2));
            centralAngle += -(probability[j]*360);

        }

        gc.fillText("Allotherletters",x+r+rEx*Math.cos(Math.toRadians(centralAngle)+r/2)-r/2,y+r-rEx*Math.sin(Math.toRadians(centralAngle)+r/2));
       //gc.fillText("All other letter",x+r+rEx*Math.cos(Math.toRadians(centralAngle))-r,y+r-rEx*Math.sin(Math.toRadians(centralAngle)));

        gc.fillText(stringOfEveryThing,x+r+rEx*Math.cos(Math.toRadians(centralAngle)+r/2)-r/2,y+r-rEx*Math.sin(Math.toRadians(centralAngle)+r/2)+r/4);
     //  gc.fillText(stringOfEveryThing,x+r+rEx*Math.cos(Math.toRadians(centralAngle))-r,y+r-rEx*Math.sin(Math.toRadians(centralAngle))+r/4);

// prob = centralAngle/2Pi 2Pi is also equal to 360 degree
    }
}
