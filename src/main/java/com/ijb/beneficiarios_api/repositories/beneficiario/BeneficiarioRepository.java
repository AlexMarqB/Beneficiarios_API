package com.ijb.beneficiarios_api.repositories.beneficiario;

import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioTriagemEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiarioRepository extends JpaRepository<BeneficiarioEntity, Integer> {
    Optional<List<BeneficiarioEntity>> findAllByEstado(EstadoBeneficiarioTriagemEnum estado);

    BeneficiarioEntity findFirstByMembrosFamiliares_Cpf(String cpf);
}
