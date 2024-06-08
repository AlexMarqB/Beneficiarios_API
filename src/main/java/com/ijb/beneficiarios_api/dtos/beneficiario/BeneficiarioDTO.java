package com.ijb.beneficiarios_api.dtos.beneficiario;

import java.util.List;

public record BeneficiarioDTO(List<MembroFamiliarDTO> compFamiliar, String endereco, String telefone, String email) {
}
