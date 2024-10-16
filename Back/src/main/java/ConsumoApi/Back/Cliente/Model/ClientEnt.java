package ConsumoApi.Back.Cliente.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Cliente_Entrada")
@EqualsAndHashCode(of = "id")
public class ClientEnt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private String emailCliente;

    @Column(nullable = false)
    private String CpfCliente;

    @Column(nullable = false)
    private String IdentificadorCarga;

    @Column(nullable = false)
    private LocalDateTime DataCriacao;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getIdentificadorCarga() {
        return IdentificadorCarga;
    }

    public void setIdentificadorCarga(String identificadorCarga) {
        IdentificadorCarga = identificadorCarga;
    }

    public LocalDateTime getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCpfCliente() {
        return CpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        CpfCliente = cpfCliente;
    }
}
