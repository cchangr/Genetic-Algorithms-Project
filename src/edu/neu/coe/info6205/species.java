package edu.neu.coe.info6205;

public class species {

    chromosome headNode;
    int Number;

    public species() {

        headNode = new chromosome();
        Number = configurefile.populationSize;
    }
    public void add(chromosome s) {
        chromosome p = headNode;
        while (p.next != null)
            p = p.next;
        p.next = s;
    }
}

