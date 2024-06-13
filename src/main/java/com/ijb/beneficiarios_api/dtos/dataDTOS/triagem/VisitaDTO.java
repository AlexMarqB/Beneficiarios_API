package com.ijb.beneficiarios_api.dtos.dataDTOS.triagem;

import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;

import java.util.Date;
import java.util.List;

public record VisitaDTO (Integer codVisita,
                         Date dtVisita,
                         List<VoluntarioDTO> voluntarios,
                         boolean realizada){
}
