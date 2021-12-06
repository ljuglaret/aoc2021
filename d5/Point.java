import java.util.*;

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void affPoint() {
        System.out.println("( " + x + " , " + y + ")");
    }

    // Point(1,2) Point(1,4) => [Point(1,2),Point(1,3),Point(1,4)]
    List<Point> pointsEntreDeuxPointsQ2(Point p2) {
        List<Point> res = new ArrayList<Point>();

        // x1 == x2
        if (this.x == p2.x) {
            int ymin = y;
            int ymax = p2.y;
            if (p2.y < ymin) {
                ymin = p2.y;
                ymax = y;
            }
            for (int j = ymin; j <= ymax; j++) {
                // System.out.print("(" + x + "," + j + ")");
                res.add(new Point(x, j));
            }
        }
        // y1 == y2
        else if (this.y == p2.y) {
            int xmin = x;
            int xmax = p2.x;
            if (p2.x < xmin) {
                xmin = p2.x;
                xmax = x;
            }
            for (int j = xmin; j <= xmax; j++) {
                res.add(new Point(j, y));
            }
        }

        /*
         * else if ((x == y) && (p2.x == p2.y)) {
         * int xmin = x;
         * int xmax = p2.x;
         * if (p2.x < xmin) {
         * xmin = p2.x;
         * xmax = x;
         * }
         * for (int j = xmin; j <= xmax; j++) {
         * res.add(new Point(j, j));
         * }
         * }
         */

        // Math.abs(x1 - x2) == Math.abs(y1 - y2)).
        // (0,8)(8,0)
        // (8,1) (6,3)=> 6-3 7-2 8-1
        // (8,0) (0,8)
        // (6,4) (2,0)
        else {
            int inc = 0;
            int xdepart = p2.x;
            int xarrivee = x;
            int ydepart = p2.y;
            int yarrivee = y;
            if (p2.y < y) {
                xdepart = x;
                xarrivee = p2.x;
                ydepart = y;
                yarrivee = p2.y;
            }

            for (int j = ydepart; j >= yarrivee; j--) {

                if (xdepart < xarrivee) {
                    res.add(new Point(xdepart + inc, j));// bg vers hd
                } else {
                    res.add(new Point(xdepart - inc, j));// bd vers hg

                }
                inc++;
            }

        }
        return res;

    }

    List<Point> pointsEntreDeuxPoints(Point p2) {
        List<Point> res = new ArrayList<Point>();

        // x1 == x2
        if (this.x == p2.x) {
            int ymin = y;
            int ymax = p2.y;
            if (p2.y < ymin) {
                ymin = p2.y;
                ymax = y;
            }
            for (int j = ymin; j <= ymax; j++) {
                // System.out.print("(" + x + "," + j + ")");
                res.add(new Point(x, j));
            }
        }
        // y1 == y2
        if (this.y == p2.y) {
            int xmin = x;
            int xmax = p2.x;
            if (p2.x < xmin) {
                xmin = p2.x;
                xmax = x;
            }
            for (int j = xmin; j <= xmax; j++) {
                res.add(new Point(j, this.y));
            }
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Point) {
            Point p2 = (Point) obj;
            return (this.getX() == p2.getX()) && (this.getY() == p2.getY());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        java.awt.Point p = new java.awt.Point(x, y);

        return p.hashCode();
    }

}