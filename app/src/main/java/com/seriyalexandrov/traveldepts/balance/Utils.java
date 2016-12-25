package com.seriyalexandrov.traveldepts.balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    private static final double MIN_WEIGHT_DIFFERENCE = 0.1;

    public static void simplifyGraph(DebtsGraph graph) {
        Map<Vertex, HashMap<Vertex, Edge>> vertexMap = graph.vertexMap;
        for (Vertex rowKey : vertexMap.keySet()) {
            for (Vertex columnKey : vertexMap.keySet()) {

                Edge rowColEdge = vertexMap.get(rowKey).get(columnKey);
                Edge colRowEdge = vertexMap.get(columnKey).get(rowKey);

                if (rowColEdge != null && colRowEdge != null) {
                    double rowColWeight = rowColEdge.weight();
                    double colRowWeight = colRowEdge.weight();
                    double resultWeight;

                    if (Math.abs(rowColWeight - colRowWeight) < MIN_WEIGHT_DIFFERENCE) {
                        vertexMap.get(columnKey).remove(rowKey);
                        vertexMap.get(rowKey).remove(columnKey);
                    } else if (rowColWeight > colRowWeight) {
                        resultWeight = rowColWeight - colRowWeight;
                        rowColEdge.setWeight(resultWeight);
                        vertexMap.get(columnKey).remove(rowKey);
                    } else {
                        resultWeight = colRowWeight - rowColWeight;
                        colRowEdge.setWeight(resultWeight);
                        vertexMap.get(rowKey).remove(columnKey);
                    }
                }
            }
        }
    }

    public static void calculateVertexBalance(DebtsGraph graph) {
        Map<Vertex, HashMap<Vertex, Edge>> vertexMap = graph.vertexMap;

        for (Vertex creditor : vertexMap.keySet()) {
            for (Vertex debtor : vertexMap.get(creditor).keySet()) {
                if (vertexMap.get(creditor).get(debtor) != null) {

                    double difference = vertexMap.get(creditor).get(debtor).weight();
                    double newCreditorBalance = creditor.getBalance() + difference;
                    double newDebtorBalance = debtor.getBalance() - difference;
                    creditor.setBalance(newCreditorBalance);
                    debtor.setBalance(newDebtorBalance);
                }
            }
        }
    }

    public static String printVertexes(DebtsGraph graph) {

        StringBuilder result = new StringBuilder();

        Map<Vertex, HashMap<Vertex, Edge>> vertexMap = graph.vertexMap;

        for (Vertex vertex : vertexMap.keySet()) {
            double balance = vertex.getBalance();
            result.append(String.format("%-15s", vertex.name) + ": " + balance + "\n");

            for (Vertex debtor : vertexMap.get(vertex).keySet()) {
                result.append("Edge: " + vertexMap.get(vertex).get(debtor).weight() + "\n");
            }
        }
        return result.toString();
    }

    public static List<DeptsResultEntry> calculateDebtsOnNormallizedGraph(DebtsGraph graph) {
        List<DeptsResultEntry> resultList = new ArrayList<DeptsResultEntry>();
        Map<Vertex, HashMap<Vertex, Edge>> vertexMap = graph.vertexMap;

        List<Vertex> profitList = new ArrayList<Vertex>();
        List<Vertex> expenceList = new ArrayList<Vertex>();

        for (Vertex person : vertexMap.keySet()) {
            double balance = person.getBalance();

            if (balance > 0) {
                profitList.add(person.clone());
            } else if (balance < 0) {
                expenceList.add(person.clone());
            }
        }

        int profitIndex = 0;
        int expenceIndex = 0;

        for (profitIndex = 0; profitIndex < profitList.size(); profitIndex++) {

            Vertex profVertex = profitList.get(profitIndex);

            while (profVertex.getBalance() > 0) {
                Vertex expVertex = expenceList.get(expenceIndex);

                if (profVertex.getBalance() > Math.abs(expVertex.getBalance())) {
                    profVertex.setBalance(profVertex.getBalance() - Math.abs(expVertex.getBalance()));
                    double debt = Math.abs(expVertex.getBalance());
                    expVertex.setBalance(0d);
                    expenceIndex++;
                    resultList.add(new DeptsResultEntry(expVertex, profVertex, debt));
                } else if (profVertex.getBalance() < Math.abs(expVertex.getBalance())) {
                    double debt = profVertex.getBalance();
                    profVertex.setBalance(0d);
                    expVertex.setBalance(expVertex.getBalance() + profVertex.getBalance());
                    profitIndex++;
                    resultList.add(new DeptsResultEntry(expVertex, profVertex, debt));
                } else {
                    double debt = profVertex.getBalance();
                    profVertex.setBalance(0d);
                    expVertex.setBalance(0d);
                    expenceIndex++;
                    expenceIndex++;
                    resultList.add(new DeptsResultEntry(expVertex, profVertex, debt));
                }
            }
        }

        return resultList;
    }
}