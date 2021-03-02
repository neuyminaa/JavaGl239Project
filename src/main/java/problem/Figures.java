package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Figures {
    public static void renderPoint (GL2 gl, double x, double y, float size) {
        gl.glPointSize(size);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x,y);
        gl.glEnd();
    }

    public static void renderLine (GL2 gl,double xa, double ya,double xb, double yb, float width ) {
        gl.glLineWidth(width);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(xa,ya);
        gl.glVertex2d(xb,yb);
        gl.glEnd();
    }
    public static void renderTriangle (GL2 gl, double xa, double ya,double xb, double yb,double xc, double yc, boolean filed) {
        if (filed) {
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex2d(xa, ya);
            gl.glVertex2d(xb, yb);
            gl.glVertex2d(xc, yc);
            gl.glEnd();
        }
        else {
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2d(xa, ya);
            gl.glVertex2d(xb, yb);
            gl.glVertex2d(xc, yc);
            gl.glVertex2d(xa, ya);
            gl.glEnd();
        }
    }
    public static void renderQuad (GL2 gl, double xa, double ya,double xb, double yb,double xc, double yc,double xd, double yd, boolean filed ) {
        if (filed) {
            gl.glBegin(GL.GL_QUAD);
            gl.glVertex2d(xa, ya);
            gl.glVertex2d(xb, yb);
            gl.glVertex2d(xc, yc);
            gl.glVertex2d(xd, yd);
            gl.glEnd();
        }
        else {
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2d(xa, ya);
            gl.glVertex2d(xb, yb);
            gl.glVertex2d(xc, yc);
            gl.glVertex2d(xa, ya);
            gl.glEnd();
        }
    }
}