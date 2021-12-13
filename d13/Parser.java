import java.util.*;
import java.io.*;

class Parser {
    public void lit(String donnees) throws Exception {
        List<String> feuille = new ArrayList<String>();
        List<String> fold = new ArrayList<String>();

        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String ligne = sc.nextLine(); 
            if(ligne.contains("fold")){
                String[] ligneBis = ligne.split(" ");
                fold.add(ligneBis[2]);
            }
            else{
                feuille.add(ligne);
            } 
        }
     //matriceApresPlis2(feuille,fold); 
     aff(matriceApresPlis2(feuille,fold))  ;      
    }

 
  public Set<String> matriceApresPlis2(List<String> mat , List<String>listeDePlis ){
    Set<String> res = new HashSet<String>();
    for (String pli : listeDePlis){
        String axe =  pli.split("=")[0];
        int valeur  = Integer.parseInt(pli.split("=")[1]);
        res.clear();
        if(axe.equals("x")){
            for (int i = 0 ; i < 1400;i++){
                for (int j = 0 ; j <= 2*valeur ; j++){
                    if (j< valeur && mat.contains(j+","+i)){
                        res.add(j+","+i);
                    } 
                    else if(j >= valeur + 1 && mat.contains(j+","+i)){
                        int valeurDroiteAmeneAGauche = 2*valeur  - j ;
                        res.add(String.valueOf(valeurDroiteAmeneAGauche)+","+i); 
                    }
                }
            }
        }
        else{ 
            for (int i = 0 ; i <= 2*valeur;i++){
                for (int j = 0 ; j <= 1400 ; j++){       
                    if (i< valeur && mat.contains(j+","+i)){
                        res.add(j+","+i);
                    } 
                     if(i>= valeur + 1 && mat.contains(j+","+i)){
                        int valeurBasRemonte = 2*valeur - i ;
                        res.add(j+","+String.valueOf(valeurBasRemonte));
                    }
                }
            }
        }
        mat.clear();
        mat.addAll(res);
    }
  System.out.println(mat+" -" + mat.size());
    return res;
  }

  public void aff(Set<String> l){
    for (int i = 0 ; i < 40 ; i++){
        for (int j = 0 ; j < 40 ; j++){
            if (l.contains(j+","+i)){
                System.out.print("#");
            }
            else{
                System.out.print(".");
            }
        }
        System.out.println();
    }
  }
}
    