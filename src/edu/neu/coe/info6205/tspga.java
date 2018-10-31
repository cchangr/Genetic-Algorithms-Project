package edu.neu.coe.info6205;

import java.util.Random;

import static edu.neu.coe.info6205.configurefile.tprobablity;

public class tspga {

        //create original species
        public static void originalSpecies(species m)
        {
            int rNumber=configurefile.populationSize;
            for(int i=1; i<=rNumber; i++){
                chromosome h=new chromosome();
                //ramdom gene of original h
                h.buildOriginalRandomGenes();
                m.add(h);
            }
        }

        //the probability of selection
        public static void speciesProbability(species l)
        {
            //calculate fitness
            float wholeFitness=0.0f;
            l.Number =0;
            chromosome p1=l.headNode.next;
            while(p1 != null){
                p1.fitness();
                wholeFitness += p1.fitness;
                l.Number++;
                p1=p1.next;
            }
            //calculate the rate of being selected
            p1=l.headNode.next;
            while(p1 != null){
                p1.probability =p1.fitness/wholeFitness;
                p1=p1.next;
            }
        }

        //fitness proportionate selection
        public static void select(species k){
            //fitness & choose
            speciesProbability(k);
            float excellentDistance=Float.MAX_VALUE;
            chromosome chooseSpecies=null;
            chromosome p2=k.headNode.next;
            while(p2!=null){
                if(excellentDistance > p2.distance){
                    excellentDistance=p2.distance;
                    chooseSpecies=p2;
                }
                p2=p2.next;
            }
            //copy species to new list
            species newSpeciesList=new species();
            int chooseNum=(int)(k.Number * tprobablity);
            for(int i=1;i<=chooseNum;i++){
                chromosome newSpecies=chooseSpecies.copy();
                newSpeciesList.add(newSpecies);
            }

            int roundNumber=k.Number -chooseNum;
            for(int i=1;i<=roundNumber;i++){
                float probability=(float)Math.random();
                chromosome old=k.headNode.next;
                while(old != null && old != chooseSpecies){
                    if(probability <= old.probability)
                    {
                        chromosome newSpecies=old.copy();
                        newSpeciesList.add(newSpecies);
                        break;
                    }else{
                        probability=probability-old.probability;
                    }
                    old=old.next;
                }
                if(old == null || old == chooseSpecies){
                    //copy the last one
                    p2=k.headNode;
                    while(p2.next != null)
                        p2=p2.next;
                    chromosome newSpecies=p2.copy();
                    newSpeciesList.add(newSpecies);
                }

            }
            k.headNode =newSpeciesList.headNode;
        }

        //crossover
        public static void crossover(species o){
            float probability=(float)Math.random();
            if(probability > configurefile.cProbabilitylow && probability < configurefile.cProbabilityhigh){
                chromosome p3=o.headNode.next;
                Random r=new Random();
                int f=r.nextInt(o.Number);
                while(p3 != null && f != 0){
                    p3=p3.next;
                    f--;
                }

                if(p3.next != null) {
                    int begin=r.nextInt(configurefile.city_number);
                    //take point and point.next and then do crossover, generate new chromosome
                    for(int i = begin; i<configurefile.city_number; i++) {
                        //find the equal position fir between point.genes and point.next.genetic[i]
                        //find the equal position sec between point.next.genes and point.genetic[i]
                        int fir,sec;
                        for(fir=0; p3.genetic[fir] != (p3.next.genetic[i]); fir++);
                        for(sec=0; p3.next.genetic[sec] !=(p3.genetic[i]); sec++);
                        //switch gene
                        int tmp;
                        tmp=p3.genetic[i];
                        p3.genetic[i]=p3.next.genetic[i];
                        p3.next.genetic[i]=tmp;
                        p3.genetic[fir]=p3.next.genetic[i];
                        p3.next.genetic[sec]=p3.genetic[i];
                    }
                }
            }
        }

        //mutation, details in report
        public static void mutate(species q){
            chromosome p4=q.headNode.next;
            while(p4 != null) {
                float probability=(float)Math.random();
                if(probability < configurefile.mProbability){
                    Random r=new Random();
                    int left=r.nextInt(configurefile.city_number);
                    int right=r.nextInt(configurefile.city_number);
                    if(left > right){
                        int tmp;
                        tmp=left;
                        left=right;
                        right=tmp;
                    }
                    while(left < right) {
                        int tmp;
                        tmp=p4.genetic[left];
                        p4.genetic[left]=p4.genetic[right];
                        p4.genetic[right]=tmp;
                        left++;
                        right--;
                    }
                }
                p4=p4.next;
            }
        }

        //find the species with highest fitness
        public static chromosome best(species s){
            float distance=Float.MAX_VALUE;
            chromosome bestSpecies=null;
            chromosome p4=s.headNode.next;
            while(p4 != null){
                if(distance > p4.distance){

                    bestSpecies=p4;
                    distance=p4.distance;
                }
                p4=p4.next;
            }
            return bestSpecies;
        }



    public chromosome drive(species l){
        //create original species
        originalSpecies(l);
        for(int i = 0; i<configurefile.generation; i++){
            select(l);
            crossover(l);
            mutate(l);
        }
        return best(l);
    }

}
