package com.ijb.beneficiarios_api.entities.triagem;

import com.ijb.beneficiarios_api.entities.beneficiario.BeneficiarioEntity;
import com.ijb.beneficiarios_api.entities.voluntario.VoluntarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

@Entity
@Table(name = "tb_relatorio_visita")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioVisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codRelatorio;

    @ManyToOne
    @JoinColumn(name = "codVisita")
    private VisitaEntity visita;

    @ManyToOne
    @JoinColumn(name = "codBeneficiario")
    private BeneficiarioEntity beneficiario;

    @ManyToOne
    @JoinColumn(name = "codVoluntario")
    private VoluntarioEntity voluntario;

    private Long descricao;
    private String imagens;
}
