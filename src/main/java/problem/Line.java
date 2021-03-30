package problem;

public class Line {
    double xA;
    double yA;

    double xB;
    double yB;
    public Point n(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
        double A1 = y1 - y2;
        double B1 = x2 - x1;
        double C1 = x1*y2 - x2*y1;
        double A2 = y3 - y4;
        double B2 = x4 - x3;
        double C2 = x3*y4 - x4*y3;
        if ((A1 != 0 && B1 != 0 && A2 !=0 && B2 != 0) || ( B1 == 0 && B2 == 0) || (A1 == 0 && A2 == 0)){
            return null;
        }
        else if ((((((C1/B1 - C2/B2)/(A2/B2 - A1/B1) <= x2) && (C1/B1 - C2/B2)/(A2/B2 - A1/B1) >= x1) && (x2 > x1)) || ((C1/B1 - C2/B2)/(A2/B2 - A1/B1) <= x1) && ((C1/B1 - C2/B2)/(A2/B2 - A1/B1) >= x2) && (x1 > x2)) || (((C1/B1 - C2/B2)/(A2/B2 - A1/B1) <= x3) && ((C1/B1 - C2/B2)/(A2/B2 - A1/B1) >= x4) && (x3 > x4)) || ((C1/B1 - C2/B2)/(A2/B2 - A1/B1) <= x4) && ((C1/B1 - C2/B2)/(A2/B2 - A1/B1) >= x3) && (x4 > x3)){
            return new Point((C1/B1 - C2/B2)/(A2/B2 - A1/B1), ((- A1/B1) * (C1/B1 - C2/B2)/(A2/B2 - A1/B1)) - C1/B1, 1);
        }
        else{
            return null;
        }
    }
}
