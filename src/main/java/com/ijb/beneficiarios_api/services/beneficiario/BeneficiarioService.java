package com.ijb.beneficiarios_api.services.beneficiario;

import com.ijb.beneficiarios_api.dtos.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.beneficiario.PreCadastroBeneficiarioDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioEnum;
import com.ijb.beneficiarios_api.repositories.beneficiario.BeneficiarioRepository;
import com.ijb.beneficiarios_api.util.fromDTO.beneficiario.ConversorBeneficiarioFromDTO;
import com.ijb.beneficiarios_api.util.fromEntity.beneficiario.ConversorBeneficiarioFromEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    @Autowired
    private ConversorBeneficiarioFromDTO conversorBeneficiarioFromDTO;
    @Autowired
    private ConversorBeneficiarioFromEntity conversorBeneficiarioFromEntity;

    public ResponseEntity preCadastrarBeneficiario(PreCadastroBeneficiarioDTO dto) {
        if(dto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Precisa fornecer todas as informações!");
        try {
            BeneficiarioEntity novoBeneficiario = conversorBeneficiarioFromDTO.converterPreCadastro(dto);
            beneficiarioRepository.save(novoBeneficiario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Beneficiario pré-cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado!\n" + e.getMessage());
        }
    }

    public ResponseEntity cadastrarBeneficiario(BeneficiarioDTO dto) {
        if(dto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Precisa fornecer todas as informações!");
        try {
            BeneficiarioEntity novoBeneficiario = conversorBeneficiarioFromDTO.converterBeneficiario(dto);
            beneficiarioRepository.save(novoBeneficiario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Beneficiario cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado!\n" + e.getMessage());
        }
    }

    public ResponseEntity atualizarBeneficiario(BeneficiarioDTO dto) {
        Optional<BeneficiarioEntity> beneficiarioExistente = beneficiarioRepository.findById(dto.codBeneficiario());
        if(beneficiarioExistente.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiario não encontrado!");
        try {
            BeanUtils.copyProperties(dto, beneficiarioExistente);
            beneficiarioRepository.save(beneficiarioExistente.get());
            return ResponseEntity.status(HttpStatus.OK).body("Informações atualizadas com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado!\n" + e.getMessage());
        }
    }

    public ResponseEntity buscarBeneficiarioPorCodigo(Long codBeneficiario) {
        Optional<BeneficiarioEntity> beneficiarioExistente = beneficiarioRepository.findById(codBeneficiario);
        if (beneficiarioExistente.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiario não encontrado!");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(conversorBeneficiarioFromEntity.converterBeneficiario(beneficiarioExistente.get()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado!\n" + e.getMessage());
        }

    }

    public ResponseEntity listarBeneficiarios() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(conversorBeneficiarioFromEntity.converterListaBeneficiarios(beneficiarioRepository.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado!\n" + e.getMessage());
        }

    }

    public ResponseEntity listarBeneficiarioPorEstado(EstadoBeneficiarioEnum estado) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(conversorBeneficiarioFromEntity.converterListaBeneficiarios(beneficiarioRepository.findAllByEstadoAtividade(estado)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado!\n" + e.getMessage());
        }
    }

    public ResponseEntity solicitarAtualizacao(Long codBeneficiario) {
        try {
            //lógica para notificar um voluntario
            return ResponseEntity.status(HttpStatus.OK).body("Notificação enviada logo um voluntário entrara em contato!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo deu errado!\n" + e.getMessage());
        }
    }
}
