import java.util.*;
import java.io.*;

class Parser {
    private String donnees;

    public Parser(String donnees) {
        this.donnees = donnees;
    }

    public List<Integer> lit() throws Exception {
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

    public void calculePlusPetitCout() throws Exception{
        List<Integer>points = lit();
        int minimum = Integer.MAX_VALUE;
        for (int p : points){
            int cout = 0;
            for (int p2 : points){
                cout+=Math.abs(p2-p);
            }
            if (cout <= minimum){
                minimum = cout;
            }
        }
        System.out.println(minimum ) ;
    }

    /*
    Instead, each change of 1 step in horizontal position
    costs 1 more unit of fuel than the last:
        the first step costs 1,
        the second step costs 2,*
        the third step costs 3, and so on.
    p1 à p2 : somme de 1 à ecart( p1,p2)

    */

    public static int somme (int x , int y){
        int res = 0 ;
        int min = Math.min(x,y);
        int max = Math.max(x,y);
       int ecart = max - min;
        for (int i = 1 ; i <= ecart ; i++){
            res +=i;
        }
        
        return res;
    }

    public void calculePlusPetitCoutV2() throws Exception{
        List<Integer>points = lit();
        long minimum = Integer.MAX_VALUE;
        for (int  p= 0 ; p < 1000 ; p++){
            int cout = 0;
            for (int p2 : points){
                cout+=somme(p,p2);
            }
            if (cout <= minimum){
                minimum = cout;
            }
        }
        System.out.println(minimum ) ;
    }
}