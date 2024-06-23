package com.ijb.beneficiarios_api.controllers.beneficiario;

import com.ijb.beneficiarios_api.dtos.creationDTOS.beneficiario.CreateMembroFamiliarDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.MembroFamiliarDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import com.ijb.beneficiarios_api.services.beneficiario.BeneficiarioService;
import com.ijb.beneficiarios_api.services.beneficiario.MembroFamiliarService;
import com.ijb.beneficiarios_api.utils.converters.beneficiario.BeneficiarioConverter;
import com.ijb.beneficiarios_api.utils.converters.beneficiario.MembroFamiliarConverter;
import com.ijb.beneficiarios_api.utils.validators.StringValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membroFamiliar")
public class MembroFamiliarController {

    @Autowired
    private MembroFamiliarService membroFamiliarService;

    @Autowired
    private MembroFamiliarConverter membroFamiliarConverter;

    @Autowired
    private StringValidator stringValidator;

    @PostMapping
    public ResponseEntity criarMembroFamiliar(@RequestBody @Validated CreateMembroFamiliarDTO dto) {
        try {
            if(dto == null) {
                return ResponseEntity.badRequest().body("É preciso todos os dados para criar um beneficiario");
            }
            if(!stringValidator.validarCpf(dto.cpf())) {
                return ResponseEntity.badRequest().body("Cpf invalido!");
            }
            if(!stringValidator.validarEmail(dto.email())) {
                return ResponseEntity.badRequest().body("Email invalido!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(membroFamiliarConverter.converterMembroFamiliarEntity(membroFamiliarService.create(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity buscarMembroFamiliarPorCpf(@PathVariable String cpf) {
        try {
            MembroFamiliarDTO membroFamiliar = membroFamiliarConverter.converterMembroFamiliarEntity(membroFamiliarService.getById(cpf));
            if (membroFamiliar == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel encontrar o membroFamiliar!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(membroFamiliar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/codBeneficiario/{codBeneficiario}")
    public ResponseEntity listarMembroFamiliares(@PathVariable Integer codBeneficiario) {
        try {
            List<MembroFamiliarDTO> compFamiliar = membroFamiliarConverter.converterListMembroFamiliarEntity(membroFamiliarService.getAllByCodBeneficiario(codBeneficiario));
            return ResponseEntity.status(HttpStatus.OK).body(compFamiliar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/update/{cpf}")
    public ResponseEntity atualizarMembroFamiliar(@PathVariable String cpf, @RequestBody @Validated MembroFamiliarDTO dto) {
        try {
            MembroFamiliarDTO membroFamiliar = membroFamiliarConverter.converterMembroFamiliarEntity(membroFamiliarService.getById(dto.getCpf()));
            if(membroFamiliar == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel encontrar o membroFamiliar!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(membroFamiliarConverter.converterMembroFamiliarEntity(membroFamiliarService.update(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity deletarMembroFamiliar(@PathVariable String cpf) {
        try {
            MembroFamiliarDTO membroFamiliar = membroFamiliarConverter.converterMembroFamiliarEntity(membroFamiliarService.getById(cpf));
            if(membroFamiliar == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel encontrar o membroFamiliar!");
            }
            if(membroFamiliarService.deleteById(cpf)) {
                return ResponseEntity.status(HttpStatus.OK).body("Membro familiar deletado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possivel deletar o membroFamilair!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
