package edu.neu.coe.info6205;

import org.junit.Test;

import static org.junit.Assert.*;

public class tspgaTest {

    @Test
    public void originalSpecies() {

        species l = new species();
        tspga.originalSpecies(l);
        assertNotNull(l);
    }

    @Test
    public void crossover() {
        tspga t1 = new tspga();
        species m = new species();
        species n = new species();
        tspga t2 = new tspga();
        t2 = t1;
        t1.originalSpecies(m);
        assertNotEquals(n, m);

    }

    @Test
    public void mutate() {
        tspga t1 = new tspga();
        species m = new species();
        species n = new species();
        tspga t2 = new tspga();
        t2 = t1;
        t1.originalSpecies(m);
        assertNotEquals(n, m);
    }
}
