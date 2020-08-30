package sample;
import javafx.scene.paint.Color;

public enum MyColor
{
    RED(255,0,0),BLUE(0,0,255),GREEN(0,255,0),BLACK(0,0,0),WHITE(255,255,255),Ram(30,50,90),
    GREY(153,153,153),YELLOW(255,255,0),LIGHTGREEN(102,255,102),LIGHTBLUE(51,204,255), LIGHTRED(255,102,102);

    private Color c;
    MyColor(int r, int g , int b)
    {
        c = Color.rgb(r,g,b);

    }

    public Color getColor()
    {
        return c;
    }
}

