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
@Table(name = "Cliente_Saida")
@EqualsAndHashCode(of = "id")

public class ClientSaid implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long idOriginal;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private String emailCliente;

    @Column(nullable = false)
    private String cpfCliente;

    @Column(nullable = false)
    private String identificadorCarga;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;


    public Long getIdOriginal() {
        return idOriginal;
    }

    public void setIdOriginal(Long idOriginal) {
        this.idOriginal = idOriginal;
    }

    public String getIdentificadorCarga() {
        return identificadorCarga;
    }

    public void setIdentificadorCarga(String identificadorCarga) {

        this.identificadorCarga = identificadorCarga;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
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
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
}
