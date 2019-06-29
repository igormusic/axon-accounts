package com.transactrules.accounts.runtime.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class TransactionCreatedEvent {
    private String id;
    private String transactionType;
    private BigDecimal amount;
}
