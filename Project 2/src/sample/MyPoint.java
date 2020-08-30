package sample;


public interface MyPoint {
    Points getPoint();
    void setPoint(double x, double y);
    void moveTo(double x, double y);
    double distanceTo(double x, double y, double x2, double y2);
}
