import java.util.*;
import java.io.*;
class Parser {
    int l = 10;
    int c = 10;
    public void lit(String donnees) throws Exception {
        int[][] grille = new int[l][c];
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        int i = 0 ;
        while (sc.hasNext()) {           
            String ligne = sc.nextLine();      
            for (int j = 0 ; j < ligne.length() ; j++){
                grille[i][j] = Integer.parseInt(String.valueOf(ligne.charAt(j)));
            }
            i++;
        }
        affGrille(grille);
        //Q1
        long count = 0 ;
        for (i = 0 ; i < 100 ; i++){
          
            //count +=etapes(grille);
            //System.out.println(count);
        }
        //Q2
        int j=0;
        long count2 = 0 ;
        while(count2 != grille.length*grille.length){
            count2 =etapes(grille);
            affGrille(grille);
            j++;
            System.out.println(j+" " + count2);
              
            }
           

            
    }

    public long etapes(int[][] grille) {
        for (int x = 0; x < grille.length; x++) {
            for (int y = 0; y < grille[x].length; y++) {
                grille[x][y]++;
            }
        }
        long count = 0;
      
        for (int x = 0; x < grille.length; x++) {
            for (int y = 0; y < grille[x].length; y++) {
                    count = propagationIncendie(grille, x, y);
            }
        }
        return count;
}


   //si grille[x][y] > 9 => grille[x][y] propagation incendie
        //si grille[x][y] == 9 => 0
    static long propagationIncendie(int[][] grille, int x, int y) {
        int xMin = Math.max(0, x-1);
        int xMax = Math.min(grille.length - 1 ,x+1);
        int yMin = Math.max(0, y - 1);
        int yMax = Math.min(grille.length - 1, y + 1);
        if (grille[x][y] > 9){
            grille[x][y] = 0 ;      
            for (int vx = xMin ; vx <= xMax;vx++){
                for(int vy = yMin ; vy <= yMax ; vy++){
                  
                    if (grille[vx][vy] != 0) { 
                        grille[vx][vy]++;
                    }
                    if (grille[vx][vy] >9){
                        propagationIncendie(grille, vx, vy);
                    }
                }
            }  
        }
     
        long count = 0;

        for (int i = 0 ; i < grille.length;i++){
            for (int j = 0 ; j < grille[0].length;j++){
                if (grille[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean grille0(int[][] grille){
        for (int i = 0 ; i < grille.length; i++ ){
            for (int j = 0 ; j < grille.length; j++ ){
                if (grille[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
    }

    public void affGrille(int[][] grille){
        for (int i = 0 ; i < grille.length; i++ ){
            for (int j = 0 ; j < grille.length; j++ ){
                System.out.print(grille[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


}
