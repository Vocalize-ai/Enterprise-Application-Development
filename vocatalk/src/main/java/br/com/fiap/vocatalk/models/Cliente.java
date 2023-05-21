package br.com.fiap.vocatalk.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "t_vt_cliente")
@Schema(description = "Modelo de dados para um cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    @Schema(example = "1", description = "ID do cliente")
    private Long id;

    @NotBlank(message = "O nome tem que ser preenchido")
    @Size(min = 0, max = 120)
    @Column(name = "nm_cliente")
    @Schema(example = "João da Silva", description = "Nome do cliente")
    private String nome;

    @NotBlank(message = "O cpf tem que ser preenchido")
    @Size(min = 11, max = 11)
    @Column(name = "nr_cpf", unique = true)
    @Schema(example = "12345678901", description = "CPF do cliente")
    private String cpf;

    @NotNull(message = "A data não pode ser nula")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_cadastro")
    @Schema(description = "Data de cadastro do cliente", example = "2023-05-20T10:30:00")
    private LocalDateTime dataCadastro;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Login login;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Schema(description = "Lista de faturas do cliente")
    private List<Fatura> fatura = new ArrayList<Fatura>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_telefone_contato")
    @Schema(description = "Telefone de contato do cliente")
    private Telefone telefoneContato;

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataCadastro=" + dataCadastro
                + ", telefoneContato=" + telefoneContato + "]";
    }

}
