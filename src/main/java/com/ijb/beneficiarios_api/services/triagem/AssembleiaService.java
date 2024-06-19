package com.ijb.beneficiarios_api.services.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateAssembleiaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.AssembleiaDTO;
import com.ijb.beneficiarios_api.entities.triagem.AssembleiaEntity;
import com.ijb.beneficiarios_api.repositories.triagem.AssembleiaRepository;
import com.ijb.beneficiarios_api.services.AbstractService;
import com.ijb.beneficiarios_api.services.beneficiario.BeneficiarioService;
import com.ijb.beneficiarios_api.services.voluntario.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssembleiaService extends AbstractService<AssembleiaEntity, CreateAssembleiaDTO, AssembleiaDTO, Integer> {

    @Autowired
    private AssembleiaRepository assembleiaRepository;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private VoluntarioService voluntarioService;

    @Override
    public AssembleiaEntity create(CreateAssembleiaDTO createAssembleiaDTO) {
        return null;
    }

    @Override
    public AssembleiaEntity update(AssembleiaDTO assembleiaDTO) {
        return null;
    }

    @Override
    public AssembleiaEntity getById(Integer integer) {
        return null;
    }

    @Override
    public List<AssembleiaEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }
}
