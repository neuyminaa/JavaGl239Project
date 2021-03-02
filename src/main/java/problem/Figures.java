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
}
