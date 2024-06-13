package com.ijb.beneficiarios_api.dtos.dataDTOS.triagem;

import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssembleiaDTO {
    private Integer codAssembleia;
    private List<VoluntarioDTO> voluntarios;
    private List<BeneficiarioDTO> beneficiarios;
    private Date dtAssembleia;
    private String decisao;
}
