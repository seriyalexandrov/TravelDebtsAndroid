package com.seriyalexandrov.traveldepts.model;

public class Dept {

    public Dept(
            String id, String deptorName, String creditorName,
            String summ, String currency, String comment) {
        this.id = id;
        this.deptorName = deptorName;
        this.creditorName = creditorName;
        this.summ = summ;
        this.currency = currency;
        this.comment = comment;
    }

    public String id;
    public String deptorName;
    public String creditorName;
    public String summ;
    public String currency;
    public String comment;
}
