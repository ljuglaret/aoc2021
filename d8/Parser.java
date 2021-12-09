import java.util.*;
import java.io.*;



class Parser {
    private String donnees;

    public Parser(String donnees) {
        this.donnees = donnees;
    }

    public void lit() throws Exception {
        int res = 0 ;
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String ligne = sc.nextLine();
            String[] ligneT = ligne.split(" \\| ");
            res+=nbStrSures(ligneT[0]);
        }
       System.out.println(res);
    }

    public static void lit2() throws Exception {
        File file = new File("donnesQ2.txt");
        Scanner sc = new Scanner(file);
        int total = 0 ;
        while (sc.hasNext()) {
            String ligne = sc.nextLine();
            String[] ligneT = ligne.split("\\| ");
          

            total+=traduire(trie(ligneT[0].split(" ")),ligneT[1].split(" "));
        }
        System.out.println(total);
    }

    //sur une ligne retourne le nombre de chaines de caractères de taille différente de 5 et 6
    public int nbStrSures(String ligne) {
        String[] ligneT = ligne.split(" ");
        int res = 0 ;
        for (int i = 0 ; i < ligneT.length;i++){
            if (ligneT[i].length()!=5 && ligneT[i].length()!=6 ){
                res++;
            }
        }
       return res;
    }

    public static String soustraction(String str1, String str2){
        String sub = "";
        String premierOperande = str1;
        String secondeOperande = str2;
        if (str2.length() > str1.length()){
            premierOperande = str2;
            secondeOperande = str1;
        }
        for (int i = 0 ; i < premierOperande.length() ; i++){
            if(!secondeOperande.contains(String.valueOf(premierOperande.charAt(i)))){
                sub+=String.valueOf(premierOperande.charAt(i));
            }
        }
        return sub;
    }

    public static boolean contient(String str1, String str2){
        for (int i = 0 ; i < str2.length();i++){
            if (!str1.contains(String.valueOf(str2.charAt(i)))){
                return false;
            }
        }
       return true;
    }

    public static String sort(String inputString)
    {
        // Converting input string to character array
        char tempArray[] = inputString.toCharArray();
 
        // Sorting temp array using
        Arrays.sort(tempArray);
 
        // Returning new sorted string
        return new String(tempArray);
    }

    public static Map<String,Integer> fabriquePattern(String[] str){
        Map<String,Integer> res = new HashMap<String,Integer>();

        String un = ""; 
        String quatre="";
        String sept = "";
        String huit = "";

        String deux = "";
        String trois = "" ;
        String cinq = "" ;
        String six =  "";
        String neuf = "";
        String zero= "";

        for (int i = 0 ; i < str.length ; i++){
            int l = str[i].length();
            
            if(l == 2){ un = str[i];}
            else if(l==3){sept = str[i];}
            else if(l==4){

                quatre = str[i];

            }
            else if(l==7){ huit = str[i];}
            else if(l==6){ 

                if ((contient(str[i],quatre))&&(contient(str[i],sept))){
                        neuf = str[i];
                    }
                else if ((contient(str[i],sept))){
                            zero = str[i];
                        }
                else{
                    six = str[i];
                }
            }
                        
            else if(l == 5) {
                    if (contient(str[i],sept)){
                                trois = str[i];
                    }
                    else if (contient(neuf,str[i])){
                                    cinq = str[i];
                                }
                    else{
                                deux = str[i];
                            };       
            } 
        }

    
        res.put(zero,0);
        res.put(un,1);
        res.put(deux,2);
        res.put(trois,3);
        res.put(quatre,4);
        res.put(cinq,5);
        res.put(six,6);
        res.put(sept,7);
        res.put(huit,8);
        res.put(neuf,9);

        return res;
    }

 public static int traduire(String[]str, String[] aTraduire){
    
     Map<String,Integer>pattern = fabriquePattern(str);
     int somme = 0 ;
    String s = "";
   
     for (int i = 0 ; i < aTraduire.length; i++){
        for (Map.Entry<String,Integer> mapentry : pattern.entrySet()) {
            //System.out.println(mapentry.getKey()+ " " +mapentry.getValue());
            if (sort(aTraduire[i]).equals(sort(mapentry.getKey()))){
             s+= String.valueOf(mapentry.getValue());
            }
         }
     }
     somme+=Integer.parseInt(s);
     System.out.println(somme);
     return somme;
 }

 public static String[] trie(String[] s){
     List<String> t = new ArrayList<String>();


     List<String> tdeux = new ArrayList<String>();
     List<String> ttrois = new ArrayList<String>();
     List<String> tquatre = new ArrayList<String>();
     List<String> tsept = new ArrayList<String>();
     List<String> tcinq = new ArrayList<String>();
     List<String> tsix  = new ArrayList<String>();

    for (int i = 0 ; i < s.length ; i++){
        switch(s[i].length()){
            case 2 : tdeux.add(s[i]);break;
            case 3 : ttrois.add(s[i]);break;
            case 4 : tquatre.add(s[i]);break;
            case 7 : tsept.add(s[i]);break;
            case 5 : tcinq.add(s[i]);break;
            case 6 : tsix.add(s[i]);break;
        }
    }

//mots de taille : 2 puis 3 puis 4 puis 7 puis 6 puis 5
String[] itemsArray = new String[s.length];
int j = 0 ;
    for (int i = 0 ; i < tdeux.size() ;i++){
        itemsArray[j] = tdeux.get(i);
        System.out.println( itemsArray[i]);

        j++;
    }
    for (int i = 0 ; i < ttrois.size() ;i++){
        itemsArray[j] = ttrois.get(i);
        System.out.println( itemsArray[j]);
        j++;

    }
    for (int i = 0 ; i < tquatre.size() ;i++){
        itemsArray[j] = tquatre.get(i);
        System.out.println( itemsArray[j]);

        j++;
    }
    for (int i = 0 ; i < tsept.size() ;i++){
        itemsArray[j] = tsept.get(i);
        System.out.println( itemsArray[j]);

        j++;
    }
    for (int i = 0 ; i < tsix.size() ;i++){
        itemsArray[j] = tsix.get(i);
        System.out.println( itemsArray[j]);
        j++;
    }
    for (int i = 0 ; i < tcinq.size() ;i++){
        itemsArray[j] = tcinq.get(i);
        System.out.println( itemsArray[j]);
        j++;
    }
    
    
    return itemsArray;
 }
 public static void main(String[] args) throws Exception {
  //  System.out.println(contient("abds", "bs"));
  //"ab" ,"dab", "eafb", "acedgfb", "cdfbe", "gcdfa", "fbcad", "cefabd", "cdfgeb", "cagedb"
  //"ab","dab","eafb", "acedgfb", "cdfgeb",  "cefabd",  "cagedb","cdfbe", "gcdfa", "fbcad"
  String[]t = {"ab" ,"dab", "eafb", "acedgfb", "cefabd","cdfbe", "gcdfa", "fbcad", "cdfgeb", "cagedb"};

   trie(t);
 //traduire(trie(t),new String[]{"cdfeb" ,"fcadb" ,"cdfeb" ,"cdbaf"});
 //traduire(t,new String[]{"cdfeb" ,"fcadb" ,"cdfeb" ,"cdbaf"});

   lit2();


}
}