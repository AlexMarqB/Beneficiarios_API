package com.ijb.beneficiarios_api.controllers.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateVisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.VisitaDTO;
import com.ijb.beneficiarios_api.services.triagem.VisitaService;
import com.ijb.beneficiarios_api.utils.converters.triagem.VisitaConverter;
import com.ijb.beneficiarios_api.utils.validators.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visita")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @Autowired
    private VisitaConverter visitaConverter;

    @PostMapping
    public ResponseEntity criarVisita(@RequestBody @Validated CreateVisitaDTO dto) {
        try {
            if (dto == null) {
                return ResponseEntity.badRequest().body("É preciso todos os dados para criar uma visita.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(visitaConverter.converterVisitaEntity(visitaService.create(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarVisitaPorId(@PathVariable Integer id) {
        try {
            VisitaDTO visita = visitaConverter.converterVisitaEntity(visitaService.getById(id));
            if (visita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar a visita!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(visita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity listarVisitas() {
        try {
            List<VisitaDTO> visitas = visitaConverter.converterListVisitaEntity(visitaService.getAll());
            return ResponseEntity.status(HttpStatus.OK).body(visitas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/realizada/{realizada}")
    public ResponseEntity listarVisitasPorRealizacao(@PathVariable boolean realizada) {
        try {
            List<VisitaDTO> visitas = visitaConverter.converterListVisitaEntity(visitaService.getAllByRealizada(realizada));
            return ResponseEntity.status(HttpStatus.OK).body(visitas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity atualizarVisita(@PathVariable Integer id, @RequestBody @Validated VisitaDTO dto) {
        try {
            VisitaDTO visita = visitaConverter.converterVisitaEntity(visitaService.getById(id));
            if (visita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar a visita!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(visitaConverter.converterVisitaEntity(visitaService.update(dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletarVisita(@PathVariable Integer id) {
        try {
            VisitaDTO visita = visitaConverter.converterVisitaEntity(visitaService.getById(id));
            if (visita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar a visita!");
            }
            if (visitaService.deleteById(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Visita deletada com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível deletar a visita!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
