package com.ijb.beneficiarios_api.utils.converters.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateAssembleiaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.AssembleiaDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.triagem.AssembleiaEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import com.ijb.beneficiarios_api.utils.converters.beneficiario.BeneficiarioConverter;
import com.ijb.beneficiarios_api.utils.converters.voluntario.VoluntarioConverter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class AssembleiaConverter {

    @Autowired
    BeneficiarioConverter beneficiarioConverter;

    @Autowired
    VoluntarioConverter voluntarioConverter;

    public AssembleiaEntity converterCreateAssembleiaDTO(CreateAssembleiaDTO dto, List<VoluntarioEntity> voluntarios, List<BeneficiarioEntity> beneficiarios) {
        AssembleiaEntity entity = new AssembleiaEntity();
        BeanUtils.copyProperties(dto, entity, "beneficiarios", "voluntarios");
        entity.setVoluntarios(voluntarios);
        entity.setBeneficiarios(beneficiarios);
        return entity;
    }

    public AssembleiaDTO converterAssembleiaEntity(AssembleiaEntity entity) {
        AssembleiaDTO dto = new AssembleiaDTO();
        BeanUtils.copyProperties(entity, dto, "beneficiarios", "voluntarios");
        dto.setBeneficiarios(beneficiarioConverter.converterListBeneficiarioEntity(entity.getBeneficiarios()));
        dto.setVoluntarios(voluntarioConverter.converterListaVoluntarioEntity(entity.getVoluntarios()));
        return dto;
    }

    public List<AssembleiaDTO> converterListAssembleiaEntity(List<AssembleiaEntity> entities) {
        return entities.stream()
                .map(this::converterAssembleiaEntity)
                .collect(Collectors.toList());
    }
}
