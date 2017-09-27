package ru.job4j.bank;

public class Account {
    private  double value;
    private long requisites;

    public Account(double value, long requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setRequisites(long requisites) {
        this.requisites = requisites;
    }

    public long getRequisites() {
        return requisites;
    }

    public boolean eqauls(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        Account otherAccount = (Account) obj;
        if (requisites == otherAccount.requisites) {
            return true;
        } else {
            return false;
        }

    }
}
