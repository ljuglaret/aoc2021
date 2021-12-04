import java.io.*;
import java.util.*;

/*
initial :
liste d ' entiers
liste de carres 

parcours de tous les entiers
    Le premier carré gagnant est celui dont une ligne ou une colonne (complète) contient une partie des entiers.
*/
class Jouer {

    private List<Integer> nb ;
    private List<Grille<Integer>> l;
    public Jouer(List<Integer> nb ,List<Grille<Integer>> l){
        this.nb = nb;
        this.l = l ;
    }
    
   
    // à chaque itération il peut y avoir soit : 
    // -> une grille gagnante : Optional.of(grille)
    // -> aucun gagnant :  Optional.empty()
  public Optional<Grille<Integer>> grilleGagnante(){
    for (Grille<Integer> grille : l){
        if(grille.ligneOuColonneComplete()){
           return Optional.of(grille);
        }
    }
    return Optional.empty();
  }

  //il n'y a pas de gagnant , on joue de nouveau (en retirant la valeur que l'on vient de chercher dans les grilles)
  // sinon on calcule la valeur finale
  public void jouer(){
      //enregistrement de la position
    for (Grille<Integer> grille : l){
        grille.enregistrePosition(nb.get(0));
    }
    if (grilleGagnante().isEmpty()){
        nb.remove(nb.get(0));
        Jouer nouvelleIte = new Jouer(nb,l);
        nouvelleIte.jouer();
    }
    else{
        Grille grilleGagnante = grilleGagnante().get();
        System.out.println(nb.get(0));
        System.out.println(grilleGagnante.getL().toString());
        System.out.println(grilleGagnante.getValeursRencontrees().toString());

        System.out.println(bingo(nb.get(0) , grilleGagnante.getValeursRencontrees(),grilleGagnante.getL()));
        
    }
  }

  public int bingo(int derniereValVue,Map<Integer,Position>valeursRencontrees, List<List<Integer>>matGagnante){
    List<Integer> entierBingoVu = new ArrayList<Integer>();
    for (Map.Entry<Integer,Position> pair : valeursRencontrees.entrySet()){
        entierBingoVu.add(pair.getKey()); 
    }
    
    int somme = 0;
    for (List<Integer>ligne : matGagnante){
        for (int x : ligne ){
            if (!entierBingoVu.contains(x)){
                somme+=x;
            }
        }
    }
  
    return somme*derniereValVue;
    }

    public void jouer2(){
        
      
      while (l.size() != 0){
        
        for (Grille<Integer> grille : l){
            grille.enregistrePosition(nb.get(0));
        }
        if (grilleGagnante().isEmpty()){
            nb.remove(nb.get(0));
            Jouer nouvelleIte = new Jouer(nb,l);
            nouvelleIte.jouer();
        }
        else{
            Grille grilleGagnante = grilleGagnante().get();
            System.out.println(bingo(nb.get(0) , grilleGagnante.getValeursRencontrees(),grilleGagnante.getL()));
            l.remove(grilleGagnante);                        
        }
    

       
      }
    }
}