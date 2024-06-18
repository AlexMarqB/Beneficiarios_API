package com.ijb.beneficiarios_api.utils.converters.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateRelatorioVisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.RelatorioVisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.triagem.RelatorioVisitaEntity;
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
public class RelatorioVisitaConverter {

    @Autowired
    VoluntarioConverter voluntarioConverter;

    @Autowired
    BeneficiarioConverter beneficiarioConverter;

    public RelatorioVisitaEntity converterCreateRelatorioVisitaDTO(CreateRelatorioVisitaDTO dto, VoluntarioEntity voluntario, BeneficiarioEntity beneficiario) {
        RelatorioVisitaEntity entity = new RelatorioVisitaEntity();
        BeanUtils.copyProperties(dto, entity, "voluntario", "beneficiario");
        entity.setBeneficiario(beneficiario);
        entity.setVoluntario(voluntario);
        return entity;
    }

    public RelatorioVisitaDTO converterRelatorioVisitaEntity(RelatorioVisitaEntity entity) {
        RelatorioVisitaDTO dto = new RelatorioVisitaDTO();
        BeanUtils.copyProperties(entity, dto, "voluntario", "beneficiario");
        VoluntarioDTO voluntario = voluntarioConverter.converterVoluntarioEntity(entity.getVoluntario());
        BeneficiarioDTO beneficiario = beneficiarioConverter.converterBeneficiarioEntity(entity.getBeneficiario());
        dto.setVoluntario(voluntario);
        dto.setBeneficiario(beneficiario);
        return dto;
    }

    public List<RelatorioVisitaDTO> converterListRelatorioVisitaEntity(List<RelatorioVisitaEntity> entities) {
        return entities.stream()
                .map(this::converterRelatorioVisitaEntity)
                .collect(Collectors.toList());
    }
}    