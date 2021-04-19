package Gui;

import problem.Point;
import problem.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Класс формы приложения
 */
public class Form extends JFrame {
    /**
     * панель для отображения OpenGL
     */
    private JFrame frame = new JFrame();

    //JPanel
    private JPanel GLPlaceholder;
    private JPanel root;

    //текста и надписи
    private JLabel problemText;
    private JLabel addRectangleText;
    private JLabel apex1Text;
    private JLabel apex2Text;
    private JLabel rectanglePText;

    //ввод прямоугольных точек
    private JTextField xQuadApex1Field;
    private JTextField xQuadApex2Field;
    private JTextField xQuadPointField;
    private JTextField yQuadApex1Field;
    private JTextField yQuadApex2Field;
    private JTextField yQuadPointField;
    private JTextField quadCountField;
    //ввод прямоугольных точек

    //КНОПКИ
    //основные
    private JButton loadFromFileBtn;
    private JButton saveToFileBtn;
    private JButton clearBtn;
    private JButton solveBtn;
    //добавительные кнопки
    private JButton randomRectangle;
    private JButton addRectangle;
    private JLabel solveText;

    /**
     * таймер
     */
    private final Timer timer;
    /**
     * рисовалка OpenGL
     */
    private final RendererGL renderer;

    /**
     * Конструктор формы
     */
    private Form() {
        super(Problem.PROBLEM_CAPTION);
        // инициализируем OpenGL
        renderer = new RendererGL();
        // связываем элемент на форме с рисовалкой OpenGL
        GLPlaceholder.setLayout(new BorderLayout());
        GLPlaceholder.add(renderer.getCanvas());
        // указываем главный элемент формы
        getContentPane().add(root);

        setSize(getPreferredSize());
        // показываем форму
        setVisible(true);
        // обработчик зарытия формы
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(() -> {
                    renderer.close();
                    timer.stop();
                    System.exit(0);
                }).start();
            }
        });
        // тинициализация таймера, срабатывающего раз в 100 мсек
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTime();
            }
        });
        timer.start();
        //renderer.problem.solve();
        initWidgets();
    }

    public void setSolveText() {
        if (renderer.problem.TruePoint!=null)
            solveText.setText("Отрезок с концами " + renderer.problem.TruePoint.get(0)+ " и " + renderer.problem.TruePoint.get(1));
        else
            solveText.setText("Нет решения");

    }

    /**
     * Инициализация виджетов
     */
    private void initWidgets() {
        // задаём текст полю описания задачи
        problemText.setText("<html>" + Problem.PROBLEM_TEXT.replaceAll("\n", "<br>"));

        //задаю текст для остальных текстов
        addRectangleText.setText("ЗАДАТЬ ПРЯМОУГОЛЬНИК");
        apex1Text.setText("Первая вершина: ");
        apex2Text.setText("Вторая вершина: ");
        rectanglePText.setText("Точка на прямой через две другие вершины: ");

        addRectangle.setText("Добавить прямоугольник");

        addRectangle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double x1 = Double.parseDouble(xQuadApex1Field.getText());
                double y1 = Double.parseDouble(yQuadApex1Field.getText());
                double x2 = Double.parseDouble(xQuadApex2Field.getText());
                double y2 = Double.parseDouble(yQuadApex2Field.getText());
                double xp = Double.parseDouble(xQuadPointField.getText());
                double yp = Double.parseDouble(yQuadPointField.getText());
                Point
                        Point1 = new Point(x1, y1),
                        Point2 = new Point(x2, y2),
                        PointP = new Point(xp, yp);
                renderer.problem.addRectangle(Point1, Point2, PointP);
            }
        });

        randomRectangle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int n = (int) Double.parseDouble(quadCountField.getText());
                renderer.problem.getRandomRectangle(n);
            }
        });

        loadFromFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.showOpenDialog(frame);
                renderer.problem.clear();
                renderer.problem.loadFromFile(fileOpen.getSelectedFile());
            }
        });

        saveToFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileSave = new JFileChooser();
                fileSave.showSaveDialog(frame);
                renderer.problem.saveToFile(fileSave.getSelectedFile());
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.clear();
            }
        });

        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.solve();
                setSolveText();
            }
        });
    }

    /**
     * Событие таймера
     */
    private void onTime() {
        // события по таймеру
    }

    /**
     * Главный метод
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        new Form();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}