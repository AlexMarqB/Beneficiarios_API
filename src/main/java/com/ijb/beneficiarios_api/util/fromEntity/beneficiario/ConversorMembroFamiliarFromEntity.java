package com.ijb.beneficiarios_api.util.fromEntity.beneficiario;

import com.ijb.beneficiarios_api.dtos.beneficiario.MembroFamiliarDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversorMembroFamiliarFromEntity {

    public MembroFamiliarDTO converterMembroFamiliar(MembroFamiliarEntity membroFamiliarEntity) {
        return new MembroFamiliarDTO(
                membroFamiliarEntity.getCodMembroFamiliar(),
                membroFamiliarEntity.getNome(),
                membroFamiliarEntity.getSobrenome(),
                membroFamiliarEntity.getCpf(),
                membroFamiliarEntity.getDtNascimento(),
                membroFamiliarEntity.getValorRenda(),
                membroFamiliarEntity.getOrigemRenda(),
                membroFamiliarEntity.getProblemasSaude()
        );
    }

    public List<MembroFamiliarDTO> converterListaMembros(List<MembroFamiliarEntity> membroFamiliarEntities) {
        return membroFamiliarEntities.stream()
                .map(this::converterMembroFamiliar)
                .collect(Collectors.toList());
    }
}
