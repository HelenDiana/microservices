package com.api.msaccount.strategy;

public interface WithdrawalStrategy {
    boolean canWithdraw(double balance, double amount);
    String getErrorMessage();
}