package com.ijb.beneficiarios_api.util.fromDTO;

import com.ijb.beneficiarios_api.dtos.beneficiario.MembroFamiliarDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversorMembroFamiliarFromDTO {

     MembroFamiliarEntity converterMembroFamiliar(MembroFamiliarDTO membroFamiliarDTO) {
        MembroFamiliarEntity novoMembro = new MembroFamiliarEntity();
        novoMembro.setNome(membroFamiliarDTO.nome());
        novoMembro.setSobrenome(membroFamiliarDTO.sobrenome());
        novoMembro.setCpf(membroFamiliarDTO.cpf());
        novoMembro.setDtNascimento(membroFamiliarDTO.dtNascimento());
        novoMembro.setOrigemRenda(membroFamiliarDTO.origemRenda());
        novoMembro.setProblemasSaude(membroFamiliarDTO.problemasSaude());
        return novoMembro;
    }

    List<MembroFamiliarEntity> converterListaMembros(List<MembroFamiliarDTO> membroFamiliarDTOS) {
        return membroFamiliarDTOS.stream()
                .map(this::converterMembroFamiliar)
                .collect(Collectors.toList());
    }
}
