package com.ijb.beneficiarios_api.utils.converters.beneficiario;

import com.ijb.beneficiarios_api.dtos.creationDTOS.beneficiario.CreateMembroFamiliarDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.MembroFamiliarDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class MembroFamiliarConverter {

    public MembroFamiliarEntity converterCreateMembroFamiliarDTO(CreateMembroFamiliarDTO dto) {
        MembroFamiliarEntity entity = new MembroFamiliarEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public MembroFamiliarDTO converterMembroFamiliarEntity(MembroFamiliarEntity entity) {
        MembroFamiliarDTO dto = new MembroFamiliarDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public List<MembroFamiliarDTO> converterListMembroFamiliarEntity(List<MembroFamiliarEntity> entities) {
        return entities.stream()
                .map(this::converterMembroFamiliarEntity)
                .collect(Collectors.toList());
    }
}
