package com.seriyalexandrov.traveldepts.balance;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void printTest() {
        DebtsGraph graph = new DebtsGraph();

        Vertex Ser = new Vertex("Ser");
        Vertex Tag = new Vertex("Tag");
        Vertex Ann = new Vertex("Ann");

        graph.addDebt(Ser, Tag, 1);
        graph.addDebt(Tag, Ann, 2);
        graph.addDebt(Ann, Ser, 3);

        assertEquals(
                "               Ann            Ser            Tag            \n" +
                        "Ann            X              3.0                           \n" +
                        "Ser                           X              1.0            \n" +
                        "Tag            2.0                           X              ",
                graph.toString());
    }

    @Test
    public void simplifyGraph() {
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
        assertEquals(
                "               Ann            Ser            Tag            \n" +
                        "Ann            X                             3.0            \n" +
                        "Ser            5.0            X                             \n" +
                        "Tag                           1.0            X              ", graph.toString());
    }

    @Test
    public void calculateVeritexBalanceTest() {
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

        assertEquals(
                "Ann            : -2.0\n" +
                        "Edge: 3.0\n" +
                        "Ser            : 4.0\n" +
                        "Edge: 5.0\n" +
                        "Tag            : -2.0\n" +
                        "Edge: 1.0\n",
                Utils.printVertexes(graph));

    }
}