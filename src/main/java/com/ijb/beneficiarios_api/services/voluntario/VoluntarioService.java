package com.ijb.beneficiarios_api.services.voluntario;

import com.ijb.beneficiarios_api.dtos.creationDTOS.voluntario.CreateVoluntarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.voluntario.VoluntarioDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import com.ijb.beneficiarios_api.repositories.voluntario.VoluntarioRepository;
import com.ijb.beneficiarios_api.services.AbstractService;
import com.ijb.beneficiarios_api.utils.converters.voluntario.VoluntarioConverter;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoluntarioService extends AbstractService<VoluntarioEntity, CreateVoluntarioDTO, VoluntarioDTO, Integer> {

    @Autowired
    private VoluntarioRepository repository;

    @Autowired
    private VoluntarioConverter voluntarioConverter;


    @Override
    public VoluntarioEntity create(CreateVoluntarioDTO createVoluntarioDTO) {
        try {
            VoluntarioEntity newVoluntario = voluntarioConverter.converterCreateVoluntarioDTO(createVoluntarioDTO);
            return repository.save(newVoluntario);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public VoluntarioEntity update(VoluntarioDTO voluntarioDTO) {
        try {
            VoluntarioEntity foundEntity = repository.findById(voluntarioDTO.getCodVoluntario()).orElse(null);
            if (foundEntity == null) {
                throw new InternalException("Voluntario não encontrado!");
            }
            BeanUtils.copyProperties(voluntarioDTO, foundEntity);
            return repository.save(foundEntity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public VoluntarioEntity getById(Integer id) {
        try {
            VoluntarioEntity foundEntity = repository.findById(id).orElse(null);
            if (foundEntity == null) {
                throw new InternalException("Beneficiario não encontrado!");
            }
            return foundEntity;
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public List<VoluntarioEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            VoluntarioEntity foundEntity = repository.findById(id).orElse(null);
            if (foundEntity == null) {
                throw new InternalException("Beneficiario não encontrado!");
            }
            repository.delete(foundEntity);
            return true;
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
}
