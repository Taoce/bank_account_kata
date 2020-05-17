package org.bank;

import org.bank.account.Account;
import org.bank.account.exception.NegativeAmountException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    Account account;

    @Before
    public void setUp() {
        account = new Account();
    }

    @Test
    public void when_deposing_positive_money_amount_should_credit_account() throws NegativeAmountException {
        deposingMoney(account, 35.5);
    }

    @Test
    public void when_deposing_zero_money_amount_should_credit_account() throws NegativeAmountException {
        deposingMoney(account, 0);
    }

    @Test
    public void when_deposing_maximum_money_amount_should_credit_account() throws NegativeAmountException {
        deposingMoney(account, Double.MAX_VALUE);
    }

    @Test
    public void when_deposing_money_multiple_times_should_credit_account() throws NegativeAmountException {
        deposingMoney(account, 12);
        deposingMoney(account, 19.61);
        deposingMoney(account, 2);
        deposingMoney(account, 7);
        deposingMoney(account, 7153);
    }

    @Test(expected = NegativeAmountException.class)
    public void when_deposing_negative_money_amount_should_throw_error() throws NegativeAmountException {
        deposingMoney(account, -35.5);
    }

    private void deposingMoney(Account account, double amountToDeposit) throws NegativeAmountException {
        double originalBalance = account.getAmount();

        account.deposit(amountToDeposit);

        double finalBalance = account.getAmount();

        assertThat(finalBalance).isEqualTo(originalBalance + amountToDeposit);
    }


}
