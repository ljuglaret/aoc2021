import java.util.*;
import java.io.*;

class Parser {
    Set<Integer> v = new HashSet<Integer>();
    //100
    int[][] grille = new int[100][100];
    public void lit(String donnees) throws Exception {
        int res = 0 ;
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        int i  = 0;
        while (sc.hasNext()) {
            String ligne = sc.nextLine();      
            for (int j = 0 ; j < ligne.length(); j++){
               grille[i][j] = Integer.parseInt(String.valueOf(ligne.charAt(j)));
            }
            i++;
        }
        int risqueFin = 0 ;
        for (int x = 0 ; x < grille.length;x++){
            for(int j = 0 ; j < grille[0].length ; j++){
                risqueFin+=risque(grille, x , j );
            }
        }
        System.out.println(risqueFin);
      
    }
    public void lit2(String donnees) throws Exception {
        int res = 0 ;
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        int i  = 0;
        while (sc.hasNext()) {
            String ligne = sc.nextLine();      
            for (int j = 0 ; j < ligne.length(); j++){
               grille[i][j] = Integer.parseInt(String.valueOf(ligne.charAt(j)));
            }
            i++;
        }
        List<Integer>pointsF = posPointsFaibles(grille);
        List<Integer>sommesFinales = new ArrayList<Integer>();
        int temp = 1 ;
        for (int x :pointsF){
            System.out.println(convX(grille, x) +"--"+ convY(grille, x));
            bassin(grille, convX(grille, x) , convY(grille, x));
            System.out.println();
            System.out.println("taille:=>"+v.size());

            sommesFinales.add(v.size());
            temp = temp*v.size();
            System.out.println(temp);
            v.clear();
            

        }
        Collections.sort(sommesFinales, Collections.reverseOrder());
        System.out.println(sommesFinales.get(0)*sommesFinales.get(1)*sommesFinales.get(2));
       
      
    }
  
    public int convPosition(int[][] l,int x , int y){
        int c = l[0].length;
        return x*c+ y ;
    }

    public int convX(int[][] mat, int pos){
        int c = mat[0].length;
        return pos/c;
    }
    
    public int convY(int[][] mat, int pos){
        int c = mat[0].length;
    
        return  pos - c*convX(mat, pos)  ;
    } 
   
    public int[]  touslesvoisins(int[][]l,int x , int y){
        int t = l.length - 1  ;
        int nbCol = l[0].length  - 1 ;
        if (x == 0 ){
            if (y == 0){
                        int[]voisins = new int[2];
                        voisins[0] = convPosition(l, x, y+1);
                        voisins[1] = convPosition(l, x+1, y);
                        return voisins;        
            }
            else if (y == nbCol  ){
                        int[]voisins = new int[2];
                        voisins[0] = convPosition(l, x, y-1);
                        voisins[1] = convPosition(l, x+1, y);
                        return voisins;
            }
            else{
                        int[]voisins = new int[3];
                        voisins[0] = convPosition(l, x, y-1);
                        voisins[1] = convPosition(l, x, y+1);
                        voisins[2] = convPosition(l, x+1, y);
                        return voisins;
            }
        }
        else if (x == t){
            if (y == 0){
                        int[]voisins = new int[2];
                        voisins[0] = convPosition(l, x, y+1);
                        voisins[1] = convPosition(l, x-1, y);
                        return voisins;
            }
            else if (y == nbCol ){
                        int[]voisins = new int[2];
                        voisins[0] = convPosition(l, x, y-1);
                        voisins[1] = convPosition(l, x-1, y);
                        return voisins;
            }
            else{
                        int[]voisins = new int[3];
                        voisins[0] = convPosition(l, x, y-1);
                        voisins[1] = convPosition(l, x, y+1);
                        voisins[2] = convPosition(l, x-1, y);
                        return voisins;
            }
        }
        else {
          if (y == 0){
                    int[]voisins = new int[3];
                    voisins[0] = convPosition(l, x, y+1);
                    voisins[1] = convPosition(l, x-1, y);
                    voisins[2] = convPosition(l, x+1, y);
                   return voisins;
          }
          else if (y == nbCol  ){    
                    int[]voisins = new int[3];
                    voisins[0] = convPosition(l, x, y-1);
                    voisins[1] = convPosition(l, x-1, y);
                    voisins[2] = convPosition(l, x+1, y);
                    return voisins;
          }
          else {
            int[]voisins = new int[4];
            voisins[0] = convPosition(l, x, y-1);
            voisins[1] = convPosition(l, x, y+1);
            voisins[2] = convPosition(l, x-1, y);
            voisins[3] = convPosition(l, x+1, y);
            return voisins;
          }
        }
       
    }

    public int risque(int[][]l,int x , int y){
        int risque = 0 ;
        int elt = l[x][y];
       boolean mini = true;
        int[]v = touslesvoisins(l, x, y);
        for (int i = 0 ; i < v.length ; i++){
            if (elt > l[convX(l,v[i])][convY(l,v[i])] ){
                mini = false;
            }
        }
        if(mini){
            risque = elt+1;
        }
        return risque;
    }

   
    public List<Integer> posPointsFaibles(int[][]l){
        List<Integer> pts = new ArrayList<Integer>();     
        for (int x = 0 ; x < l.length ; x++){
            for (int y = 0 ; y < l[0].length ; y++){
                int elt = l[x][y];
                int[]v = touslesvoisins(l, x, y);
                boolean mini = true;
                for (int i = 0 ; i < v.length ; i++){
                    if (elt > l[convX(l,v[i])][convY(l,v[i])] ){
                        mini = false;
                    }
                }
                if(mini){
                    pts.add(convPosition(l, x, y));
                 }
            }
        }
  
        
        return pts;
    }
  
    //verifie que (x2,y2) est dans le bassin constitué autour du point (x1,y1)
    public boolean estDansLeMemeBassin(int[][]l,int x1, int y1, int x2, int y2 ){
        boolean estV = false;
        //for (int i = 0; i < touslesvoisins(l, x1, y1).length ; i++){
          //  int pos = touslesvoisins(l, x1, y1)[i];
           //int voisinPosX = convX(l, pos);
            //int voisinPosY = convY(l, pos);
            if ( l[x2][y2]!= 9 && l[x2][y2] > l[x1][y1]){
                estV = true;
            } 
        //}
        return estV;
    }

    //nombre de points dans le bassin autour d'un point faible 
    public int bassin(int[][]l,int x1, int y1){
       
        int cpt = 0 ;
        int[] voisinsInit = touslesvoisins(l, x1, y1);
          for (int i = 0; i < voisinsInit.length ; i++){
            int pos = touslesvoisins(l, x1, y1)[i];
            int voisinPosX = convX(l, pos);
            int voisinPosY = convY(l, pos);
            if (estDansLeMemeBassin(l,  x1, y1,voisinPosX,voisinPosY)){
               // System.out.println(x1+"-"+y1+ " : " + estDansLeMemeBassin(l, x1, y1,voisinPosX,voisinPosY)) ;
                //System.out.println(voisinPosX+ " - "+voisinPosY + " : " + estDansLeMemeBassin(l, x1, y1,voisinPosX,voisinPosY)) ;
                v.add(convPosition(l, x1, y1));

                v.add(convPosition(l, voisinPosX, voisinPosY));
                cpt = cpt +bassin(l,voisinPosX, voisinPosY);
            }
          }

        
          return cpt;
    }
}
       
  //  }





     