package com.ijb.beneficiarios_api.controllers.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateRelatorioVisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.RelatorioVisitaDTO;
import com.ijb.beneficiarios_api.services.triagem.RelatorioVisitaService;
import com.ijb.beneficiarios_api.utils.converters.triagem.RelatorioVisitaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorioVisita")
public class RelatorioVisitaController {

    @Autowired
    private RelatorioVisitaService relatorioVisitaService;

    @Autowired
    private RelatorioVisitaConverter relatorioVisitaConverter;

    @PostMapping
    public ResponseEntity criarRelatorioVisita(@RequestBody @Validated CreateRelatorioVisitaDTO dto) {
        try {
            if (dto == null) {
                return ResponseEntity.badRequest().body("É preciso todos os dados para criar um relatório de visita.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(relatorioVisitaConverter.converterRelatorioVisitaEntity(relatorioVisitaService.create(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarRelatorioVisitaPorId(@PathVariable Integer id) {
        try {
            RelatorioVisitaDTO relatorioVisita = relatorioVisitaConverter.converterRelatorioVisitaEntity(relatorioVisitaService.getById(id));
            if (relatorioVisita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o relatório de visita!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(relatorioVisita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity listarRelatoriosVisita() {
        try {
            List<RelatorioVisitaDTO> relatoriosVisita = relatorioVisitaConverter.converterListRelatorioVisitaEntity(relatorioVisitaService.getAll());
            return ResponseEntity.status(HttpStatus.OK).body(relatoriosVisita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity atualizarRelatorioVisita(@PathVariable Integer id, @RequestBody @Validated RelatorioVisitaDTO dto) {
        try {
            RelatorioVisitaDTO relatorioVisita = relatorioVisitaConverter.converterRelatorioVisitaEntity(relatorioVisitaService.getById(id));
            if (relatorioVisita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o relatório de visita!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(relatorioVisitaConverter.converterRelatorioVisitaEntity(relatorioVisitaService.update(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletarRelatorioVisita(@PathVariable Integer id) {
        try {
            RelatorioVisitaDTO relatorioVisita = relatorioVisitaConverter.converterRelatorioVisitaEntity(relatorioVisitaService.getById(id));
            if (relatorioVisita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o relatório de visita!");
            }
            if (relatorioVisitaService.deleteById(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Relatório de visita deletado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível deletar o relatório de visita!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
