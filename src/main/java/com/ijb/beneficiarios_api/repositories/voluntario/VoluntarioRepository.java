package com.ijb.beneficiarios_api.repositories.voluntario;

import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoluntarioRepository extends JpaRepository<VoluntarioEntity, Integer> {
}
