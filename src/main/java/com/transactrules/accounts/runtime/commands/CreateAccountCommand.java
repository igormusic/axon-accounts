package com.transactrules.accounts.runtime.commands;

import com.transactrules.accounts.CommandBase;
import com.transactrules.accounts.metadata.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class CreateAccountCommand extends CommandBase {
    @TargetAggregateIdentifier
    private String id;
    private AccountType accountType;
}
