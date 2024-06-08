package com.ijb.beneficiarios_api.enums;

public enum FuncoesVoluntarioEnum {
    PSICOLOGO("PSICOLOGO"),
    ASSISTENTE_SOCIAL("ASSISTENTE SOCIAL"),
    ENGENHEIRO("ENGENHEIRO");

    private final String funcao;

    FuncoesVoluntarioEnum(String funcao) {
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }


}
