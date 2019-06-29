package com.transactrules.accounts.metadata.domain;


import com.transactrules.accounts.utilities.Utility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@Accessors(fluent = true)
public abstract class NamedAbstractEntity {

    @NotEmpty
    protected String propertyName;

    @NotEmpty
    protected String labelName;

    public NamedAbstractEntity(String propertyName){
        this.propertyName = propertyName;
        this.labelName = Utility.splitCamelCase(this.propertyName);
    }

    public NamedAbstractEntity(String propertyName, String labelName) {
        this.propertyName = propertyName;
        this.labelName = labelName;
    }

}
