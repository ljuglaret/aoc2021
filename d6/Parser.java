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

    public int compte(long[]t, int x){
        int res = 0 ;
        for (int i = 0 ; i < t.length ; i++){
            if(t[i] == x){
                res++;   
            }
        }
        return res;
    }

    public void affPoissons(long[] grille){
        for (int i = 0 ; i < grille.length ; i++){
            System.out.print(grille[i]);
        }
        System.out.println();
    }

    public void poissons2() throws Exception {
        List<Integer> initial = lit();

        long[] fish = new long[9];

        for (int elt : initial) {
            fish[elt]++;
        }

        for (int d = 1; d <= 80; d++) {    
            //Each day, a 0 becomes a 6 and adds a new 8 to the end of the list,
            long sixAAjouter = fish[0];
            
            for (int i = 0; i <8; i++) {
            // while each other number decreases by 1 if it was present at the start of the day.
                fish[i] = fish[i+1 ] ;
            }

            fish[6] = fish[6]+sixAAjouter;
            fish[8] = sixAAjouter;
            //affPoissons(fish);

        }
        long somme = 0;
        for (int i = 0; i < fish.length; i++) {
            somme += fish[i];
        }
        System.out.println(somme);
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

