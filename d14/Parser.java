
import java.util.*;
import java.io.*;

class Parser {
    private String pattern;
    private HashMap<String,String> instructions = new HashMap<String,String>();

    
    public void lit(String donnees) throws Exception {

        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String ligne = sc.nextLine();
            if (!ligne.contains("->")) {
               pattern = ligne;
            } else {
                instructions.put(ligne.split(" -> ")[0] , ligne.split(" -> ")[1] );
            }
        }
       // System.out.println(derniereChaine());
        System.out.println(q2(10));
    }



// calcul naif 
    public String derniereChaine() {
        String res = "";   
        for (int j = 0;j < 10; j++) {    
            for (int i =0; i < pattern.length()  -  1; i++) {
                String strB = "" + pattern.charAt(i) + pattern.charAt(i+1);
                res+= strB.charAt(0)+instructions.get(strB);
            }
            res+=pattern.charAt(pattern.length()-1);
            pattern = res;
            res = "";
           System.out.println("j : " + j);
        }
        return pattern;        
    }

    public long calculQ1(String s1){
        long minimal = Long.MAX_VALUE ;
        long maximal = Long.MIN_VALUE ;
        Map<Character,Long> compte = new HashMap<Character,Long>();
        for(int i = 0 ; i < s1.length() ; i++){
            char lettre = s1.charAt(i);
            if(compte.containsKey(lettre)) {
                compte.put(lettre,compte.get(lettre)+1);
            }
            else{
                compte.put(lettre,Long.valueOf(1));
            }
        }

        for (Map.Entry<Character,Long> mapEntry : compte.entrySet()){
            if (mapEntry.getValue()<minimal){
                minimal = mapEntry.getValue();
            }
            if (mapEntry.getValue() > maximal){
                maximal = mapEntry.getValue();
            }
        }
        return maximal - minimal;
    }

    public <T>void additionSiPossible( HashMap<T, Long> map, T cle,Long valeur){  
        if (map.containsKey(cle)){
            map.put(cle, map.get(cle)+valeur);}
        else {
            map.put(cle, valeur);
        }
    }
    public  Long q2(int x) {

        // Initialisaton à partir de la première ligne fournie en entrée
        //  toutes les lettres présentes
        HashMap<Character, Long> compterLettres = new HashMap<>();
        for (Character ch : pattern.toCharArray()) {
            additionSiPossible(compterLettres, ch, 1L);
        }
        // tous les couples
        HashMap<String, Long> couples = new HashMap<>();
        for (int i=0; i<pattern.length()-1;i++) {
            String cle =  "" + pattern.charAt(i) + pattern.charAt(i+1);
           additionSiPossible(couples, cle, 1L);
        }

        // Décompte des caractères trouvés à chaque itération
        for (int i = 0; i < x; i++) {
            HashMap<String, Long> couplesLignesCourante = new HashMap<>();
            for (String nomDuCouple : couples.keySet()) {
                if (instructions.containsKey(nomDuCouple)) {
        
                    char caractereMilieu =  instructions.get(nomDuCouple).charAt(0);
                    String gauche = nomDuCouple.charAt(0) + String.valueOf(caractereMilieu);
                    String droite = String.valueOf(caractereMilieu) + nomDuCouple.charAt(1);
                 
                    additionSiPossible(couplesLignesCourante, gauche, couples.get(nomDuCouple));

                    additionSiPossible(couplesLignesCourante, droite, couples.get(nomDuCouple));
                    //System.out.println("nouveaux couple "+i+"-" + couplesLignesCourante);
                    
                    //ancienne somme + nouvelle somme des lettres 
                    if(compterLettres.containsKey(caractereMilieu)){
                        compterLettres.put(caractereMilieu, compterLettres.get(caractereMilieu)+ couples.get(nomDuCouple));
                    }
                    else{
                        compterLettres.put(caractereMilieu, 1L);
                    }
                }
            }
            couples = couplesLignesCourante;
        }
        Long max = Collections.max(compterLettres.values());
        Long min = Collections.min(compterLettres.values());
        return max-min;
    }
}
 

        

        
        
         

    
    
    
    
        
    
    
    
    
        
    



