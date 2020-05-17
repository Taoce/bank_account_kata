package org.bank.account.exception;

public class NegativeAmountException extends Throwable {
    public NegativeAmountException(String message){
        super(message);
    }
}
