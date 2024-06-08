package com.ijb.beneficiarios_api.enums;


public enum EstadoBeneficiarioEnum {
    PENDENTE("PENDENTE"),
    AGUARDANDO_VISITA("AGUARDANDO VISITA"),
    EM_ANDAMENTO("EM ANDAMENTO"),
    EM_AVALIACAO("EM AVALIAÇÃO"),
    OBRA("EM OBRA"),
    ALUGUEL("ALUGUEL"),
    FINALIZADO("FINALIZADO");

    private final String estado;

    EstadoBeneficiarioEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
