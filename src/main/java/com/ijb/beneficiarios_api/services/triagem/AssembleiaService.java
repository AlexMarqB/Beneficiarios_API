package com.ijb.beneficiarios_api.services.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateAssembleiaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.AssembleiaDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.triagem.AssembleiaEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import com.ijb.beneficiarios_api.repositories.triagem.AssembleiaRepository;
import com.ijb.beneficiarios_api.services.AbstractService;
import com.ijb.beneficiarios_api.services.beneficiario.BeneficiarioService;
import com.ijb.beneficiarios_api.services.voluntario.VoluntarioService;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssembleiaService extends AbstractService<AssembleiaEntity, CreateAssembleiaDTO, AssembleiaDTO, Integer> {

    @Autowired
    private AssembleiaRepository repository;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private VoluntarioService voluntarioService;

    @Override
    public AssembleiaEntity create(CreateAssembleiaDTO createAssembleiaDTO) {
        try {
            List<BeneficiarioEntity> beneficiarios = createAssembleiaDTO.beneficiarios().stream()
                    .map(item -> beneficiarioService.getById(item.getCodBeneficiario()))
                    .collect(Collectors.toUnmodifiableList());
            List<VoluntarioEntity> voluntarios = createAssembleiaDTO.voluntarios().stream()
                    .map(item -> voluntarioService.getById(item.getCodVoluntario()))
                    .collect(Collectors.toUnmodifiableList());
            if (beneficiarios.size() == 0 || voluntarios.size() == 0) {
                throw new InternalException("É preciso pelo menos 1 beneficiario e 1 voluntario para a assembeleia!");
            }
            AssembleiaEntity newAssembleia = new AssembleiaEntity();
            BeanUtils.copyProperties(createAssembleiaDTO, newAssembleia, "voluntarios", "beneficarios");
            newAssembleia.setBeneficiarios(beneficiarios);
            newAssembleia.setVoluntarios(voluntarios);
            return repository.save(newAssembleia);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public AssembleiaEntity update(AssembleiaDTO assembleiaDTO) {
        try {
            AssembleiaEntity foundEntity = repository.findById(assembleiaDTO.getCodAssembleia()).orElse(null);
            if(foundEntity == null) {
                throw new InternalException("Não foi possivel encontrar a assembleia!");
            }
            List<BeneficiarioEntity> beneficiarios = assembleiaDTO.getBeneficiarios().stream()
                    .map(item -> beneficiarioService.getById(item.getCodBeneficiario()))
                    .collect(Collectors.toUnmodifiableList());
            List<VoluntarioEntity> voluntarios = assembleiaDTO.getVoluntarios().stream()
                    .map(item -> voluntarioService.getById(item.getCodVoluntario()))
                    .collect(Collectors.toUnmodifiableList());
            BeanUtils.copyProperties(assembleiaDTO, foundEntity, "voluntario", "beneficiario");
            if (beneficiarios.size() == 0 || voluntarios.size() == 0) {
                throw new InternalException("É preciso pelo menos 1 beneficiario e 1 voluntario para a assembeleia!");
            }
            foundEntity.setVoluntarios(voluntarios);
            foundEntity.setBeneficiarios(beneficiarios);
            return repository.save(foundEntity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public AssembleiaEntity getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<AssembleiaEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            AssembleiaEntity foundEntity = repository.findById(id).orElse(null);
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
