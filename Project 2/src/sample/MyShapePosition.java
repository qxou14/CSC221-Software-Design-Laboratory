package sample;

public interface MyShapePosition extends MyPoint {

    boolean doOverlap(MyOval oval);
    boolean doOverlap(MyRectangle rect);
    MyRectangle getMyBoundingBox();
}
