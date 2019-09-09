package com.grupoacert.conversor.Enum;

public enum EscalaEnum {



    CELSIUS("celsius"),
    FAHRENHEIT("fahrenheit");

    private String descricao;

    public static final String MENSAGEM_ESCALA_DESCONHECIDA = "O valor converterPara é inválido, valores válidos: CELSIUS, FAHRENHEIT";
    public static final Integer TRINTA_E_DOIS = 32;
    public static final Integer NOVE = 9;
    public static final Integer CINCO = 5;


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
