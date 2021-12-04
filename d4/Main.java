import java.util.*;
class Main{
    public static void main(String[] args) throws Exception {
        Parser p = new Parser("donnees.txt");
        Jouer jeu = new Jouer(p.getListeChiffresATrouver(), p.getGrilles()) ;
        //jeu.jouer();
        jeu.jouer2();

    }
}