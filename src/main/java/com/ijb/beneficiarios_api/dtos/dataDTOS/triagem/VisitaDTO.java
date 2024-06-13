package com.ijb.beneficiarios_api.dtos.dataDTOS.triagem;

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
public class VisitaDTO {
    private Integer codVisita;
    private Date dtVisita;
    private List<VoluntarioDTO> voluntarios;
    private boolean realizada;
}
