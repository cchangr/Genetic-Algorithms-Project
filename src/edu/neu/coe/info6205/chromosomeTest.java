package edu.neu.coe.info6205;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class chromosomeTest {

    @Test
    public void fitness() {

        int[] genetic={1,2};
        int[][] distancemap = {{2,1},{2,1}};
        float wholeDistance=0.0f;
        for(int i = 0; i < 2; i++)
        {
            int firstCity=genetic[i]-1;
            int secondCity=genetic[(i+1) % 2]-1;

            wholeDistance += distancemap[firstCity][secondCity];
        }
        float distance = wholeDistance;
        float fitness = 1.0f / wholeDistance;
        assertEquals(3.0,distance,0.5);
    }
}