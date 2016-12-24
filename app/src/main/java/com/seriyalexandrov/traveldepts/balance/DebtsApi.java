package com.seriyalexandrov.traveldepts.balance;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DebtsApi {

    public static void debtAllToOneExceptCreditorEqualParts(DebtsGraph graph, Vertex creditor, double wholeDebt) {
        int size = graph.size();
        if (size <= 1) {
            throw new IllegalArgumentException("Graph must have more then one participant.");
        }
        double debt = wholeDebt / (size - 1);

        for (Vertex person : graph.vertexMap.keySet()) {
            if (person.equals(creditor)) {
                continue;
            }

            graph.addDebt(creditor, person, debt);
        }
    }

    public static void debtAllToOneEqualParts(DebtsGraph graph, Vertex creditor, double wholeDebt) {
        int size = graph.size();
        if (size <= 1) {
            throw new IllegalArgumentException("Graph must have more then one participant.");
        }
        double debt = wholeDebt / size;

        for (Vertex person : graph.vertexMap.keySet()) {
            if (person.equals(creditor)) {
                continue;
            }

            graph.addDebt(creditor, person, debt);
        }
    }

    public static void debtSelectedToOneEqualParts(DebtsGraph graph, Vertex creditor, Set<Vertex> selected, double wholeDebt) {
        int size = selected.size();
        if (size <= 1) {
            throw new IllegalArgumentException("Graph must have more then one participant.");
        }
        double debt = wholeDebt / size;
        Iterator<Vertex> selectedIter = selected.iterator();

        if (selected.contains(creditor)) {

            while (selectedIter.hasNext()) {
                Vertex next = selectedIter.next();
                if (next.equals(creditor)) {
                    continue;
                }
                graph.addDebt(creditor, next, debt);
            }
        } else {

            while (selectedIter.hasNext()) {
                graph.addDebt(creditor, selectedIter.next(), debt);
            }
        }
    }

    public static void debtSelectedToOneDifferentParte(DebtsGraph graph, Vertex creditor, Map<Vertex, Double> debtors) {

    }
}