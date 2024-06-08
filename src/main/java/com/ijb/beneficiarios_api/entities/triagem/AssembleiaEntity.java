package com.ijb.beneficiarios_api.entities.triagem;

import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_assembleia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssembleiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAssembleia;

    @OneToMany(fetch = FetchType.EAGER)
    private List<VoluntarioEntity> voluntarios;

    @OneToMany(fetch = FetchType.EAGER)
    private List<BeneficiarioEntity> beneficiarios;

    private Date dataAssembleia;
}
