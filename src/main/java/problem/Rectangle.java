package problem;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class Rectangle {
    Line[] parties = new Line[4];
    Point ax1, ax2, axP;

    /*
    пересечение четырехугольников
    всегда возвращает или ни одной точки, или 2 точки
     */
    public ArrayList<Point> intersection(Rectangle r) {
        ArrayList<Point> ans = new ArrayList<Point>(0);
        for (int i = 0; i < parties.length; i++) {
            for (int j = 0; j < r.parties.length; j++) {
                ArrayList<Point> t = parties[i].intersection(r.parties[j]);
                for (int e = 0; e < t.size(); e++)
                    ans.add(t.get(e));
            }
        }
        if (ans.size() == 0)
            return new ArrayList<Point>(0);
        ArrayList<Point> finans = new ArrayList<Point>();
        finans.add(new Point());
        finans.add(new Point());
        double mx = 0;
        for (int i = 0; i < ans.size(); i++) {
            for (int j = i; j < ans.size(); j++) {
                double t = ans.get(i).distanceTo(ans.get(j));
                if (mx < t) {
                    mx = t;
                    finans.set(0, ans.get(i));
                    finans.set(1, ans.get(j));
                }
            }
        }
        return finans;
    }

    Rectangle(Line[] parties) {
        this.parties = parties;
    }

    Rectangle(Point[] cord) {
        parties[0] = new Line(cord[0], cord[1]);
        parties[1] = new Line(cord[1], cord[2]);
        parties[2] = new Line(cord[2], cord[3]);
        parties[3] = new Line(cord[3], cord[0]);
    }

    Rectangle(Point p1, Point p2, Point pA) {
        ax1 = p1;
        ax2 = p2;
        axP = pA;

        double d1, d2;

        Line line = new Line(p1, p2);
        Line line_parallel = line.parallelLine(pA);
        Line line_perped1 = line.perpendicularLine(p1);
        Line line_perped2 = line.perpendicularLine(p2);

        Point p3 = line_parallel.intersectionNO(line_perped2);
        Point p4 = line_parallel.intersectionNO(line_perped1);

        parties[0] = new Line(p1, p2);
        parties[1] = new Line(p2, p3);
        parties[2] = new Line(p3, p4);
        parties[3] = new Line(p4, p1);
    }

    //Рисование точки через класс Figures
    void render(GL2 gl, boolean filled) {
        Figures.renderQuad(gl, parties[0].p1, parties[1].p1, parties[2].p1, parties[3].p1, filled, 1, 1, 1);
    }

    //облегчённый метод рисования
    void render(GL2 gl) {
        render(gl, false);
    }

    void renderResult(GL2 gl) {
        Figures.renderQuad(gl, parties[0].p1, parties[1].p1, parties[2].p1, parties[3].p1, false, 1, 1, 0);
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f), (%.2f; %.2f), (%.2f; %.2f)", ax1.x, ax1.y, ax2.x, ax2.y, axP.x, axP.y);
    }
}

