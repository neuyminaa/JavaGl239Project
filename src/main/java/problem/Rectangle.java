package problem;

import javax.media.opengl.GL2;

public class Rectangle {
    double xA;
    double yA;

    double xB;
    double yB;

    double xC;
    double yC;

    double xD;
    double yD;

    // L.x = x3
    // L.y = y3
    // P.x - ?
    // P/y - ?
    Rectangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        xA = x1;
        yA = y1;

        xB = x2;
        yB = y2;

        xC  =  x3 + x2 ;
        yC =    y3 + y2   ;

        xD  =  x3 + x1  ;
        yD =    y3 + y1   ;


    }

    public void render(GL2 gl) {
        Figures.renderQuad(gl, xA, yA, xB, yB, xC, yC, xD, yD, false);

    }

}
