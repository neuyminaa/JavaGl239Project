package problem;

import javax.media.opengl.GL2;

public class Point {
    public double x, y;
    /*
    конструктор точки
    */
    Point(){
        x = 0;
        y = 0;
    }
    /*
    конструктор точки
    */
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    /*
    расстояние до точки
    */
    public double distanceTo(Point p){
        return Math.sqrt((x-p.getX())*(x-p.getX())+(y-p.getY())*(y-p.getY()));
    }

    //Рисование точки через класс Figures
    void render(GL2 gl, double W) {
        Figures.renderPoint(gl, this, W);
    }

    //облегчённый метод рисования
    void render(GL2 gl) {
        render(gl, 2);
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f)",x,y);
    }
}