package ConsumoApi.Back.Cliente.Model;

import java.util.List;

public class CargaResponse {
    private String identificadorCarga;
    private List<ClientSaid> clientes;

    public List<ClientSaid> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClientSaid> clientes) {
        this.clientes = clientes;
    }

    public String getIdentificadorCarga() {
        return identificadorCarga;
    }

    public void setIdentificadorCarga(String identificadorCarga) {
        this.identificadorCarga = identificadorCarga;
    }
}


