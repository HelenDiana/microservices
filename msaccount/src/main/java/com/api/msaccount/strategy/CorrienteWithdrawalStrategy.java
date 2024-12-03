package com.api.msaccount.strategy;

public class CorrienteWithdrawalStrategy implements WithdrawalStrategy{
    @Override
    public boolean canWithdraw(double balance, double amount) {
        return (balance - amount) >= -500;
    }

    @Override
    public String getErrorMessage() {
        return "El saldo no puede ser menor a -500 - Cuenta CORRIENTE";
    }
}
