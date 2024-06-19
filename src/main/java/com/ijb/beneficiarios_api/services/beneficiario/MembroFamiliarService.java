package com.ijb.beneficiarios_api.services.beneficiario;

import com.ijb.beneficiarios_api.dtos.creationDTOS.beneficiario.CreateMembroFamiliarDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.MembroFamiliarDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import com.ijb.beneficiarios_api.repositories.beneficiario.MembroFamiliarRepository;
import com.ijb.beneficiarios_api.services.AbstractService;
import com.ijb.beneficiarios_api.utils.converters.beneficiario.MembroFamiliarConverter;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembroFamiliarService extends AbstractService<MembroFamiliarEntity, CreateMembroFamiliarDTO, MembroFamiliarDTO, String> {

    @Autowired
    MembroFamiliarRepository repository;

    @Autowired
    MembroFamiliarConverter converter;

    @Override
    public MembroFamiliarEntity create(CreateMembroFamiliarDTO createMembroFamiliarDTO) {
        try {
            MembroFamiliarEntity entity = converter.converterCreateMembroFamiliarDTO(createMembroFamiliarDTO);
            return repository.save(entity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public MembroFamiliarEntity update(MembroFamiliarDTO membroFamiliarDTO) {
        try {
            MembroFamiliarEntity foundEntity = repository.findById(membroFamiliarDTO.getCpf()).orElse(null);
            if(foundEntity == null) {
                return null;
            }
            BeanUtils.copyProperties(membroFamiliarDTO, foundEntity);
            return repository.save(foundEntity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public MembroFamiliarEntity getById(String id) {
        try {
            MembroFamiliarEntity entity = repository.findById(id).orElse(null);
            if (entity == null) {
                return null;
            }
            return entity;
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public List<MembroFamiliarEntity> getAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public boolean deleteById(String s) {
        try {
            MembroFamiliarEntity foundEntity = repository.findById(s).orElse(null);
            if(foundEntity == null) {
                return false;
            }
            repository.delete(foundEntity);
            return true;
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
}
