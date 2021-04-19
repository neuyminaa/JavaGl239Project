package problem;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class Line {
    private double DELTA = 0.0001;
    public Point p1, p2; // координаты
    public double A, B, C; // уравнение прямой
    /*
    расстояние до точки
     */
    public double distanceToPoint(Point p){
        return Math.abs((A*p.getX() + B*p.getY() + C)/(Math.sqrt(A*A + B*B)));
    }
    /*
    проверка принадлежности точки отрезку
     */
    public boolean belong(Point p){
        return Math.abs(p1.distanceTo(p2) - p.distanceTo(p1) - p.distanceTo(p2)) < DELTA;
    }
    /*
    пересечение отрезков
     */
    public ArrayList<Point> intersection(Line l){
        if(isParallel(l)) {
            // проверяем если отрезки совпадают, то возвращаем 2 крайние точки их пересечения
            if(A == l.getA() && B == l.getB() && C == l.getC()){
                ArrayList<Point> v = new ArrayList<Point>(0);
                if(l.belong(p1)){
                    v.add(p1);
                }
                if(l.belong(p2)){
                    v.add(p2);
                }
                if(this.belong(l.getP1())){
                    v.add(l.getP1());
                }
                if(this.belong(l.getP2())){
                    v.add(l.getP2());
                }
                return v;
            }
            else return new ArrayList<Point>(0);
        }
        double c = l.getC(); double a = l.getA(); double b = l.getB();
        double x = (b * C / B - c) / (a - A * b / B);
        double y = x * (- A/B) - C/B;
        if(belong(new Point(x, y)) && l.belong(new Point(x, y))) {
            ArrayList<Point> v = new ArrayList<Point>(0);
            v.add(new Point(x, y));
            return v;
        }
        else{
            return new ArrayList<Point>(0);
        }
    }
    /*
    проверка открезков на параллельность
     */
    public boolean isParallel(Line l){
        if(Math.abs(A) > DELTA && Math.abs(B) > DELTA && Math.abs(l.getA()) > DELTA && Math.abs(B) > 0)
            return(Math.abs(A* l.getB() - B * l.getA()) < DELTA);
        else if( Math.abs(B) < DELTA && Math.abs(l.getB()) < DELTA)
            return true;
        else if (Math.abs(A) < DELTA && Math.abs(l.getA()) < DELTA)
            return true;
        else
            return false;
    }

    /*
    построение параллельной линии через точку
     */
    public Line parallelLine(Point point) {
        double a = A;
        double b = B;
        double c = -(a * point.getX() + b * point.getY());
        return new Line(a, b, c);
    }
    /*
    построение перендикуляра через точку
     */
    public Line perpendicularLine(Point point) {
        double a = -B * B;
        double b = A * B;
        double c = -(a * point.getX() + b * point.getY());
        return new Line(a, b, c);
    }
    /*
    пересечение без ограничения отрезка
     */
    public Point intersectionNO(Line line) {
        if (isParallel(line)) return null;
        else {
            double a = line.getA(), b = line.getB(), c = line.getC();
            double X = (b * C / B - c) / (a - A * b / B);
            double Y = -(A * X + C) / B;
            return new Point(X, Y);
        }
    }

    Line(){
        p1 = new Point(0,0);
        p2 = new Point(1,1);
        A = p2.getY() - p1.getY();
        B = p1.getX() - p2.getX();
        if (B == 0) B = 0.0000001;
        C = p1.getX() * p2.getY() - p2.getX() * p1.getY();
    }

    Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
        A = p1.getY() - p2.getY();
        B = p2.getX()- p1.getX();
        if (B == 0) B = 0.0000001;
        C = p1.getX() * p2.getY() - p2.getX() * p1.getY();
    }

    Line(double A, double B, double C){
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public void render(GL2 gl){
        Figures.renderLine(gl, p1, p2, 2);
    }

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setB(double b) {
        B = b;
    }

    public void setA(double a) {
        A = a;
    }

    public void setC(double c) {
        C = c;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

}

