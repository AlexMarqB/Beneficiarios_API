package com.ijb.beneficiarios_api.entities.beneficiario;

import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioEnum;
import com.ijb.beneficiarios_api.entities.triagem.RelatorioVisitaEntity;
import com.ijb.beneficiarios_api.entities.triagem.VisitaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_beneficiario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codBeneficiario;
    private EstadoBeneficiarioEnum estadoAtividade = EstadoBeneficiarioEnum.PENDENTE;
    private LocalDateTime dtCatdastro;
    private LocalDateTime dtAtualizacao;
    private String nomeReponsavel;
    private String rg;
    private String endereco;
    private String telefone;

    @OneToMany(mappedBy = "beneficiario")
    private List<MembroFamiliarEntity> compFamiliar;

    private String tipodeResidencia;
    private String possuiTerreno;
    private String necessidadeDaFamilia;

    @OneToMany(mappedBy = "beneficiario")
    private List<RelatorioVisitaEntity> relatorios;

    @OneToMany(mappedBy = "beneficiario")
    private List<VisitaEntity> visitas;

    @Column(name = "ativo", columnDefinition = "boolean default true")
    private boolean ativo;
}
