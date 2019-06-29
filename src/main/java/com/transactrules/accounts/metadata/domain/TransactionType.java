package com.transactrules.accounts.metadata.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TransactionType extends NamedAbstractEntity {

    private boolean maximumPrecision;

    private List<TransactionRuleType> transactionRules = new ArrayList<>();

    public TransactionType(String name,  boolean hasMaximumPrecission) {
        super(name);
        this.maximumPrecision = hasMaximumPrecission;
    }

    public TransactionType addRule(PositionType positionType, TransactionOperation operation) {
        transactionRules.add(new TransactionRuleType(positionType.propertyName, operation));

        return this;
    }
}
