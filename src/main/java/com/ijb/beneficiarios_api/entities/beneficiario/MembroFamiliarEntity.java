package com.ijb.beneficiarios_api.entities.beneficiario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb_membroFamiliar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembroFamiliarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codMembroFamiliar;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "codBeneficiario")
    private BeneficiarioEntity beneficiario;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private Date dtNascimento;
    private String escolaridade;
    private float valorRenda;
    private String origemDaRenda;
    private String problemasDeSaúde;
}
