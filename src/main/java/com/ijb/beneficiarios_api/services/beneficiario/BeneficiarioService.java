package com.ijb.beneficiarios_api.services.beneficiario;

import com.ijb.beneficiarios_api.dtos.creationDTOS.beneficiario.CreateBeneficiarioDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.beneficiario.BeneficiarioDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioTriagemEnum;
import com.ijb.beneficiarios_api.repositories.beneficiario.BeneficiarioRepository;
import com.ijb.beneficiarios_api.services.AbstractService;
import com.ijb.beneficiarios_api.utils.converters.beneficiario.BeneficiarioConverter;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficiarioService extends AbstractService<BeneficiarioEntity, CreateBeneficiarioDTO, BeneficiarioDTO, Integer> {

    @Autowired
    BeneficiarioRepository repository;

    @Autowired
    BeneficiarioConverter beneficiarioConverter;

    @Autowired
    MembroFamiliarService membroFamiliarService;

    @Override
    public BeneficiarioEntity create(CreateBeneficiarioDTO createBeneficiarioDTO) {
        try {
            List<MembroFamiliarEntity> compFamiliar = createBeneficiarioDTO.compFamiliar().stream()
                    .map(item -> membroFamiliarService.create(item))
                    .collect(Collectors.toList());
            BeneficiarioEntity newBeneficiario = beneficiarioConverter.converterCreateBeneficiarioDTO(createBeneficiarioDTO, compFamiliar);
            return repository.save(newBeneficiario);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public BeneficiarioEntity update(BeneficiarioDTO beneficiarioDTO) {
        try {
            BeneficiarioEntity foundEntity = repository.findById(beneficiarioDTO.getCodBeneficiario()).orElse(null);
            if (foundEntity == null) {
                throw new InternalException("Beneficiario não encontrado!");
            }
            BeanUtils.copyProperties(beneficiarioDTO, foundEntity);
            return repository.save(foundEntity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public BeneficiarioEntity getById(Integer id) {
        try {
            BeneficiarioEntity foundEntity = repository.findById(id).orElse(null);
            if (foundEntity == null) {
                throw new InternalException("Beneficiario não encontrado!");
            }
            return foundEntity;
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public List<BeneficiarioEntity> getAll() {
        return repository.findAll();
    }

    public List<BeneficiarioEntity> getAllByEstado(EstadoBeneficiarioTriagemEnum estado) {
        return repository.findAllByEstado(estado).orElse(null);
    }

    public void notificarAttDados() {
        //enviar email ou sms para o beenficiario
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            BeneficiarioEntity foundEntity = repository.findById(id).orElse(null);
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
