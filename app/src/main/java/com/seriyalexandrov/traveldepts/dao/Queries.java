package com.seriyalexandrov.traveldepts.dao;

import static com.seriyalexandrov.traveldepts.Constants.DEPTS_TABLE;

public final class Queries {

    public static final String CREATE_DEPTS_TABLE_QUERY =
            "create table " + DEPTS_TABLE + " ("
            + "id text,"
            + "deptor text,"
            + "creditor text,"
            + "summ real,"
            + "currency text,"
            + "comment text" + ");";

    public static final String SELECT_ALL_DEPTS_QUERY =
            "select " +
            "id, " +
            "deptor, " +
            "creditor, " +
            "summ, " +
            "currency, " +
            "comment " +
            "from " + DEPTS_TABLE;

    public static final String DELETE_DEPT_BY_ID_CLAUSE =
            "id = ?";

    private Queries() {}
}
