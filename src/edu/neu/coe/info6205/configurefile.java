package edu.neu.coe.info6205;

public class configurefile {

    static int city_number=10; //city number
    static int populationSize =1000; // population size
    static int generation =100; //generation times
    static float cProbabilitylow =0.6f; //crossover probability
    static float cProbabilityhigh =0.95f;//
    static double tprobablity = 0.25;//probability of reproduction
    static float mProbability =0.1f;//mutate probability
    static float[][] dMap; //map


//    public static void randomcity(){
//        int[][] arrays = new int[city_number][city_number];
//
//        Random random=new Random();
//        int r=0;
//        for(int i=0;i<arrays.length;i++){
//            for(int j=0;j<city_number;j++){
//                r=random.nextInt(100);
//                arrays[i][j]=r;
//            }
//        }
//         calDistance(arrays);
//    }


    public static void calDistance(int[][] map){

        city_number =map.length;
        dMap =new float[city_number][city_number];
        for(int i = 0; i< city_number; i++) {
            for(int j = i; j< city_number; j++) {
                float distance=(float)Math.sqrt((map[i][0] - map[j][0])*(map[i][0] - map[j][0]) +
                        (map[i][1] - map[j][1])*(map[i][1] - map[j][1]));

                dMap[i][j]=distance;
                dMap[j][i]= dMap[i][j];
            }
        }
    }


    public static void city(){

        //10 cities, best solution: 147
		int[][] arrays={
				{0,0},{12,32},{5,25},{8,45},{33,17},
				{25,7},{15,15},{15,25},{25,15},{41,12}};
//
//      int[][] arrays={
//                {60,200},{180,200},{80,180},{140,180},
//                {20,160},{100,160},{200,160},{140,140},
//                {40,120},{100,120},{180,100},{60,80},
//                {120,80},{180,60},{20,40},{100,40},
//                {200,40},{20,20},{60,20},{160,20}};
        calDistance(arrays);
    }
}
