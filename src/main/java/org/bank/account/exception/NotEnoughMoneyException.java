package org.bank.account.exception;

public class NotEnoughMoneyException extends Throwable {
    public NotEnoughMoneyException(String message){
        super(message);
    }
}
