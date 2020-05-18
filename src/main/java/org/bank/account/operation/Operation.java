package org.bank.account.operation;

import java.util.Date;
import java.util.Objects;

public class Operation {

    private final OperationType type;
    private final Date date;
    private final double amount;
    private final double balance;

    public Operation(OperationType type, Date date, double amount, double balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public Operation(OperationType type, double amount, double balance) {
        this.type = type;
        this.date = new Date();
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Double.compare(operation.amount, amount) == 0 &&
                Double.compare(operation.balance, balance) == 0 &&
                type == operation.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount, balance);
    }
}
