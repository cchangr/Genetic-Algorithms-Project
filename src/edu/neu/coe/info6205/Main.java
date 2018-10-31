package edu.neu.coe.info6205;

public class Main {

    public static void main(String[] args){

        //Logger log = Logger.getLogger(Main.class.getName());
        //configurefile.randomcity();
        configurefile.city();
       // log.info("start solve:");
        tspga GA=new tspga();
        species species =new species();
        chromosome chooseBestPath=GA.drive(species);
        chooseBestPath.path();
        //chooseBestPath.logging();
    }

}
