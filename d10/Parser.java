import java.util.*;
import java.io.*;

class Parser {
    public void lit(String donnees) throws Exception {
        List<Integer> liste = new ArrayList<>();
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            Stack<Character> valeursLues = new Stack<>();
            String ligne = sc.nextLine();      
            for (int j = 0 ; j < ligne.length(); j++){
                char  valCourante = ligne.charAt(j);
                
                if(estOuvrant(valCourante)){
                    valeursLues.push(valCourante);
                }
                else if ( correspond(valeursLues.peek(), valCourante)){
                    valeursLues.pop();
                }
                else{
                    liste.add(valeur(valCourante));
                    valeursLues.pop();
                }
            }
            Integer sum = liste.stream().reduce(0, Integer::sum);
            System.out.println(sum);
        }    
    }

    public  Optional<Stack<Character>>  pileValeursVues(String ligne){
        Stack<Character> valeursLues = new Stack<>();
        List<Integer> listeDesErreurs = new ArrayList<>();

        for (int j = 0 ; j < ligne.length(); j++){
            char  valCourante = ligne.charAt(j);

            if(estOuvrant(valCourante)){
                valeursLues.push(valCourante);

            }
            else if ( correspond(valeursLues.peek(), valCourante)){
                valeursLues.pop();
            }
            else{
                listeDesErreurs.add(valeur(valCourante));
                valeursLues.pop();
            }
           
        }
        if (listeDesErreurs.size() == 0 ){

            return Optional.of(valeursLues);
           
        }
        else{
            return Optional.empty();
        }
       
    }
    public void lit2(String donnees) throws Exception {
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        Stack<Character> valeursLues =  new Stack<Character>();
        List<Long> scores = new ArrayList<>();
        while (sc.hasNext()) {
            String ligne = sc.nextLine();
            List<Character> charManquants = new ArrayList<>();

            if (!pileValeursVues(ligne).isEmpty()){ 
                valeursLues.addAll( pileValeursVues(ligne).get());

                Iterator<Character> value = valeursLues.iterator();
  
                while (value.hasNext()) {
                    charManquants.add(correspondance( value.next()));
                }
                valeursLues.clear();


                Collections.reverse(charManquants);
                scores.add(sommeQuestion2(charManquants));
            }    
        }
        //the winner is found by sorting all of the scores and then taking the middle score
        Collections.sort(scores);
        int milieu = scores.size()/2;
        System.out.println(scores);
        System.out.println(scores.get(milieu));
    }

    public boolean estOuvrant(char c1){
        return ((c1=='{') || (c1=='[') ||(c1=='(') || (c1=='<'));
    }
    //c1 est un ouvrant, c2 est un fermant
    public boolean correspond(char c1, char c2){
        return  ((c1 == '{') && (c2 == '}'))||
                ((c1 == '[') && (c2 == ']'))||
                ((c1 == '(') && (c2 == ')'))||
                ((c1 == '<') && (c2 == '>'));
        
    }

    public char correspondance(char c1){
        if (c1 == '{') return '}';
        else if (c1 == '[')return ']';
        else if (c1 == '(')return ')';
        else return '>';
       
        
    }

    /*
    
    ): 3 points.
    ]: 57 points.
    }: 1197 points.
    >: 25137 points.
 */
    //c est fermant
    public int valeur(char c){
        if (c == ')'){return 3;}
        else if (c == ']'){return 57;}
        else if (c == '}'){return 1197;}
        else {return 25137;}
    }

    

    /*
    Start with a total score of 0. Then, for each character, multiply the total score by 5 and then increase
     the total score by the point value given for the character 
    
    */

    public int valeur2(char c){
        if (c == ')'){return 1;}
        else if (c == ']'){return 2;}
        else if (c == '}'){return 3;}
        else {return 4;}
    }
    public long sommeQuestion2(List<Character> lc){
        long res = 0 ;

        for (Character c : lc){
            res = res * 5;
            res = res + valeur2(c);
        }
        return res;
    }
    //  2134
    /*
    res =0 
    res = 2
    res = 10 , res = 11
    res =55 , res = 58
    res = 290, res = 294
    */
}
    