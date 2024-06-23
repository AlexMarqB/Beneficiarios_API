package com.ijb.beneficiarios_api.repositories.beneficiario;

import com.ijb.beneficiarios_api.entities.beneficiario.MembroFamiliarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroFamiliarRepository extends JpaRepository<MembroFamiliarEntity, String> {
    List<MembroFamiliarEntity> findALlByBeneficiario_CodBeneficiario(Integer codBeneficiario);
}
