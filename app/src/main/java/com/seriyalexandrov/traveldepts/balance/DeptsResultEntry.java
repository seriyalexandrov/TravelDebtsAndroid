package com.seriyalexandrov.traveldepts.balance;

public class DeptsResultEntry {

    public final Vertex debtor;
    public final Vertex creditor;
    public final Double debt;

    public DeptsResultEntry(Vertex debtor, Vertex creditor, Double debt) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.debt = debt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeptsResultEntry that = (DeptsResultEntry) o;

        if (!debtor.equals(that.debtor)) {
            return false;
        }
        if (!creditor.equals(that.creditor)) {
            return false;
        }
        return debt.equals(that.debt);

    }

    @Override
    public int hashCode() {
        int result = debtor.hashCode();
        result = 31 * result + creditor.hashCode();
        result = 31 * result + debt.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DebtsResultEntry{" +
                "debtor=" + debtor +
                ", creditor=" + creditor +
                ", debt=" + debt +
                '}';
    }
}