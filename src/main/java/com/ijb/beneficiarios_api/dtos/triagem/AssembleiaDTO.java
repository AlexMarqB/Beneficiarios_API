package com.ijb.beneficiarios_api.dtos.triagem;

import com.ijb.beneficiarios_api.dtos.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.voluntario.VoluntarioDTO;

import java.util.Date;
import java.util.List;

public record AssembleiaDTO(List<VoluntarioDTO> voluntarios, List<BeneficiarioDTO> beneficiarios, Date dataAssembleia) {
}
