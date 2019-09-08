package com.grupoacert.conversor.Enum;

public enum EscalaEnum {

    CELSIUS("celsius"),
    FAHRENHEIT("fahrenheit");

    private String descricao;

    EscalaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
