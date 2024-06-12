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
@Table(name = "tb_visita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codVisita;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_visita_voluntarios",
            joinColumns = @JoinColumn(name = "codVisita"),
            inverseJoinColumns = @JoinColumn(name = "codVoluntario")
    )
    private List<VoluntarioEntity> voluntarios;

    @ManyToOne
    @JoinColumn(name = "codBeneficiario")
    private BeneficiarioEntity beneficiario;

    private Date dataVisita;
    private boolean realizada;

    @OneToMany(mappedBy = "visita")
    private List<RelatorioVisitaEntity> relatorios;
}
