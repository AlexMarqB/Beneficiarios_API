package com.ijb.beneficiarios_api.controllers.beneficiario;

import com.ijb.beneficiarios_api.dtos.creationDTOS.beneficiario.CreateBeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioTriagemEnum;
import com.ijb.beneficiarios_api.services.beneficiario.BeneficiarioService;
import com.ijb.beneficiarios_api.utils.converters.beneficiario.BeneficiarioConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private BeneficiarioConverter beneficiarioConverter;

    @PostMapping
    public ResponseEntity criarBeneficiario(@RequestBody @Validated CreateBeneficiarioDTO dto) {
        try {
            if(dto == null) return ResponseEntity.badRequest().body("É preciso todos os dados para criar um beneficiario");
            return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioConverter.converterBeneficiarioEntity(beneficiarioService.create(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarBeneficiarioPorId(@RequestParam("id") Integer id) {
        try {
            BeneficiarioDTO beneficiario = beneficiarioConverter.converterBeneficiarioEntity(beneficiarioService.getById(id));
            if(beneficiario == null) return ResponseEntity.badRequest().body("Não foi possivel encontrar o beneficiario!");
            return ResponseEntity.status(HttpStatus.OK).body(beneficiario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity listarBeneficiarios() {
        try {
            List<BeneficiarioDTO> beneficiarios = beneficiarioConverter.converterListBeneficiarioEntity(beneficiarioService.getAll());
            return ResponseEntity.status(HttpStatus.OK).body(beneficiarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity listarBeneficiarioPorEstado(@PathVariable EstadoBeneficiarioTriagemEnum estado) {
        try {
            List<BeneficiarioDTO> beneficiarios = beneficiarioConverter.converterListBeneficiarioEntity(beneficiarioService.getAllByEstado(estado));
            return ResponseEntity.status(HttpStatus.OK).body(beneficiarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity buscarBeneficiarioPorCpf(@PathVariable String cpf) {
        try {
            BeneficiarioDTO beneficiario = beneficiarioConverter.converterBeneficiarioEntity(beneficiarioService.getByCpf(cpf));
            if(beneficiario == null) return ResponseEntity.badRequest().body("Não foi possivel encontrar o beneficiario!");
            return ResponseEntity.status(HttpStatus.OK).body(beneficiario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/update/id/{id}")
    public ResponseEntity atualizarBeneficiario(@PathVariable Integer id, @RequestBody @Validated BeneficiarioDTO dto) {
        try {
            BeneficiarioDTO beneficiario = beneficiarioConverter.converterBeneficiarioEntity(beneficiarioService.getById(id));
            if(beneficiario == null) return ResponseEntity.badRequest().body("Não foi possivel encontrar o beneficiario!");
            return ResponseEntity.status(HttpStatus.OK).body(beneficiarioConverter.converterBeneficiarioEntity(beneficiarioService.update(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluirBeneficiario(@PathVariable Integer id) {
        try {
            BeneficiarioDTO beneficiario = beneficiarioConverter.converterBeneficiarioEntity(beneficiarioService.getById(id));
            if(beneficiario == null) return ResponseEntity.badRequest().body("Não foi possivel encontrar o beneficiario!");
            boolean deletado = beneficiarioService.deleteById(id);
            if(deletado) {
                return ResponseEntity.status(HttpStatus.OK).body("Beneficiario removido com sucesso!");
            } else {
                return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).body("Não foi possivel deletar o beneficiario!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
