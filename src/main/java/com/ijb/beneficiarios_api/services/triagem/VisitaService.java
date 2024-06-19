package com.ijb.beneficiarios_api.services.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateVisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.VisitaDTO;
import com.ijb.beneficiarios_api.entities.triagem.VisitaEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import com.ijb.beneficiarios_api.repositories.triagem.VisitaRepository;
import com.ijb.beneficiarios_api.services.AbstractService;
import com.ijb.beneficiarios_api.services.voluntario.VoluntarioService;
import com.ijb.beneficiarios_api.utils.converters.triagem.VisitaConverter;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitaService extends AbstractService<VisitaEntity, CreateVisitaDTO, VisitaDTO, Integer> {

    @Autowired
    private VisitaRepository repository;

    @Autowired
    private VisitaConverter visitaConverter;

    @Autowired
    private VoluntarioService voluntarioService;


    @Override
    public VisitaEntity create(CreateVisitaDTO createVisitaDTO) {
        try {
            List<VoluntarioEntity> voluntarios = createVisitaDTO.voluntarios().stream()
                    .map(item -> voluntarioService.getById(item.getCodVoluntario()))
                    .collect(Collectors.toList());
            VisitaEntity newVisita = new VisitaEntity();
            BeanUtils.copyProperties(createVisitaDTO, newVisita, "voluntarios");
            newVisita.setVoluntarios(voluntarios);
            return repository.save(newVisita);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public VisitaEntity update(VisitaDTO visitaDTO) {
        try {
            VisitaEntity foundEntity = repository.findById(visitaDTO.getCodVisita()).orElse(null);
            if (foundEntity == null) {
                throw new InternalException("Não foi possivel encontrar a visita");
            }
            List<VoluntarioEntity> voluntarios = visitaDTO.getVoluntarios().stream()
                    .map(item -> voluntarioService.getById(item.getCodVoluntario()))
                    .collect(Collectors.toList());
            BeanUtils.copyProperties(visitaDTO, foundEntity, "voluntarios");
            foundEntity.setVoluntarios(voluntarios);
            return repository.save(foundEntity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public VisitaEntity getById(Integer id) {
        try {
            VisitaEntity foundEntity = repository.findById(id).orElse(null);
            if (foundEntity == null) {
                throw new InternalException("Visita não encontrada!");
            }
            return foundEntity;
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public List<VisitaEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            VisitaEntity foundEntity = repository.findById(id).orElse(null);
            if (foundEntity == null) {
                throw new InternalException("Visita não encontrada!");
            }
            repository.delete(foundEntity);
            return true;
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
}
