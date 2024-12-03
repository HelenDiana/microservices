package com.api.msaccount.strategy;

import com.api.msaccount.enums.AccountType;

public class WithdrawalStrategyFactory {
    public static WithdrawalStrategy getStrategy(AccountType accountType) {
        if (accountType == AccountType.AHORRO) {
            return new AhorroWithdrawalStrategy();
        } else if (accountType == AccountType.CORRIENTE) {
            return new CorrienteWithdrawalStrategy();
        } else {
            throw new IllegalArgumentException("Tipo de cuenta no soportado");
        }
    }
}