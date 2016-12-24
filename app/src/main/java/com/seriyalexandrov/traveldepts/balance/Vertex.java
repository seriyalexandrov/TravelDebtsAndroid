package com.seriyalexandrov.traveldepts.balance;

import java.util.HashMap;
import java.util.Map;

public class Vertex {

    public final String name;
    private double balance;
    private Map<String, String> attrs;

    public Vertex(String name) {
        this.name = name;
        this.balance = 0;
        this.attrs = new HashMap<>(10);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAttr(String attrName) {
        return attrs.get(attrName);
    }

    public void setAttr(String attrName, String value) {
        attrs.put(attrName, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }

        Vertex vertex = (Vertex) o;

        if (!name.equals(vertex.name)) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    protected Vertex clone() {
        Vertex newVertex = new Vertex(name);
        newVertex.setBalance(balance);
        return newVertex;
    }
}