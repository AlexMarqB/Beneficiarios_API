package com.ijb.beneficiarios_api.dtos.creationDTOS.triagem;

import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;

import java.util.Date;
import java.util.List;

public record CreateAssembleiaDTO(Date dtAssembleia,
                                  List<VoluntarioDTO> voluntarios,
                                  List<BeneficiarioDTO> beneficiarios) {
}
