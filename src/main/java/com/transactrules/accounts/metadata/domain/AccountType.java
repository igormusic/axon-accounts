package com.transactrules.accounts.metadata.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Aggregate root for account metadata
 */

@Getter
@Accessors(fluent = true)
public class AccountType {

    @NotEmpty
    private String className;

    @NotEmpty
    private String labelName;

    @Valid
    private List<PositionType> positionTypes = new ArrayList<>();

    @Valid
    private List<TransactionType> transactionTypes = new ArrayList<>();

    public AccountType(String className, String labelName) {
        this.className = className;
        this.labelName = labelName;
    }

    public PositionType addPositionType(String name){
        PositionType positionType = new PositionType( name);
        positionTypes.add(positionType);
        return positionType;
    }

    public PositionType addPositionType(String name, String labelName, Boolean isPrincipal){
        PositionType positionType = new PositionType( name, labelName, isPrincipal);
        positionTypes.add(positionType);
        return positionType;
    }

    public TransactionType addTransactionType(String name, boolean hasMaximumPrecission){
        TransactionType transactionType = new TransactionType(name, hasMaximumPrecission);
        transactionTypes.add(transactionType);

        return transactionType;
    }

    public TransactionType addTransactionType(String name){
        TransactionType transactionType = new TransactionType(name, false);
        transactionTypes.add(transactionType);

        return transactionType;
    }

    public Optional<TransactionType> getTransactionType(String transactionTypeName){

        for(TransactionType transactionType : transactionTypes) {
            if(transactionType.propertyName().equalsIgnoreCase(transactionTypeName)){
                return Optional.of(transactionType);
            }
        }

        return Optional.empty();
    }
}
