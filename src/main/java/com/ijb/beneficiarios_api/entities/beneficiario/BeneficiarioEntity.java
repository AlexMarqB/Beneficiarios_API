package com.ijb.beneficiarios_api.entities.beneficiario;

import com.ijb.beneficiarios_api.entities.Usuario;
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
public class BeneficiarioEntity extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codBeneficiario;

    @Column(nullable = false, unique = true)
    private String cpf;

    private EstadoBeneficiarioEnum estadoAtividade = EstadoBeneficiarioEnum.PENDENTE;
    private LocalDateTime dtCadastro;
    private LocalDateTime dtAtualizacao;
    private String endereco;
    private String telefone;
    private String email;

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

    @PostPersist
    public void postPersist() {
        dtCadastro = LocalDateTime.now();
    }

    @PostUpdate
    public void postUpdate() {
        dtAtualizacao = LocalDateTime.now();
    }
}
