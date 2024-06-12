package com.ijb.beneficiarios_api.enums;


public enum EstadoBeneficiarioTriagemEnum {
    PENDENTE("PENDENTE"),
    AGUARDANDO_VISITA("AGUARDANDO VISITA"),
    EM_ANDAMENTO("EM ANDAMENTO"),
    EM_AVALIACAO("EM AVALIAÇÃO"),
    OBRA("EM OBRA"),
    ALUGUEL("ALUGUEL"),
    FINALIZADO("FINALIZADO");

    private final String estado;

    EstadoBeneficiarioTriagemEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
