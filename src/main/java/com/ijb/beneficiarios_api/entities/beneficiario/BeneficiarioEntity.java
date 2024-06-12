package com.ijb.beneficiarios_api.entities.beneficiario;

import com.ijb.beneficiarios_api.entities.Usuario;
import com.ijb.beneficiarios_api.entities.triagem.AssembleiaEntity;
import com.ijb.beneficiarios_api.enums.EstadoBeneficiarioTriagemEnum;
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
public class  BeneficiarioEntity extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codBeneficiario;

    private String endereço;
    private String tipoResidencia;
    private String necessidadeFamiliar;
    private EstadoBeneficiarioTriagemEnum estado= EstadoBeneficiarioTriagemEnum.PENDENTE;

    @OneToMany(mappedBy = "beneficiario")
    private List<MembroFamiliarEntity> compFamiliar;


    @ManyToOne
    @JoinColumn(name = "codAssembleia")
    private AssembleiaEntity assembleia;

    private LocalDateTime dtCadastro;
    private LocalDateTime dtAtualizacao;
    private boolean ativo = true;

    @PostPersist
    public void postPersist() {
        dtCadastro = LocalDateTime.now();
    }

    @PostUpdate
    public void postUpdate() {
        dtAtualizacao = LocalDateTime.now();
    }
}
