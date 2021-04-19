package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Problem {

    public static final String PROBLEM_TEXT = "Постановка задачи:\n" +
            "На плоскости задано множество прямоугольников. \n" +
            "Найти такую пару пересекающихся прямоугольников, что длина отрезка, \n" +
            "проведенного от одной точки пересечения этих двух прямоугольников до другой, максимальна. \n" +
            "Если прямоугольники имеют более двух точек пересечения, \n" +
            "выбирать среди них такую пару, расстояние между которыми максимально. \n" +
            "В качестве ответа:\n" +
            "выделить эту пару прямоугольников,\n" +
            "нарисовать отрезок между найденными точками пересечения. \n";


    public static final String PROBLEM_CAPTION = "Итоговый проект ученицы 10-7 класса Неуйминой Антонины";

    public ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> TrueRectangles = new ArrayList<Rectangle>();
    //хранит те самые два прямоугольника

    public ArrayList<Point> TruePoint = new ArrayList<Point>();
    // хранит те самые две точки

    public void solve() {
        TrueRectangles.clear();
        TruePoint.clear();
        //очистить ответ

        double mx = 0;
        //максимальное значение длины

        for (int i = 0; i < rectangles.size(); i++) {
            for (int j = i + 1; j < rectangles.size(); j++) {
                //циклы по всем парам прямоугольников

                ArrayList<Point> t = rectangles.get(i).intersection(rectangles.get(j));
                //получаем пересечение прямоугольников, размер массива всегда или 0(если нет пересечений) или 2

                if (t.size() > 0) {
                    //если есть пересечение

                    double tdist = t.get(0).distanceTo(t.get(1));
                    //считаем новую длину

                    if (tdist > mx) {
                        TrueRectangles.clear();
                        TruePoint.clear();
                        //если она больше, то обновляем максимум и ответ

                        mx = tdist;
                        TruePoint.add(t.get(0));
                        TruePoint.add(t.get(1));
                        TrueRectangles.add(rectangles.get(i));
                        TrueRectangles.add(rectangles.get(j));
                    }
                }
            }
        }
    }

    //добавить прямоугольник
    public void addRectangle(Point p1, Point p2, Point pP) {
        Rectangle newestRectangle = new Rectangle(p1, p2, pP);
        rectangles.add(newestRectangle);
    }

    Random random = new Random();

    //получить случайный прямоугольник
    public void getRandomRectangle(int cntQ) {
        for (int i = 0; i < cntQ; i++) {
            Point
                    point1 = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1),
                    point2 = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1),
                    pointP = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);
            addRectangle(point1, point2, pointP);
        }
    }

    //Загрузить задачу из файла
    public void loadFromFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(", ");
                Point[] pQ = new Point[3];
                for (int i = 0; i < 3; i++) {
                    String pS = s[i].replace("(", "");
                    pS = pS.replace(")", "");
                    pS = pS.replace(",", ".");
                    String[] xyS = pS.split(";");
                    pQ[i] = new Point(Double.parseDouble(xyS[0]), Double.parseDouble(xyS[1]));
                }
                rectangles.add(new Rectangle(pQ[0], pQ[1], pQ[2]));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("couldn't read the file");
            ex.printStackTrace();
        }
    }

    //Сохранить задачу в файл
    public void saveToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String s = "";
            for (Rectangle r : rectangles) {
                s += (r + "\n");
            }

            writer.write(s);
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Очистить задачу
    public void clear() {
        rectangles.clear();
        TruePoint.clear();
        TrueRectangles.clear();
    }

    public void render(GL2 gl) {
        if(rectangles!=null)
        for (Rectangle r : rectangles) {
            r.render(gl);
        }

        if(TrueRectangles!=null)
        for (Rectangle r : TrueRectangles) {
            r.renderResult(gl);
        }

        if (TruePoint.size()==2) {
            for (Point p : TruePoint) {
                p.render(gl, 5);
            }
            Line resultLine = new Line(TruePoint.get(0), TruePoint.get(1));
            resultLine.render(gl);
        }
    }
}
