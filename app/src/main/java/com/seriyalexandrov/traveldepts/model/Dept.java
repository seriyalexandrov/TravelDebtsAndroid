package com.seriyalexandrov.traveldepts.model;

public class Dept {

    public Dept(String deptorName, String creditorName, double summ, String currency, String comment) {
        this.deptorName = deptorName;
        this.creditorName = creditorName;
        this.summ = summ;
        this.currency = currency;
        this.comment = comment;
    }

    public String deptorName;
    public String creditorName;
    public double summ;
    public String currency;
    public String comment;
}
