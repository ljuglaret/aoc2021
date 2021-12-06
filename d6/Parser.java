import java.util.*;
import java.io.*;

class Parser {
    private String donnees;

    public Parser(String donnees) {
        this.donnees = donnees;
    }

    public List<Integer> lit() throws Exception {
        int res = 0;
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        List<Integer> points = new ArrayList<>();
        while (sc.hasNext()) {
            String ligne = sc.nextLine();
            String[] ligneT = ligne.split(",");
            for (int i = 0; i < ligneT.length; i++) {
                points.add(Integer.parseInt(
                        String.valueOf(ligneT[i])));
                System.out.println(Integer.parseInt(
                        String.valueOf(ligneT[i])));
            }

        }
        return points;
    }

    public void poissons() throws Exception {
        List<Integer> initial = lit();
        for (int i = 0; i < 7; i++) {
            List<Integer> suivant = ligneSuivante(initial);
            initial.addAll(suivant);
            System.out.println(suivant);
            initial = suivant;
        }
        System.out.println(initial.size());
    }

    public void poissons2() throws Exception {

    }

    public List<Integer> ligneSuivante(List<Integer> l0) {
        List<Integer> l1 = new ArrayList<>();
        for (int elt : l0) {
            if (elt == 0) {
                l1.add(6);
                l1.add(8);
            } else {
                l1.add(elt - 1);
            }
        }
        return l1;
    }
}