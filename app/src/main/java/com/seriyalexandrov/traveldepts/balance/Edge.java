package com.seriyalexandrov.traveldepts.balance;

public class Edge {

    private double weight = 0;
    private Vertex endVertex;

    public Edge(Vertex endVertex, double weight) {
        this.weight = weight;
        this.endVertex = endVertex;
    }

    public double weight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex end() {
        return endVertex;
    }
}