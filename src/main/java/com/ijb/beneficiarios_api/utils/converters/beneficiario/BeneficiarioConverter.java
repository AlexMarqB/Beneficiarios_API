package com.ijb.beneficiarios_api.utils.converters.beneficiario;

import com.ijb.beneficiarios_api.dtos.creationDTOS.beneficiario.CreateBeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.MembroFamiliarDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class BeneficiarioConverter {

    @Autowired
    MembroFamiliarConverter membroFamiliarConverter;

    public BeneficiarioEntity converterCreateBeneficiarioDTO(CreateBeneficiarioDTO dto) {
        BeneficiarioEntity entity = new BeneficiarioEntity();
        BeanUtils.copyProperties(dto, entity, "compFamiliar");
        if (dto.compFamiliar() != null) {
            List<MembroFamiliarEntity> compFamiliar = dto.compFamiliar().stream()
                    .map(membroFamiliarConverter::converterCreateMembroFamiliarDTO)
                    .collect(Collectors.toList());
            entity.setCompFamiliar(compFamiliar);
        }
        return entity;
    }

    public BeneficiarioDTO converterBeneficiarioEntity(BeneficiarioEntity entity) {
        BeneficiarioDTO dto = new BeneficiarioDTO();
        BeanUtils.copyProperties(entity, dto, "compFamiliar");
        if (entity.getCompFamiliar() != null) {
            List<MembroFamiliarDTO> compFamiliar = entity.getCompFamiliar().stream()
                    .map(membroFamiliarConverter::converterMembroFamiliarEntity)
                    .collect(Collectors.toList());
            dto.setCompFamiliar(compFamiliar);
        }
        return dto;
    }

    public List<BeneficiarioDTO> converterListBeneficiarioEntity(List<BeneficiarioEntity> entities) {
        return entities.stream()
                .map(this::converterBeneficiarioEntity)
                .collect(Collectors.toList());
    }
}
