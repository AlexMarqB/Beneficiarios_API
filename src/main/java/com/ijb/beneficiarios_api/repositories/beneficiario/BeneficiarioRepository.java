package com.ijb.beneficiarios_api.repositories.beneficiario;

import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiarioRepository extends JpaRepository<BeneficiarioEntity, Long> {
    List<BeneficiarioEntity> findAllByEstadoAtividade(EstadoBeneficiarioEnum estado);
}
