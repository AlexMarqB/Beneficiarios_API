package com.ijb.beneficiarios_api.controllers.voluntario;

import com.ijb.beneficiarios_api.dtos.creationDTOS.voluntario.CreateVoluntarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;
import com.ijb.beneficiarios_api.services.voluntario.VoluntarioService;
import com.ijb.beneficiarios_api.utils.converters.voluntario.VoluntarioConverter;
import com.ijb.beneficiarios_api.utils.validators.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    @Autowired
    private VoluntarioConverter voluntarioConverter;

    @Autowired
    private StringValidator stringValidator;

    @PostMapping
    public ResponseEntity criarVoluntario(@RequestBody @Validated CreateVoluntarioDTO dto) {
        try {
            if(dto == null) {
                return ResponseEntity.badRequest().body("É preciso todos os dados para criar um voluntário.");
            }
            if(!stringValidator.validarCpf(dto.cpf())) {
                return ResponseEntity.badRequest().body("Cpf inválido!");
            }
            if(!stringValidator.validarEmail(dto.email())) {
                return ResponseEntity.badRequest().body("Email inválido!");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(voluntarioConverter.converterVoluntarioEntity(voluntarioService.create(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarVoluntarioPorId(@PathVariable Integer id) {
        try {
            VoluntarioDTO voluntario = voluntarioConverter.converterVoluntarioEntity(voluntarioService.getById(id));
            if (voluntario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o voluntário!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(voluntario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity listarVoluntarios() {
        try {
            List<VoluntarioDTO> voluntarios = voluntarioConverter.converterListVoluntarioEntity(voluntarioService.getAll());
            return ResponseEntity.status(HttpStatus.OK).body(voluntarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity atualizarVoluntario(@PathVariable Integer id, @RequestBody @Validated VoluntarioDTO dto) {
        try {
            VoluntarioDTO voluntario = voluntarioConverter.converterVoluntarioEntity(voluntarioService.getById(id));
            if(voluntario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o voluntário!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(voluntarioConverter.converterVoluntarioEntity(voluntarioService.update(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletarVoluntario(@PathVariable Integer id) {
        try {
            VoluntarioDTO voluntario = voluntarioConverter.converterVoluntarioEntity(voluntarioService.getById(id));
            if(voluntario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o voluntário!");
            }
            if(voluntarioService.deleteById(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Voluntário deletado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível deletar o voluntário!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
