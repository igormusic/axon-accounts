package com.transactrules.accounts;

import com.transactrules.accounts.metadata.domain.AccountType;
import com.transactrules.accounts.metadata.domain.PositionType;
import com.transactrules.accounts.metadata.domain.TransactionOperation;

public class TestUtility {
    public static AccountType depositAccountType(){
        AccountType depositAccount = new AccountType("DepositAccount", "Deposit Account");

        PositionType current = depositAccount.addPositionType("current", "Current", true);
        PositionType interestAccrued = depositAccount.addPositionType("interestAccrued");

         depositAccount.addTransactionType("deposit")
            .addRule(current, TransactionOperation.Add);

        depositAccount.addTransactionType("withdrawal")
                .addRule(current, TransactionOperation.Subtract);

        depositAccount.addTransactionType("interestAccrual", true)
                .addRule(interestAccrued, TransactionOperation.Add);

        depositAccount.addTransactionType("interestCapitalization")
            .addRule(interestAccrued, TransactionOperation.Subtract)
            .addRule(current, TransactionOperation.Add);

        return depositAccount;
    }
}
