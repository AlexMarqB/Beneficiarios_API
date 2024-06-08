package com.ijb.beneficiarios_api.entities.voluntario;

import com.ijb.beneficiarios_api.enums.FuncoesVoluntarioEnum;
import com.ijb.beneficiarios_api.entities.triagem.RelatorioVisitaEntity;
import com.ijb.beneficiarios_api.entities.triagem.VisitaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_voluntario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoluntarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codVoluntario;
    private String nome, sobrenome, email;
    private Date dataNascimento;
    private FuncoesVoluntarioEnum funcao;

    @ManyToMany(mappedBy = "voluntarios", fetch = FetchType.LAZY)
    private List<VisitaEntity> visitas;

    @OneToMany(mappedBy = "voluntario", fetch = FetchType.LAZY)
    private List<RelatorioVisitaEntity> relatorios;
}

