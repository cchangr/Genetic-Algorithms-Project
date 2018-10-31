package edu.neu.coe.info6205;

import java.util.Random;

public class chromosome {

    int[] genetic;
    float distance;
    float fitness;
    chromosome next;
    float probability;

    public chromosome(){
        //constractor
        genetic = new int[configurefile.city_number];
        fitness=0.0f;
        distance=0.0f;
        next=null;
        probability =0.0f;
    }

    //initial original random genes
    public void buildOriginalRandomGenes(){
        int k=genetic.length;
        for(int i = 0; i < k; i++){
            genetic[i]=i+1;
        }
        Random r=new Random();
        for(int j = 0; j< k; j++){
            int num= j + r.nextInt(k-j);
            //switch
            int a;
            a= genetic[num];
            genetic[num]= genetic[j];
            genetic[j]=a;
        }
    }

    //calculate fitness
    public void fitness(){
        float wholeDistance=0.0f;
        for(int i = 0; i < configurefile.city_number; i++){
            int firstCity=genetic[i]-1;
            int secondCity=genetic[(i+1) % configurefile.city_number]-1;
            wholeDistance += configurefile.dMap[firstCity][secondCity];
        }
        distance=wholeDistance;
        fitness=1.0f/wholeDistance;
    }



    //copy
    public chromosome copy(){
        chromosome s=new chromosome();
        for(int i = 0; i< genetic.length; i++)
            s.genetic[i]= genetic[i];
        s.distance=distance;
        s.fitness=fitness;
        return s;
    }

    //print the path
    public void path(){
        System.out.print("the shortest path is：");
        for(int i = 0; i< genetic.length; i++)
            System.out.print(genetic[i]+"->");
        System.out.print(genetic[0]);
        System.out.println();
        System.out.print("the shortest distance is：" + distance);
    }



//    public static Logger logger = Logger.getLogger(tspga.class.getName());
//
//    public void logging() {
//        if (chromosome.logger.isInfoEnabled()) {
//            chromosome.logger.info("----------------");
//            chromosome.logger.info("Shortest distance: " );
//            for(int i = 0; i< genetic.length; i++)
//                chromosome.logger.info(genetic[i]+"->");
//
//            chromosome.logger.info("Path: " + distance + "\n");
//        }
//    }

}
