import java.util.*;

class Grille<T> {

    private List<List<T>> l;
    private Map<T,Position> valeursRencontrees = new HashMap<T,Position>();

    public Grille(List<List<T>>l){
        this.l = l;
    }

    public List<List<T>> getL() {
        return l;
    }

    public void enregistrePosition(T elt){
        for (int i = 0 ; i < l.size() ; i++){
            List<T>ligne = l.get(i);
            for(int j = 0 ; j < ligne.size() ; j++){
                T col = ligne.get(j);
                if (col.equals(elt)){
                    valeursRencontrees.put(elt,new Position(i,j));
                    //return true;
                }
            }
        }
       // return false;
    }

    public Map<T,Position> getValeursRencontrees() {
        return valeursRencontrees;
    }

    public void setValeursRencontrees(T addElt, Position pos){
        valeursRencontrees.put(addElt,pos);
    }

    public boolean ligneOuColonneComplete(){
        List<Position> toutesLesPositions = new ArrayList<Position>();
        for (Map.Entry<T,Position> pair : getValeursRencontrees().entrySet()){
            toutesLesPositions.add(pair.getValue()); 
        }
        //dans une grille 3*3
        //[(0,0),(0,1),(0,2),....] => ligne 0 complete
        //[...,(0,2),..,(1,2),..,(2,2)] => colonne 2 complete
        for (int i =  0 ; i < l.size() ;i++){
            int cptSurLigne = 0 ;
            int cptSurColonne = 0 ;
            int tailleGrille = l.size();
        for (Position pos : toutesLesPositions){
           
                if (pos.getX() == i){
                    cptSurLigne++;
                    //une ligne complete
                    if(cptSurLigne == tailleGrille){
                        System.out.println("ligne " + i);
                        return true;
                    }
                }
                if (pos.getY() == i){
                    cptSurColonne++;
                    //une colonne complete
                    if(cptSurColonne == tailleGrille){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}