package problem;

import javax.media.opengl.GL2;
import java.util.Random;

public class Rectangle {
    double xA;
    double yA;

    double xB;
    double yB;

    double xC;
    double yC;

    double xD;
    double yD;

    double xT;
    double yT;

    // L.x = x3
    // L.y = y3
    // P.x - ?
    // t=((x2-x1)*x3+(y2-y1)*y3-(x2-x1)*x1-(y2-y1)*y1)/(x2-x1)*(x2-x1)*(y2-y1)*(y2-y1)
    // P.x=(x2-x1)*t+x1
    // P/y - ?
    // P.y=(y2-y1)*t+y1
    Rectangle(double x1, double y1, double x2, double y2, double x3, double y3) {

        xA = x1;
        yA = y1;

        xB = x2;
        yB = y2;
        xT = x3;
        yT = y3;
        double t = ((x2 - x1) * x3 + (y2 - y1) * y3 - (x2 - x1) * x1 - (y2 - y1) * y1) / (x2 - x1) * (x2 - x1) * (y2 - y1) * (y2 - y1);

        double xP = (x2 - x1) * t + x1;
        double yP = (y2 - y1) * t + y1;

        xC = xB + x3 - xP;
        yC = yB + y3 - yP;

        xD = xA + x3 - xP;
        yD = yA + y3 - yP;

    }

    public void render(GL2 gl) {
        Figures.renderQuad(gl, xA, yA, xB, yB, xC, yC, xD, yD, false);

    }

    public static Rectangle getRandomRectangle() {
        Random r = new Random();
        return new Rectangle(
                r.nextDouble() * 2 - 1,
                r.nextDouble() * 2 - 1,
                r.nextDouble() * 2 - 1,
                r.nextDouble() * 2 - 1,
                r.nextDouble() * 2 - 1,
                r.nextDouble() * 2 - 1
        );
    }

//   public Line Linecrossing() {
//        re
//
//   }

}
