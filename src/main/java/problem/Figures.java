package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;

public class Figures {
    public static void renderPoint(GL2 gl, double x, double y, float size) {
        gl.glPointSize(size);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
    }

    public static void renderLine(GL2 gl, double xa, double ya, double xb, double yb, float width) {
        gl.glLineWidth(width);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(xa, ya);
        gl.glVertex2d(xb, yb);
        gl.glEnd();
    }

    public static void renderTriangle(GL2 gl, double xa, double ya, double xb, double yb, double xc, double yc, boolean filed) {
        if (filed) {
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex2d(xa, ya);
            gl.glVertex2d(xb, yb);
            gl.glVertex2d(xc, yc);
            gl.glEnd();
        } else {
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2d(xa, ya);
            gl.glVertex2d(xb, yb);
            gl.glVertex2d(xc, yc);
            gl.glVertex2d(xa, ya);
            gl.glEnd();
        }
    }

    public static void renderQuad(GL2 gl, double xa, double ya, double xb, double yb, double xc, double yc, double xd, double yd, boolean filed) {
        if (filed) {
            gl.glBegin(GL2GL3.GL_QUADS);
            gl.glVertex2d(xa, ya);
            gl.glVertex2d(xb, yb);
            gl.glVertex2d(xc, yc);
            gl.glVertex2d(xd, yd);
            gl.glEnd();
        } else {
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2d(xa, ya);
            gl.glVertex2d(xb, yb);
            gl.glVertex2d(xc, yc);
            gl.glVertex2d(xd, yd);
            gl.glVertex2d(xa, ya);
            gl.glEnd();
        }

    }

    public static void renderCircle(GL2 gl, double centerx,double centery, double rad, boolean filed) {
        gl.glBegin(GL2GL3.GL_TRIANGLE_FAN);
        gl.glVertex2d(centerx, centery);
        for (int i = 0;   i <= 40; i++) {
           double angle = 2*Math.PI/40*i;
           double x=rad*Math.cos(angle);
           double y=rad*Math.sin(angle);
            gl.glVertex2d(x+centerx, y+centery);
        }
        gl.glEnd();

    }
}