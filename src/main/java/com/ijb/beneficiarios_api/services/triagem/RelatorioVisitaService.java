package com.ijb.beneficiarios_api.services.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateRelatorioVisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.RelatorioVisitaDTO;
import com.ijb.beneficiarios_api.entities.triagem.RelatorioVisitaEntity;
import com.ijb.beneficiarios_api.repositories.triagem.RelatorioVisitaRepository;
import com.ijb.beneficiarios_api.services.AbstractService;
import com.ijb.beneficiarios_api.services.beneficiario.BeneficiarioService;
import com.ijb.beneficiarios_api.services.voluntario.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioVisitaService extends AbstractService<RelatorioVisitaEntity, CreateRelatorioVisitaDTO, RelatorioVisitaDTO, Integer> {

    @Autowired
    private RelatorioVisitaRepository repository;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private VoluntarioService voluntarioService;


    @Override
    public RelatorioVisitaEntity create(CreateRelatorioVisitaDTO createRelatorioVisitaDTO) {
        return null;
    }

    @Override
    public RelatorioVisitaEntity update(RelatorioVisitaDTO relatorioVisitaDTO) {
        return null;
    }

    @Override
    public RelatorioVisitaEntity getById(Integer integer) {
        return null;
    }

    @Override
    public List<RelatorioVisitaEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }
}
