package com.ijb.beneficiarios_api.entities.voluntario;

import com.ijb.beneficiarios_api.entities.Usuario;
import com.ijb.beneficiarios_api.enums.FuncoesVoluntarioEnum;
import com.ijb.beneficiarios_api.entities.triagem.RelatorioVisitaEntity;
import com.ijb.beneficiarios_api.entities.triagem.VisitaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_voluntario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoluntarioEntity extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codVoluntario;

    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private Date dtNascimento;
    private FuncoesVoluntarioEnum funcao = FuncoesVoluntarioEnum.NENHUM;

    @ManyToMany(mappedBy = "voluntarios")
    private List<VisitaEntity> visitas;

    @OneToMany(mappedBy = "voluntario")
    private List<RelatorioVisitaEntity> relatorios;

    private LocalDateTime dtCadastro;

    @PostPersist
    public void postPersist() {
        dtCadastro = LocalDateTime.now();
    }
}

