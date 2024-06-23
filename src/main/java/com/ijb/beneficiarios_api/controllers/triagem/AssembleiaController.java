package com.ijb.beneficiarios_api.controllers.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateAssembleiaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.AssembleiaDTO;
import com.ijb.beneficiarios_api.services.triagem.AssembleiaService;
import com.ijb.beneficiarios_api.utils.converters.triagem.AssembleiaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assembleia")
public class AssembleiaController {

    @Autowired
    private AssembleiaService assembleiaService;

    @Autowired
    private AssembleiaConverter assembleiaConverter;

    @PostMapping
    public ResponseEntity criarAssembleia(@RequestBody @Validated CreateAssembleiaDTO dto) {
        try {
            if (dto == null) {
                return ResponseEntity.badRequest().body("É preciso todos os dados para criar uma assembleia.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(assembleiaConverter.converterAssembleiaEntity(assembleiaService.create(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarAssembleiaPorId(@PathVariable Integer id) {
        try {
            AssembleiaDTO assembleia = assembleiaConverter.converterAssembleiaEntity(assembleiaService.getById(id));
            if (assembleia == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar a assembleia!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(assembleia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity listarAssembleias() {
        try {
            List<AssembleiaDTO> assembleias = assembleiaConverter.converterListAssembleiaEntity(assembleiaService.getAll());
            return ResponseEntity.status(HttpStatus.OK).body(assembleias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity atualizarAssembleia(@PathVariable Integer id, @RequestBody @Validated AssembleiaDTO dto) {
        try {
            AssembleiaDTO assembleia = assembleiaConverter.converterAssembleiaEntity(assembleiaService.getById(id));
            if (assembleia == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar a assembleia!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(assembleiaConverter.converterAssembleiaEntity(assembleiaService.update(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletarAssembleia(@PathVariable Integer id) {
        try {
            AssembleiaDTO assembleia = assembleiaConverter.converterAssembleiaEntity(assembleiaService.getById(id));
            if (assembleia == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar a assembleia!");
            }
            if (assembleiaService.deleteById(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Assembleia deletada com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível deletar a assembleia!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
