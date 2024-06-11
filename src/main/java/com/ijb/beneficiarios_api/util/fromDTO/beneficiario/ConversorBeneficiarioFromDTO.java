package com.ijb.beneficiarios_api.util.fromDTO.beneficiario;

import com.ijb.beneficiarios_api.dtos.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.beneficiario.PreCadastroBeneficiarioDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversorBeneficiarioFromDTO {

    @Autowired
    ConversorMembroFamiliarFromDTO conversorMembroFamiliarFromDTO;

    public BeneficiarioEntity converterPreCadastro (PreCadastroBeneficiarioDTO preCadastroBeneficiarioDTO) {
        BeneficiarioEntity novoBeneficiario = new BeneficiarioEntity();
        novoBeneficiario.setCpf(preCadastroBeneficiarioDTO.familiar().cpf());
        novoBeneficiario.setEmail(preCadastroBeneficiarioDTO.email());
        novoBeneficiario.setTelefone(preCadastroBeneficiarioDTO.telefone());
        novoBeneficiario.setEndereco(preCadastroBeneficiarioDTO.endereco());
        MembroFamiliarEntity membroFamiliarEntity = conversorMembroFamiliarFromDTO.converterMembroFamiliar(preCadastroBeneficiarioDTO.familiar());
        //service.cadastrarMembroFamiliar
        novoBeneficiario.getCompFamiliar().add(membroFamiliarEntity);
        return novoBeneficiario;
    }

    public BeneficiarioEntity converterBeneficiario (BeneficiarioDTO beneficiarioDTO) {
        BeneficiarioEntity novoBeneficiario = new BeneficiarioEntity();
        List<MembroFamiliarEntity> membroFamiliarEntities = conversorMembroFamiliarFromDTO.converterListaMembros(beneficiarioDTO.compFamiliar());
        //service.cadastrar
        novoBeneficiario.getCompFamiliar().addAll(membroFamiliarEntities);
        novoBeneficiario.setCpf(novoBeneficiario.getCompFamiliar().get(0).getCpf());
        novoBeneficiario.setEndereco(beneficiarioDTO.endereco());
        novoBeneficiario.setTelefone(beneficiarioDTO.telefone());
        novoBeneficiario.setEmail(beneficiarioDTO.email());
        return novoBeneficiario;
    }

    public List<BeneficiarioEntity> converterListaBeneficiarios(List<BeneficiarioDTO> beneficiarioDTOs) {
        return beneficiarioDTOs.stream()
                .map(this::converterBeneficiario)
                .collect(Collectors.toList());
    }
}
