package com.ijb.beneficiarios_api.repositories.beneficiario;

import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioRepository extends JpaRepository<BeneficiarioEntity, Long> {
}
