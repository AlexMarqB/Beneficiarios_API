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
    private Integer codAssembleia;

    @ManyToMany
    @JoinTable(
            name = "tb_participa_assembleia",
            joinColumns = @JoinColumn(name = "codAssembleia"),
            inverseJoinColumns = @JoinColumn(name = "codVoluntario")
    )
    private List<VoluntarioEntity> voluntarios;

    @OneToMany(mappedBy = "assembleia")
    private List<BeneficiarioEntity> beneficiarios;

    private Date dtAssembleia;
    private String decisao;
}
