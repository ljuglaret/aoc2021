import java.io.*;
import java.util.*;

class Parser {

    public List<String> lit(String donnees)throws Exception{
        List<String> l = new ArrayList<>();
        File file = new File(donnees);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            l.add(sc.nextLine());
        }
        return l;
    }

    public void parse(List<String>l){
        int horizontal = 0;
        int depth = 0 ;
        for (String indication : l){
            switch (indication.split(" ")[0]) {
                case "forward" : horizontal  += Integer.parseInt(indication.split(" ")[1]); break;
                case "down" : depth  += Integer.parseInt(indication.split(" ")[1]); break;
                case "up" : depth -= Integer.parseInt(indication.split(" ")[1]); break;
                default : break;
            }
        }
        System.out.println(horizontal*depth);
    }

    /*
    
    down X increases your aim by X units.
    up X decreases your aim by X units.
    forward X does two things:
        It increases your horizontal position by X units.
        It increases your depth by your aim multiplied by X.

    */

    public void parseP2(List<String>l){
        int horizontal = 0;
        int depth = 0 ;
        int aim = 0;
        for (String indication : l){
            switch (indication.split(" ")[0]) {
                case "forward" :
                    horizontal  += Integer.parseInt(indication.split(" ")[1]);
                    depth  +=  (aim*Integer.parseInt(indication.split(" ")[1]));
                    break;
                case "down" : aim  += Integer.parseInt(indication.split(" ")[1]); break;
                case "up" : aim -= Integer.parseInt(indication.split(" ")[1]); break;
                default : break;
            }
        }
        System.out.println(horizontal*depth);
    }

}