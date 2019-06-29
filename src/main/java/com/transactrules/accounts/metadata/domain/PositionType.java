package com.transactrules.accounts.metadata.domain;

import lombok.Getter;

@Getter
public class PositionType  extends NamedAbstractEntity {

    private Boolean isPrincipal=false;

    public PositionType(String name) {
        super(name);
    }

    public PositionType(String name, String label) {
        super(name,label);
    }

    public PositionType(String name, String label, Boolean isPrincipal) {
        this(name,label);
        this.isPrincipal = isPrincipal;
    }

}
