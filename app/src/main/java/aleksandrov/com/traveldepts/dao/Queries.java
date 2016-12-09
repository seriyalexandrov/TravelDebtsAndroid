package aleksandrov.com.traveldepts.dao;

public final class Queries {

    public static final String CREATE_DEPTS_TABLE_QUERY =
            "create table depts ("
            + "deptor text"
            + "creditor text,"
            + "summ real,"
            + "currency text,"
            + "comment text" + ");";

    public static final String SELECT_ALL_DEPTS_QUERY =
            "select " +
                "d.deptor, " +
                "d.creditor, " +
                "d.summ, " +
                "d.currency, " +
                "d.comment " +
            "from depts d";

    private Queries() {}
}
