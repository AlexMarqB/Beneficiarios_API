package com.ijb.beneficiarios_api.utils.converters.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateVisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.VisitaDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.triagem.VisitaEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import com.ijb.beneficiarios_api.utils.converters.beneficiario.BeneficiarioConverter;
import com.ijb.beneficiarios_api.utils.converters.voluntario.VoluntarioConverter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class VisitaConverter {


    public VisitaEntity converterCreateVisitaDTO(CreateVisitaDTO dto, BeneficiarioEntity beneficiario, List<VoluntarioEntity> voluntarios) {
        VisitaEntity entity = new VisitaEntity();
        BeanUtils.copyProperties(dto, entity, "voluntarios", "beneficiario");
        entity.setVoluntarios(voluntarios);
        entity.setBeneficiario(beneficiario);
        return entity;
    }

    public VisitaDTO converterVisitaEntity(VisitaEntity entity) {
        VisitaDTO dto = new VisitaDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public List<VisitaDTO> converterListVisitaEntity(List<VisitaEntity> entities) {
        return entities.stream()
                .map(this::converterVisitaEntity)
                .collect(Collectors.toList());
    }
}
