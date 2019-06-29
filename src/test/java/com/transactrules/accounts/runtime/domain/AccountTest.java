package com.transactrules.accounts.runtime.domain;

import com.transactrules.accounts.TestUtility;
import com.transactrules.accounts.metadata.domain.AccountType;
import com.transactrules.accounts.runtime.commands.CreateAccountCommand;
import com.transactrules.accounts.runtime.commands.CreateTransactionCommand;
import com.transactrules.accounts.runtime.events.AccountCreatedEvent;
import com.transactrules.accounts.runtime.events.TransactionCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountTest {

    private FixtureConfiguration<Account> fixture;
    private AccountType depositAccountType;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Account.class);
        depositAccountType = TestUtility.depositAccountType();
    }

    @Test
    public void createAccountSuccess() {
        String id = UUID.randomUUID().toString();

        fixture
                .givenNoPriorActivity()
                .when(new CreateAccountCommand(id, depositAccountType))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AccountCreatedEvent(id, depositAccountType))
                .expectState(state -> assertThat(state.positions().size(), is(2)));
    }

    @Test
    public void depositWithdrawalTest() {
        String id = UUID.randomUUID().toString();

        fixture
                .given(new AccountCreatedEvent(id, depositAccountType),
                        new TransactionCreatedEvent(id,"deposit", BigDecimal.valueOf(1000)))
                .when(new CreateTransactionCommand(id, "withdrawal", BigDecimal.valueOf(100)))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new TransactionCreatedEvent(id, "withdrawal", BigDecimal.valueOf(100)))
                .expectState(state -> assertThat(state.positions().get("current").amount(), is(BigDecimal.valueOf(900))));
    }
}