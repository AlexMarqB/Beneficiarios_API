package com.ijb.beneficiarios_api.util.fromEntity.beneficiario;

import com.ijb.beneficiarios_api.dtos.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.beneficiario.MembroFamiliarDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversorBeneficiarioFromEntity {

    @Autowired
    ConversorMembroFamiliarFromEntity conversorMembroFamiliarFromEntity;

    public BeneficiarioDTO converterBeneficiario(BeneficiarioEntity beneficiarioEntity) {
        List<MembroFamiliarDTO> membroFamiliarDTOs = conversorMembroFamiliarFromEntity.converterListaMembros(beneficiarioEntity.getCompFamiliar());
        return new BeneficiarioDTO(
                beneficiarioEntity.getCodBeneficiario(),
                membroFamiliarDTOs,
                beneficiarioEntity.getEndereco(),
                beneficiarioEntity.getTelefone(),
                beneficiarioEntity.getEmail()
        );
    }

    public List<BeneficiarioDTO> converterListaBeneficiarios(List<BeneficiarioEntity> beneficiarioEntities) {
        return beneficiarioEntities.stream()
                .map(this::converterBeneficiario)
                .collect(Collectors.toList());
    }
}
