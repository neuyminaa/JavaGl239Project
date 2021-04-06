package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "Заданы два множества точек в пространстве.\n" +
            "Требуется построить пересечения и разность этих множеств";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-7 Иванова Ивана";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "points.txt";

    /**
     * список точек
     */
    private ArrayList<Point> points;
    private ArrayList<Rectangle> rectangles;

    Rectangle rectA;
    Rectangle rectB;

    Line line;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
        rectangles = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x координата X точки
     * @param y координата Y точки
     */
    public void addPoint(double x, double y, double x2, double y2, double x3, double y3) {
        Rectangle point = new Rectangle(x, y, x2, y2, x3, y3);
        rectangles.add(point);
    }

    /**
     * Решить задачу
     */
    public void solve() {
        // перебираем пары точек
        double maxlength =0;
        Line maxlengthline = null;
        for (int i=0, i<n, i++ ){
            Point p1 = points.get(i);
            for( int J=i+1, j<n, j++ ){
                Point p2 = point.get(j);
            }
        }
        // rectB =
        // line =
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        points.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                double x2 = sc.nextDouble();
                double y2 = sc.nextDouble();
                double x3 = sc.nextDouble();
                double y3 = sc.nextDouble();
                sc.nextLine();
                Rectangle point = new Rectangle(x, y, x2, y2, x3, y3);
                rectangles.add(point);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            for (Rectangle rectangle : rectangles) {
                out.printf("%.2f %.2f %.2f %.2f %.2f %.2f\n", rectangle.xA, rectangle.yA, rectangle.xB, rectangle.yB, rectangle.xT, rectangle.yT);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomPoints(int n) {
        for (int i = 0; i < n; i++) {
            Rectangle r = Rectangle.getRandomRectangle();
            rectangles.add(r);
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        points.clear();
        rectangles.clear();
        rectA = null;
        rectB = null;
        line = null;
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        for (Rectangle rectangle : rectangles) {
            rectangle.render(gl);
        }

        if (rectA != null) {
            rectA.render(gl);
            rectB.render(gl);
            line.render(gl);
        }
        // Figures.renderPoint(gl,0.5,0.5,3);
//        Figures.renderPoint(gl,-0.5,-0.5,5);
//        Figures.renderPoint(gl,-0.5,0.5,2);
//        Figures.renderPoint(gl,0.5,-0.5,1);

        // Line(gl, 0, 0, 0.5, 0.5, 3);
        // Figures.renderTriangle(gl, -2, 0, 0, 0.5, 0.5 , 0.4, false);
        //Figures.renderQuad ( gl, 0,0,0, 0.4,0.4,0.2,0.3,0.4, false);
//        Rectangle rectangle = new Rectangle(0.1,0.1,0.2,0.3,-0.1,0.3);
//        rectangle.render(gl);
        // Figures.renderCircle (gl, 0.1,0.2,0.1, false);
    }

}
