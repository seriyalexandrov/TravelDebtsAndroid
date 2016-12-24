package com.seriyalexandrov.traveldepts.balance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ser on 12/06/16.
 */
public class CalculationTests {

    @Test
    public void calculateDebtsTest1() {

        DebtsGraph graph = new DebtsGraph();

        Vertex Ser = new Vertex("Ser");
        Vertex Tag = new Vertex("Tag");
        Vertex Ann = new Vertex("Ann");

        graph.addDebt(Ser, Tag, 1);
        graph.addDebt(Tag, Ann, 2);
        graph.addDebt(Ann, Ser, 3);

        graph.addDebt(Tag, Ser, 2);
        graph.addDebt(Ann, Tag, 5);
        graph.addDebt(Ser, Ann, 8);

        Utils.simplifyGraph(graph);

        Utils.calculateVertexBalance(graph);

        List<DeptsResultEntry> results = Utils.calculateDebtsOnNormallizedGraph(graph);

        List<DeptsResultEntry> check = new ArrayList<DeptsResultEntry>() {{
            add(new DeptsResultEntry(new Vertex("Ann"), new Vertex("Ser"), 2d));
            add(new DeptsResultEntry(new Vertex("Tag"), new Vertex("Ser"), 2d));
        }};

//        System.out.println(graph);
//
//        System.out.println("Debts:");
//        for (DebtsResultEntry entry : results) {
//            System.out.println(entry);
//        }

        assertEquals(check, results);
    }

    @Test
    public void calculateDebtsTest2() {

        DebtsGraph graph = new DebtsGraph();

        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");

        graph.addDebt(b, a, 3);
        graph.addDebt(c, b, 5);
        graph.addDebt(a, c, 2);
        graph.addDebt(d, a, 7);
        graph.addDebt(c, d, 10);

        Utils.simplifyGraph(graph);

        Utils.calculateVertexBalance(graph);

        List<DeptsResultEntry> results = Utils.calculateDebtsOnNormallizedGraph(graph);

        List<DeptsResultEntry> check = new ArrayList<DeptsResultEntry>() {{
            add(new DeptsResultEntry(new Vertex("a"), new Vertex("c"), 8d));
            add(new DeptsResultEntry(new Vertex("b"), new Vertex("c"), 2d));
            add(new DeptsResultEntry(new Vertex("d"), new Vertex("c"), 3d));
        }};

        assertEquals(check, results);
    }
}