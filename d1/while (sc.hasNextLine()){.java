while (sc.hasNextLine()){
    int suivant = Integer.parseInt(sc.nextLine());
    if(suivant > premiereValeur){
        compteValSupAValPrecedente++;
    }
    premiereValeur = suivant;
}