package com.ijb.beneficiarios_api.entities.beneficiario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_membroFamiliar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembroFamiliarEntity {

    @Id
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "codBeneficiario")
    private BeneficiarioEntity beneficiario;

    private String rg;
    private String nome;
    private String sobrenome;

    @Column(unique = true)
    private String email;

    private String telefone;
    private String senha;
    private int idade;
    private String escolaridade;
    private float renda;
    private String origemRenda;
    private String problemasSaude;
    private LocalDateTime dtCadastro;

    @PostPersist
    public void postPersist() {
        dtCadastro = LocalDateTime.now();

    }
}