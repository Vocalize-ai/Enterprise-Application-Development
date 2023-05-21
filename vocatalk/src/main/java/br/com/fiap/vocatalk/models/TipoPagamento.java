package br.com.fiap.vocatalk.models;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_vt_tipo_pagamento")
public class TipoPagamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo_pagamento")
    @Schema(example = "1")
    private Long id;

    @Size(min = 2, max = 50)
    @NotBlank(message = "O nome do tipo de pagamento tem que ser preenchido")
    @Column(name = "nm_tipo_pagamento")
    @Schema(example = "Boleto")
    private String nome;

    @Size(min = 0, max = 80)
    @Column(name = "ds_tipo_pagamento")
    @Schema(example = "Pagamento boleto")
    private String descricao;

    @OneToMany(mappedBy = "tipoPagamento")
    @JsonIgnore
    private List<Fatura> faturas;


    public TipoPagamento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
