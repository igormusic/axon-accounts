package com.transactrules.accounts.metadata.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public class TransactionRuleType  {

    private String posititonTypeName;

    private TransactionOperation transactionOperation;
}

