package com.ijb.beneficiarios_api.dtos.dataDTOS.triagem;

import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RelatorioVisitaDTO {
    private Integer codRelatorio;
    private VisitaDTO visita;
    private BeneficiarioDTO beneficiario;
    private VoluntarioDTO voluntario;
    private Long descricao;
    private String imagens;
}
