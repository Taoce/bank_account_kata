package org.bank.account;

import org.bank.account.exception.NegativeAmountException;

public class Account {

    private double moneyAmount;

    public double getAmount() {
        return this.moneyAmount;
    }

    public void deposit(double amountToDeposit) throws NegativeAmountException {
        if (amountToDeposit < 0) {
            throw new NegativeAmountException("Operation Denied. Deposit of a negative amount of money : " + amountToDeposit);
        }
        this.moneyAmount += amountToDeposit;
    }
}
