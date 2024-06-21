package com.ijb.beneficiarios_api.services.triagem;

import com.ijb.beneficiarios_api.dtos.creationDTOS.triagem.CreateRelatorioVisitaDTO;
import com.ijb.beneficiarios_api.dtos.dataDTOS.triagem.RelatorioVisitaDTO;
import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.triagem.RelatorioVisitaEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import com.ijb.beneficiarios_api.repositories.triagem.RelatorioVisitaRepository;
import com.ijb.beneficiarios_api.services.AbstractService;
import com.ijb.beneficiarios_api.services.beneficiario.BeneficiarioService;
import com.ijb.beneficiarios_api.services.voluntario.VoluntarioService;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.BeanUtils;
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
        try {
            BeneficiarioEntity beneficiario = beneficiarioService.getById(createRelatorioVisitaDTO.beneficiario().getCodBeneficiario());
            VoluntarioEntity voluntario = voluntarioService.getById(createRelatorioVisitaDTO.voluntario().getCodVoluntario());
            RelatorioVisitaEntity newRelatorioVisita = new RelatorioVisitaEntity();
            BeanUtils.copyProperties(createRelatorioVisitaDTO, newRelatorioVisita, "voluntario", "beneficiario");
            if (voluntario == null || beneficiario == null) {
                throw new InternalException("Não foi possivel encontrar o beneficiario ou o voluntario!");
            }
            newRelatorioVisita.setVoluntario(voluntario);
            newRelatorioVisita.setBeneficiario(beneficiario);
            return repository.save(newRelatorioVisita);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public RelatorioVisitaEntity update(RelatorioVisitaDTO relatorioVisitaDTO) {
        try {
            RelatorioVisitaEntity foundEntity = repository.findById(relatorioVisitaDTO.getCodRelatorio()).orElse(null);
            if(foundEntity == null) {
                throw new InternalException("Não foi possivel encontrar o relátorio!");
            }
            BeneficiarioEntity beneficiario = beneficiarioService.getById(relatorioVisitaDTO.getBeneficiario().getCodBeneficiario());
            VoluntarioEntity voluntario = voluntarioService.getById(relatorioVisitaDTO.getVoluntario().getCodVoluntario());
            BeanUtils.copyProperties(relatorioVisitaDTO, foundEntity, "voluntario", "beneficiario");
            if (voluntario == null || beneficiario == null) {
                throw new InternalException("Não foi possivel encontrar o beneficiario ou o voluntario!");
            }
            foundEntity.setVoluntario(voluntario);
            foundEntity.setBeneficiario(beneficiario);
            return repository.save(foundEntity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public RelatorioVisitaEntity getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<RelatorioVisitaEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            RelatorioVisitaEntity foundEntity = repository.findById(id).orElse(null);
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
