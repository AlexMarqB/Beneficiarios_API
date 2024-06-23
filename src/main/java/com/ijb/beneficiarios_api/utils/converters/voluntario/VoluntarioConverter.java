package com.ijb.beneficiarios_api.utils.converters.voluntario;


import com.ijb.beneficiarios_api.dtos.creationDTOS.voluntario.CreateVoluntarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class VoluntarioConverter {

    public VoluntarioEntity converterCreateVoluntarioDTO(CreateVoluntarioDTO dto) {
        VoluntarioEntity entity = new VoluntarioEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public VoluntarioDTO converterVoluntarioEntity(VoluntarioEntity entity) {
        VoluntarioDTO dto = new VoluntarioDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public List<VoluntarioDTO> converterListVoluntarioEntity(List<VoluntarioEntity> entities) {
        return entities.stream()
                .map(this::converterVoluntarioEntity)
                .collect(Collectors.toList());
    }

    public List<VoluntarioDTO> converterListaVoluntarioEntity(List<VoluntarioEntity> entities) {
        return entities.stream()
                .map(this::converterVoluntarioEntity)
                .collect(Collectors.toList());
    }
}
