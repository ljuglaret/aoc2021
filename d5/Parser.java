import java.util.*;
import java.io.*;

class Parser {
    private String donnees;

    public Parser(String donnees) {
        this.donnees = donnees;
    }

    public static List<Point> lit(String donnees) throws Exception {
        Map<Point, Integer> pointEtNbDeFoisOuIlEstPresent = new HashMap<Point, Integer>();
        int res = 0;
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        List<Point> points = new ArrayList<>();
        while (sc.hasNext()) {
            String ligne = sc.nextLine();
            String ligneSplit[] = ligne.split(" -> ");
            String gaucheSplit[] = ligneSplit[0].split(",");
            String droitSplit[] = ligneSplit[1].split(",");

            int x1 = Integer.parseInt(gaucheSplit[0]);
            int y1 = Integer.parseInt(gaucheSplit[1]);
            int x2 = Integer.parseInt(droitSplit[0]);
            int y2 = Integer.parseInt(droitSplit[1]);

            // System.out.println("----->");
            if ((x1 == x2)
                    || (y1 == y2)
                    // || ((x1 == y1) && (x2 == y2))// diagonale de haut en bas
                    || (Math.abs(x1 - x2) == Math.abs(y1 - y2))) {
                Point debut = new Point(x1, y1);
                Point fin = new Point(x2, y2);
                // debut.affPoint();
                // fin.affPoint();
                for (Point p : debut.pointsEntreDeuxPointsQ2(fin)) {
                    // p.affPoint();
                    points.add(p);
                    if (pointEtNbDeFoisOuIlEstPresent.containsKey(p)) {

                        pointEtNbDeFoisOuIlEstPresent.put(p, pointEtNbDeFoisOuIlEstPresent.get(p) + 1);

                    } else {
                        pointEtNbDeFoisOuIlEstPresent.put(p, 1);
                    }
                }
                // System.out.println("-----");

            }
        }
        for (Map.Entry<Point, Integer> mapentry : pointEtNbDeFoisOuIlEstPresent.entrySet()) {
            if (mapentry.getValue() >= 2) {
                res++;
            }
        }
        System.out.println(res);
        return points;
    }

    public static void main(String[] args) throws Exception {
        lit("donnees.txt");

    }
}