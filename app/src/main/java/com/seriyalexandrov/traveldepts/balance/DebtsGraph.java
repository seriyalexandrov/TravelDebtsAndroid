package com.seriyalexandrov.traveldepts.balance;

import java.util.HashMap;

public class DebtsGraph {

    public HashMap<Vertex, HashMap<Vertex, Edge>> vertexMap = new HashMap<Vertex, HashMap<Vertex, Edge>>();
    public HashMap<String, Vertex> vertexNames = new HashMap<>();

    public int size() {
        return vertexMap.size();
    }

    public void addVertex(Vertex vertex) {
        if (!hasVertex(vertex)) {
            vertexMap.put(vertex, new HashMap<Vertex, Edge>());
            vertexNames.put(vertex.name, vertex);
        }
    }

    public boolean hasVertex(Vertex vertex) {
        return vertexMap.containsKey(vertex);
    }

    public void addDebt(Vertex creditor, Vertex debtor, double debt) {
        if (creditor.equals(debtor)) {
            throw new IllegalArgumentException("Vertex " + creditor.name + " cannot have ende woth itself.");
        }
        if (!hasVertex(creditor)) {
            addVertex(creditor);
            vertexNames.put(creditor.name, creditor);
        }
        if (!hasVertex(debtor)) {
            addVertex(debtor);
            vertexNames.put(debtor.name, debtor);
        }
        HashMap<Vertex, Edge> edges1 = vertexMap.get(creditor);
        if (edges1.containsKey(debtor)) {
            double curWeight = edges1.get(debtor).weight();
            edges1.get(debtor).setWeight(curWeight + debt);
        } else {
            edges1.put(debtor, new Edge(debtor, debt));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("               ");
        for (Vertex key : vertexMap.keySet()) {
            result.append(String.format("%-15s", key.name));
        }

        for (Vertex rowKey : vertexMap.keySet()) {
            result.append("\n" + String.format("%-15s", rowKey.name));

            for (Vertex columnKey : vertexMap.keySet()) {
                if (vertexMap.get(rowKey).get(columnKey) != null) {
                    result.append(String.format("%-15s", vertexMap.get(rowKey).get(columnKey).weight()));
                } else {
                    if (rowKey.equals(columnKey)) {
                        result.append(String.format("%-15s", "X"));
                    } else {
                        result.append(String.format("%-15s", ""));
                    }
                }
            }
        }

        return result.toString();
    }
}