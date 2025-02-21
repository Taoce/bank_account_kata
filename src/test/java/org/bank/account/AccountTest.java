package org.bank.account;

import org.bank.account.exception.NegativeAmountException;
import org.bank.account.exception.NotEnoughMoneyException;
import org.bank.account.operation.Operation;
import org.bank.account.operation.OperationType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    Account emptyAccount;
    Account provisionedAccount;

    @Before
    public void setUp() {
        emptyAccount = new Account();
        provisionedAccount = new Account(105.3);
    }

    @Test
    public void when_deposing_positive_money_amount_should_credit_account() throws NegativeAmountException {
        deposingMoney(emptyAccount, 35.5);
    }

    @Test
    public void when_deposing_zero_money_amount_should_credit_account() throws NegativeAmountException {
        deposingMoney(emptyAccount, 0);
    }

    @Test
    public void when_deposing_maximum_money_amount_should_credit_account() throws NegativeAmountException {
        deposingMoney(emptyAccount, Double.MAX_VALUE);
    }

    @Test
    public void when_deposing_money_multiple_times_should_credit_account() throws NegativeAmountException {
        deposingMoney(emptyAccount, 12);
        deposingMoney(emptyAccount, 19.61);
        deposingMoney(emptyAccount, 2);
        deposingMoney(emptyAccount, 7);
        deposingMoney(emptyAccount, 7153);
    }

    @Test(expected = NegativeAmountException.class)
    public void when_deposing_negative_money_amount_should_throw_error() throws NegativeAmountException {
        deposingMoney(emptyAccount, -35.5);
    }

    @Test
    public void when_withdrawing_money_multiple_times_should_debit_account() throws NegativeAmountException, NotEnoughMoneyException {
        withdrawMoney(provisionedAccount, 12);
        withdrawMoney(provisionedAccount, 19.61);
        withdrawMoney(provisionedAccount, 2);
        withdrawMoney(provisionedAccount, 7.81);
        withdrawMoney(provisionedAccount, 20);
    }

    @Test
    public void when_withdrawing_positive_money_amount_should_debit_account() throws NegativeAmountException, NotEnoughMoneyException {
        withdrawMoney(provisionedAccount, 35.5);
    }

    @Test
    public void when_withdrawing_zero_money_amount_should_debit_account() throws NegativeAmountException, NotEnoughMoneyException {
        withdrawMoney(provisionedAccount, 0);
    }

    @Test
    public void when_withdrawing_maximum_money_amount_should_debit_account() throws NegativeAmountException, NotEnoughMoneyException {
        withdrawMoney(provisionedAccount, 100);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void when_withdrawing_money_on_empty_account_should_throw_error() throws NegativeAmountException, NotEnoughMoneyException {
        withdrawMoney(emptyAccount, 110);
    }

    @Test(expected = NegativeAmountException.class)
    public void when_withdrawing_negative_money_amount_should_throw_error() throws NegativeAmountException, NotEnoughMoneyException {
        withdrawMoney(provisionedAccount, -35.5);
    }

    @Test
    public void when_withdrawing_money_should_add_to_history() throws NegativeAmountException, NotEnoughMoneyException {
        List<Operation> accountHistory = provisionedAccount.getHistory();
        Operation expectedAccountHistoryOperation = new Operation(OperationType.WITHDRAW, 10, provisionedAccount.getBalance() - 10);

        assertThat(accountHistory).hasSize(0);

        provisionedAccount.withdraw(10);

        assertThat(accountHistory).hasSize(1);
        assertThat(accountHistory.get(0)).isEqualTo(expectedAccountHistoryOperation);
    }

    @Test
    public void when_deposing_money_should_add_to_history() throws NegativeAmountException {
        List<Operation> accountHistory = provisionedAccount.getHistory();
        Operation expectedAccountHistoryOperation = new Operation(OperationType.DEPOSIT, 10, provisionedAccount.getBalance() + 10);

        assertThat(accountHistory).hasSize(0);

        provisionedAccount.deposit(10);

        assertThat(accountHistory).hasSize(1);
        assertThat(accountHistory.get(0)).isEqualTo(expectedAccountHistoryOperation);
    }

    @Test
    public void when_deposing_and_withdrawing_money_should_add_to_history() throws NegativeAmountException, NotEnoughMoneyException {
        List<Operation> accountHistory = provisionedAccount.getHistory();
        Operation expectedDepositAccountHistoryOperation = new Operation(OperationType.DEPOSIT, 10, provisionedAccount.getBalance() + 10);

        assertThat(accountHistory).hasSize(0);

        provisionedAccount.deposit(10);

        assertThat(accountHistory).hasSize(1);
        assertThat(accountHistory.get(0)).isEqualTo(expectedDepositAccountHistoryOperation);

        Operation expectedWithdrawAccountHistoryOperation = new Operation(OperationType.WITHDRAW, 10, provisionedAccount.getBalance() - 10);

        provisionedAccount.withdraw(10);

        assertThat(accountHistory).hasSize(2);
        assertThat(accountHistory.get(1)).isEqualTo(expectedWithdrawAccountHistoryOperation);
    }

    private void deposingMoney(Account account, double amountToDeposit) throws NegativeAmountException {
        double originalBalance = account.getBalance();

        account.deposit(amountToDeposit);

        double finalBalance = account.getBalance();

        assertThat(finalBalance).isEqualTo(originalBalance + amountToDeposit);
    }

    private void withdrawMoney(Account account, double amountToWithdraw) throws NegativeAmountException, NotEnoughMoneyException {
        double originalBalance = account.getBalance();

        account.withdraw(amountToWithdraw);

        double finalBalance = account.getBalance();

        assertThat(finalBalance).isEqualTo(originalBalance - amountToWithdraw);
    }


}
