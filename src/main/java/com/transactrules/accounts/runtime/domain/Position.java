package com.transactrules.accounts.runtime.domain;

import com.transactrules.accounts.metadata.domain.TransactionOperation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode
public class Position  {

    private BigDecimal amount;

    public Position() {
        this.amount = BigDecimal.ZERO;
    }

    public Position(Position prototype){
        this.amount = prototype.amount;
    }

    public void applyOperation(TransactionOperation operation, BigDecimal value){
        switch (operation) {
            case Subtract:
                amount = amount.subtract(value);
                break;
            case Add:
                amount = amount.add(value);
                break;
            default:
                break;
        }
    }
}
