package com.ijb.beneficiarios_api.dtos.dataDTOS.triagem;

import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;

import java.util.Date;
import java.util.List;

public record AssembleiaDTO(Integer codAssembleia,
                            List<VoluntarioDTO> voluntarios,
                            List<BeneficiarioDTO> beneficiarios,
                            Date dtAssembleia,
                            String decisao) {
}
