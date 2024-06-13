package com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario;

import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.AssembleiaDTO;
import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioTriagemEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BeneficiarioDTO {
    private Integer codBeneficiario;
    private String endereco;
    private String tipoResidencia;
    private String necessidadeFamiliar;
    private EstadoBeneficiarioTriagemEnum estado;
    private List<MembroFamiliarDTO> compFamiliar;
    private AssembleiaDTO assembleia;
    private LocalDateTime dtAtualizacao;
}
