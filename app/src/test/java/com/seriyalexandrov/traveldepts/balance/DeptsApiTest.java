package com.seriyalexandrov.traveldepts.balance;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class DeptsApiTest {

    @Test
    public void deptOneToOneTest() {
        DebtsGraph graph = new DebtsGraph();

        DebtsApi.debtOneToOne(graph, "a", "b", 90);

        Assert.assertEquals(
                "               a              b              \n" +
                "a              X              90.0           \n" +
                "b                             X              ", graph.toString());
    }

    @Test
    public void debtAllToOneExceptCreditorEqualPartsTest() {
        DebtsGraph graph = new DebtsGraph();

        graph.addVertex(new Vertex("a"));
        graph.addVertex(new Vertex("b"));
        graph.addVertex(new Vertex("c"));
        graph.addVertex(new Vertex("d"));

        DebtsApi.debtAllToOneExceptCreditorEqualParts(graph, new Vertex("a"), 90);

        Assert.assertEquals(
                "               a              b              c              d              \n" +
                        "a              X              30.0           30.0           30.0           \n" +
                        "b                             X                                            \n" +
                        "c                                            X                             \n" +
                        "d                                                           X              ", graph.toString());
    }

    @Test
    public void debtAllToOneEqualPartsTest() {
        DebtsGraph graph = new DebtsGraph();

        graph.addVertex(new Vertex("a"));
        graph.addVertex(new Vertex("b"));
        graph.addVertex(new Vertex("c"));
        graph.addVertex(new Vertex("d"));

        DebtsApi.debtAllToOneEqualParts(graph, new Vertex("a"), 100);

        Assert.assertEquals(
                "               a              b              c              d              \n" +
                        "a              X              25.0           25.0           25.0           \n" +
                        "b                             X                                            \n" +
                        "c                                            X                             \n" +
                        "d                                                           X              ", graph.toString());
    }

    @Test
    public void debtSelectedToOneEqualPartsTest1() {
        DebtsGraph graph = new DebtsGraph();

        graph.addVertex(new Vertex("a"));
        graph.addVertex(new Vertex("b"));
        graph.addVertex(new Vertex("c"));
        graph.addVertex(new Vertex("d"));

        DebtsApi.debtAllToOneEqualParts(graph, new Vertex("a"), 100);

        Set<Vertex> selected = new HashSet<Vertex>() {{
            add(new Vertex("b"));
            add(new Vertex("c"));
        }};

        DebtsApi.debtSelectedToOneEqualParts(graph, new Vertex("a"), selected, 20);

        Assert.assertEquals(
                "               a              b              c              d              \n" +
                        "a              X              35.0           35.0           25.0           \n" +
                        "b                             X                                            \n" +
                        "c                                            X                             \n" +
                        "d                                                           X              ",
                graph.toString());

    }

    @Test
    public void debtSelectedToOneEqualPartsTest2() {
        DebtsGraph graph = new DebtsGraph();

        graph.addVertex(new Vertex("a"));
        graph.addVertex(new Vertex("b"));
        graph.addVertex(new Vertex("c"));
        graph.addVertex(new Vertex("d"));

        DebtsApi.debtAllToOneEqualParts(graph, new Vertex("a"), 100);

        Set<Vertex> selected = new HashSet<Vertex>() {{
            add(new Vertex("a"));
            add(new Vertex("b"));
            add(new Vertex("c"));
        }};

        DebtsApi.debtSelectedToOneEqualParts(graph, new Vertex("a"), selected, 30);

        Assert.assertEquals(
                "               a              b              c              d              \n" +
                        "a              X              35.0           35.0           25.0           \n" +
                        "b                             X                                            \n" +
                        "c                                            X                             \n" +
                        "d                                                           X              ",
                graph.toString());

    }

}
