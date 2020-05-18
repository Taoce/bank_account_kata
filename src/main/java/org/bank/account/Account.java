package org.bank.account;

import org.bank.account.exception.NegativeAmountException;
import org.bank.account.exception.NotEnoughMoneyException;
import org.bank.account.operation.Operation;
import org.bank.account.operation.OperationType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private double balance;
    private List<Operation> history = new ArrayList<>();

    protected Account(double initialMoney) {
        balance = initialMoney;
    }

    public Account() {
        balance = 0;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amountToDeposit) throws NegativeAmountException {
        if (amountToDeposit < 0) {
            throw new NegativeAmountException("Operation Denied. Deposit of a negative amount of money : " + amountToDeposit);
        }
        this.balance += amountToDeposit;

        this.history.add(new Operation(OperationType.DEPOSIT, amountToDeposit, balance));

    }

    public void withdraw(double amountToWithdraw) throws NegativeAmountException, NotEnoughMoneyException {
        if (amountToWithdraw < 0) {
            throw new NegativeAmountException("Operation Denied. Withdraw of a negative amount of money : " + amountToWithdraw);
        }

        if (amountToWithdraw > balance) {
            throw new NotEnoughMoneyException("Operation Denied. Can't withdraw more money than available.");
        }

        this.balance -= amountToWithdraw;

        this.history.add(new Operation(OperationType.WITHDRAW, amountToWithdraw, balance));
    }

    public List<Operation> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
