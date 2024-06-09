package com.ijb.beneficiarios_api.util.fromDTO;

import com.ijb.beneficiarios_api.dtos.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.beneficiario.PreCadastroBeneficiarioDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversorBeneficiarioFromDTO {

    @Autowired
    ConversorMembroFamiliarFromDTO conversorMembroFamiliarFromDTO;

    BeneficiarioEntity converterPreCadastro (PreCadastroBeneficiarioDTO preCadastroBeneficiarioDTO) {
        BeneficiarioEntity novoBeneficiario = new BeneficiarioEntity();
        novoBeneficiario.setCpf(preCadastroBeneficiarioDTO.familiar().cpf());
        novoBeneficiario.setEmail(preCadastroBeneficiarioDTO.email());
        novoBeneficiario.setTelefone(preCadastroBeneficiarioDTO.telefone());
        novoBeneficiario.setEndereco(preCadastroBeneficiarioDTO.endereco());
        novoBeneficiario.getCompFamiliar().add(conversorMembroFamiliarFromDTO.converterMembroFamiliar(preCadastroBeneficiarioDTO.familiar()));
        return novoBeneficiario;
    }

    BeneficiarioEntity converterBeneficiario (BeneficiarioDTO beneficiarioDTO) {
        BeneficiarioEntity novoBeneficiario = new BeneficiarioEntity();
        novoBeneficiario.getCompFamiliar().addAll(conversorMembroFamiliarFromDTO.converterListaMembros(beneficiarioDTO.compFamiliar()));
        novoBeneficiario.setCpf(novoBeneficiario.getCompFamiliar().get(0).getCpf());
        novoBeneficiario.setEndereco(beneficiarioDTO.endereco());
        novoBeneficiario.setTelefone(beneficiarioDTO.telefone());
        novoBeneficiario.setEmail(beneficiarioDTO.email());
        return novoBeneficiario;
    }

    List<BeneficiarioEntity> converterListaBeneficiarios(List<BeneficiarioDTO> beneficiarioDTOs) {
        return beneficiarioDTOs.stream()
                .map(this::converterBeneficiario)
                .collect(Collectors.toList());
    }
}
