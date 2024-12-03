package com.api.msaccount.strategy;

public class AhorroWithdrawalStrategy implements WithdrawalStrategy {
    @Override
    public boolean canWithdraw(double balance, double amount) {
        return (balance - amount) >= 0;
    }

    @Override
    public String getErrorMessage() {
        return "El saldo no puede ser menor a 0 - Cuenta AHORRO";
    }
}
