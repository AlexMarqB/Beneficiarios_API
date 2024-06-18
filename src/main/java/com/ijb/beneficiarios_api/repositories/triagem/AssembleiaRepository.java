package com.ijb.beneficiarios_api.repositories.triagem;

import com.ijb.beneficiarios_api.entities.triagem.AssembleiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssembleiaRepository extends JpaRepository<AssembleiaEntity, Integer> {
}
