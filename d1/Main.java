import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
            D1F d1 = new D1F();
           
            List<Integer> elt = d1.elementsFichiers("donnees.txt");
            System.out.println( d1.nombreDeValSupGlissantes(elt));
  }
}

