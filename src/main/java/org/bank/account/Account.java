package org.bank.account;

import org.bank.account.exception.NegativeAmountException;
import org.bank.account.exception.NotEnoughMoneyException;

public class Account {

    private double moneyAmount;

    protected Account(double initialMoney) {
        moneyAmount = initialMoney;
    }

    public Account() {
        moneyAmount = 0;
    }

    public double getAmount() {
        return this.moneyAmount;
    }

    public void deposit(double amountToDeposit) throws NegativeAmountException {
        if (amountToDeposit < 0) {
            throw new NegativeAmountException("Operation Denied. Deposit of a negative amount of money : " + amountToDeposit);
        }
        this.moneyAmount += amountToDeposit;
    }

    public void withdraw(double amountToWithdraw) throws NegativeAmountException, NotEnoughMoneyException {
        if (amountToWithdraw < 0) {
            throw new NegativeAmountException("Operation Denied. Withdraw of a negative amount of money : " + amountToWithdraw);
        }

        if (amountToWithdraw > moneyAmount) {
            throw new NotEnoughMoneyException("Operation Denied. Can't withdraw more money than available.");
        }

        this.moneyAmount -= amountToWithdraw;
    }
}
