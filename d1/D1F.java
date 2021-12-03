import java.io.File;
import java.util.*;
class D1F{
    
    public List<Integer> elementsFichiers(String donnees)throws Exception{
        List<Integer> l = new ArrayList<>();
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            l.add(Integer.parseInt(sc.nextLine()));
        }
        return l;
    }
    public int nombreDeValSup(List<Integer> l) throws Exception {
        int premiereValeur = Integer.MAX_VALUE;
        int compteValSupAValPrecedente = 0 ;
        
        for (Integer suivant : l){
            if(suivant > premiereValeur){
                compteValSupAValPrecedente++;
            }
            premiereValeur = suivant;
        }
        return compteValSupAValPrecedente;
    }

    public int nombreDeValSupGlissantes(List<Integer> elementsEnEntree)throws Exception {
        List<Integer> sommerParTrois = new ArrayList<Integer>();
        for (int i = 0 ; i < elementsEnEntree.size() - 2 ; i++){
            int somme = 0 ;
            for (int j = 0 ; j < 3 ; j++){
                somme+=elementsEnEntree.get(i+j);
            }
            sommerParTrois.add(somme);
        }
        return nombreDeValSup(sommerParTrois);
    }

}