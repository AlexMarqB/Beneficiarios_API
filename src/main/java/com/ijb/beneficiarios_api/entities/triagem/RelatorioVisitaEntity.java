package com.ijb.beneficiarios_api.entities.triagem;

import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_relatorio_visita")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioVisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codRelatorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codVisita", referencedColumnName = "codVisita")
    private VisitaEntity visita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codBeneficiario", referencedColumnName = "codBeneficiario")
    private BeneficiarioEntity beneficiario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codVoluntario", referencedColumnName = "codVoluntario")
    private VoluntarioEntity voluntario;

    private String descricao;
}
