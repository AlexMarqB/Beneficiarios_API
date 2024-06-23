package com.ijb.beneficiarios_api.repositories.triagem;

import com.ijb.beneficiarios_api.entities.triagem.VisitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<VisitaEntity, Integer> {
    List<VisitaEntity> findAllByRealizada(boolean realizada);
}
