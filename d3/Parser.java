import java.io.*;
import java.util.*;


class Parser {

    /**
     * compter les 1 au fur et à mesure
00100  -> 0 0 1 0 0
11110  -> 0 0 1 0 0 + 1 1 1 1 0   = 1 1 2 1 0
10110  -> 1 1 2 1 0 + 1 0 1 1 0 = 2 1 3 2 0
10111
10101
01111
00111
11100
10000
11001
00010
01010

    simultanement : compter les 0 au fur et à mesure : pareil mais sur les complémentaires
00100  -> 1 1 0 1 1
11110  -> 1 1 0 1 1 + 0 0 0 0 1   = 1 1 0 1 2
10110  -> 1 1 0 1 2 + 0 1 0 0 1   = 1 2 0 1 3
     
     On compare terme à termes
     additionUn ->   21320
     additionZero -> 12013
     --------------- 10110
     */
    
    public int nb0ParColonnes(String donnees)throws Exception{
        String res = "";
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        List<Integer> calculZ=new ArrayList<Integer>();
        List<Integer> calculU=new ArrayList<Integer>();
        int i=0;
        int tailleLigne = 0;
            while (sc.hasNextLine()){
                String ligne = sc.nextLine();
                String comp = complementaire(ligne);
               

                if(i==0){
                    tailleLigne = ligne.length();
                    for (int j = 0 ; j < ligne.length() ; j++){
                        calculZ.add(Integer.parseInt(String.valueOf( comp.charAt(j))));

                        calculU.add(Integer.parseInt(String.valueOf(ligne.charAt(j))));

                    }
                }
                else{
                    Integer[]tempU = new Integer[ ligne.length()];
                    Integer[] tempZ= new Integer[ ligne.length()];
                    for (int j = 0 ; j < ligne.length();j++){
                        if (ligne.charAt(j) == '0'){
                            tempZ[j]=calculZ.get(j) + 1;
                            tempU[j]=calculU.get(j) + 0;
                        }
                        else{
                            tempZ[j]=calculZ.get(j) + 0;
                            tempU[j]=calculU.get(j) + 1;
                        }
                    }
                    calculU.clear();
                    calculZ.clear();   
                    Collections.addAll(calculU,tempU);
                    Collections.addAll(calculZ,tempZ);
                }
                i++;  
               
            }
             System.out.println(calculU);
             System.out.println(calculZ);
            for (int j = 0 ; j < tailleLigne ;j++){
                System.out.println(calculU.get(j)>calculZ.get(j));
                if (calculU.get(j)>calculZ.get(j)){
                    res+="1";
                }
                else{
                    res+="0";
                }
            }
            
            return  Integer.parseInt(res,2)*  Integer.parseInt(complementaire(res),2);
        }

    public String complementaire(String s){
        String comp = "";
        for (int i = 0 ; i < s.length() ; i ++){
            char ite = s.charAt(i);
            if (ite== '0'){
                comp += '1';
            }
            else{
                comp+='0';
            }
        }
        return comp;
    }

    /*
    remarque : si il y autant de 0 que de 1 dans une colonne peut importe
    que l'on choisisse lequel est le maximum puisque on se retrouve dans une situtation de symétrie
    exemple
     (0,1)
     (1,0)

     Soit on considère qu'il y a plus de 0 que de 1 :
        00
        et son complémetaire : 11
     Soit on considère qu'il y a plus de 1 que de 0 et on obtient le symétrique du cas précédent :
        11
        complémetaire 00
    
    Comme au final on doit retourner le produit et que cette opération est  commutative, peu importe la convention choisie.
     */ 

     //Partie 2

     /*
     oxygen generator rating
    => 11110, 10110, 10111, 10101, 11100, 10000, 11001. 2eme position, majorité de 01
    => 10110, 10111, 10101,10000. 3eme position, majorité de 1
    => 10110, 10111, 10101.4eme position majorité de 1
    => 10110, 10111.5eme position égalite, on garde celui avec 1
    => 10111

    CO2 scrubber rating
    cette fois on garde les minorités, et si choix alors 0
  
     */

     //oxygen generator rating majorité et 1
     //nouvelleListeOxyge,e (2,[00,01]) -> 01
     //nouvelleListeOxyge,e (4,[10110, 10111, 10101]) -> 10110, 10111
     public List<String> nouvelleListeOxygene ( int pos, List<String> s){
        List<String> res = new ArrayList<String>() ;
        int compte0 = 0;
        int compte1 = 0;
         for (int i = 0 ; i < s.size() ; i++){
            if (s.get(i).charAt(pos) == '0') {
            compte0++;
            }
            else{
                compte1++;
            }
         }
         for (int i = 0 ; i < s.size() ; i++){
             if(compte0 <= compte1 && s.get(i).charAt(pos) == '1'){
                 res.add(s.get(i));
             }
             else if (compte0 > compte1 && s.get(i).charAt(pos) == '0'){
                res.add(s.get(i));
             }
         }
         return res;
     }

    //co2 minorité et 0
     public List<String> nouvelleListCo2 ( int pos, List<String> s){
        List<String> res = new ArrayList<String>() ;
        int compte0 = 0;
        int compte1 = 0;
         for (int i = 0 ; i < s.size() ; i++){
            if (s.get(i).charAt(pos) == '0') {
            compte0++;
            }
            else{
                compte1++;
            }
         }
         for (int i = 0 ; i < s.size() ; i++){
             if(compte0 <= compte1 && s.get(i).charAt(pos) == '0'){
                 res.add(s.get(i));
             }
             else if (compte1 < compte0 && s.get(i).charAt(pos) == '1'){
                res.add(s.get(i));
             }
         }
         return res;
     }

     public List<String> lecture(String donnees) throws Exception{
         List<String> l= new ArrayList<String>();
            File file = new File(donnees);
            Scanner sc = new Scanner(file);
                while (sc.hasNextLine()){
                   l.add(sc.nextLine());
                }      
                return l;
     }

     public void partie2(String donnees) throws Exception{
         String oxygene = "";
        String co2 = "";
        List<String> l = lecture(donnees);
        List<String> oxy  = new ArrayList<String>();
        oxy.addAll(l);
        List<String> cO2  = new ArrayList<String>();
        cO2.addAll(l);
        for (int pos = 0 ; pos < l.get(0).length() ; pos++){
       
            List<String> tempOxy = new ArrayList<String>();
            tempOxy.addAll(nouvelleListeOxygene(pos,oxy));
            List<String> tempCO2= new ArrayList<String>();
           tempCO2.addAll(nouvelleListCo2(pos,cO2));

            oxy.clear();
            oxy.addAll(tempOxy);
            cO2.clear();
            cO2.addAll(tempCO2);
            if (oxy.size() == 1){oxygene =oxy.get(0);}
            if (cO2.size() == 1){ co2 =  cO2.get(0);}
          
        
    }
    System.out.println(Integer.parseInt(oxygene,2) * Integer.parseInt(co2,2));
     }

}