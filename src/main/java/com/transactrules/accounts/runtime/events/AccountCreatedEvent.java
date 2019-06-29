package com.transactrules.accounts.runtime.events;

import com.transactrules.accounts.metadata.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class AccountCreatedEvent {
    private String id;
    private AccountType accountType;
}
