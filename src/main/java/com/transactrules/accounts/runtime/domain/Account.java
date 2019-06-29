package com.transactrules.accounts.runtime.domain;

import com.transactrules.accounts.metadata.domain.AccountType;
import com.transactrules.accounts.metadata.domain.PositionType;
import com.transactrules.accounts.metadata.domain.TransactionRuleType;
import com.transactrules.accounts.metadata.domain.TransactionType;
import com.transactrules.accounts.runtime.commands.CreateAccountCommand;
import com.transactrules.accounts.runtime.commands.CreateTransactionCommand;
import com.transactrules.accounts.runtime.events.AccountCreatedEvent;
import com.transactrules.accounts.runtime.events.TransactionCreatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Accessors(fluent = true)
public class Account {
    @AggregateIdentifier
    private String id;

    private AccountType accountType;

    private Map<String,Position> positions = new HashMap<>();

    @CommandHandler
    public Account(CreateAccountCommand cmd){
        AggregateLifecycle.apply(new AccountCreatedEvent(cmd.id(), cmd.accountType()));
    }

    @CommandHandler
    public void handle(CreateTransactionCommand cmd){
        AggregateLifecycle.apply(new TransactionCreatedEvent(cmd.id(), cmd.transactionType(), cmd.amount()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent evt){
        id = evt.id();
        accountType = evt.accountType();

        for(PositionType positionType: evt.accountType().positionTypes()){
            positions.put(positionType.propertyName(), new Position());
        }
    }

    @EventSourcingHandler
    public void on(TransactionCreatedEvent evt){
        Optional<TransactionType> transactionType = accountType.getTransactionType(evt.transactionType());

        if(transactionType.isPresent()){
            for(TransactionRuleType rule:transactionType.get().getTransactionRules()){
                if(positions.containsKey(rule.posititonTypeName())){
                    positions.get(rule.posititonTypeName()).applyOperation(rule.transactionOperation(), evt.amount());
                }
            }
        }

    }
}


