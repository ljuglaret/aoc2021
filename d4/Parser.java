import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.*;
class Parser {
    List<Grille<Integer>> grilles = new ArrayList<Grille<Integer>>();
    List<Integer> listeChiffresATrouver ;

    public Parser(String donnees) throws Exception{

        List<List<String>> blocs = lit(donnees);

        listeChiffresATrouver = litCh(blocs.get(0));
        //System.out.println(listeChiffresATrouver);
        for (int i = 1 ; i < blocs.size() ; i++){
            Grille grille = litGr(blocs.get(i));
            grilles.add(grille);
           // grille.afficherGrille();

        }
    }

    public List<List<String>> lit(String donnees) throws Exception{
        
        List< List<String>> blocs = new ArrayList<List<String>>();
        List<String> blocTemp = new ArrayList<String>();

        List<String> l = new ArrayList<>();
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String ligne = sc.nextLine();
            if (ligne.isEmpty()){
                blocs.add(blocTemp);
                blocTemp = new ArrayList<String>();
            }
           else{
               blocTemp.add(ligne);
           }
        }

      return blocs;
    }

    public List<Integer>litCh(List<String>l){
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0 ; i < l.size() ; i++){

            try{
                String[] temp = l.get(i).split(",");
                for (int j = 0 ; j < temp.length ; j++){
                    res.add(Integer.parseInt(String.valueOf(temp[j])));
                }
            }
            catch(NumberFormatException e){
            }
        }
        return res;
    }

    //[14 21 17 24  4, 10 16 15  9 19, 18  8 23 26 20, 22 11 13  6  5,  2  0 12  3  7]
    //=>[[14,21,17,24,4],[10,16,15,9,19],...]

    public Grille litGr(List<String>l){
        List<List<Integer>>matrice = new ArrayList<List<Integer>>();
        for (int i = 0 ; i < l.size();i++){
            matrice.add(parseLigneMat(l.get(i)));
        }
        Grille grille = new Grille(matrice);
        return grille;
    }
    //"14 21 17 24  4" -> [14,21,17,24,4]
    public List<Integer> parseLigneMat(String l){
        List<Integer>res = new ArrayList<Integer>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(l);
        while(m.find()) {
            res.add(Integer.parseInt(m.group()));
        }
        return res;
    }
    public List<Grille<Integer>> getGrilles() {
        return grilles;
    }
    public List<Integer> getListeChiffresATrouver() {
        return listeChiffresATrouver;
    }
 
    public List<String> lectureLigne(String ligne){
        List<String> res = new ArrayList<String>();
        String[]temp = ligne.split(" ");
        for (int i = 0 ; i < temp.length ; i++){
            
        }
        return res;
    }
}