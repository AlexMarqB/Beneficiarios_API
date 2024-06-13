package com.ijb.beneficiarios_api.dtos.creationDTOS.beneficiario;

import java.util.List;

public record CreateBeneficiarioDTO(String endereco,
                                    String tipoResidencia,
                                    String necessidadeFamiliar,
                                    List<CreateMembroFamiliarDTO> compFamiliar) {
}
