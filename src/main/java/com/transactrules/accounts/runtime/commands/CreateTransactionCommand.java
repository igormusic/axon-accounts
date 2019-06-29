package com.transactrules.accounts.runtime.commands;

import com.transactrules.accounts.CommandBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class CreateTransactionCommand extends CommandBase {
    @TargetAggregateIdentifier
    private String id;
    private String transactionType;
    private BigDecimal amount;
}
