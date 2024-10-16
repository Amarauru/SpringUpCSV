package ConsumoApi.Back.Cliente.Controller;

import ConsumoApi.Back.Cliente.Model.CargaResponse;
import ConsumoApi.Back.Cliente.Model.ClientEnt;
import ConsumoApi.Back.Cliente.Model.ClientSaid;
import ConsumoApi.Back.Cliente.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/ClientTotal")
    public List<ClientEnt> listAll() {
        return clienteService.ListAll();
    }

    @GetMapping("/ClientTotalConsultados")
    public List<ClientSaid> ListAllCConsultados() {
        return clienteService.ListAllConsultados();
    }


    @GetMapping("/Count")
    public Map<String, Long> count() {
        long totalCliente = clienteService.Count();
        Map<String, Long> response = new HashMap<>();
        response.put("totalClientes", totalCliente);
        return response;
    }

    @GetMapping("/clientes-consultados")
    public Map<String, Long> CountConsultados() {
        long totalCliente = clienteService.CountConsultados();
        Map<String, Long> response = new HashMap<>();
        response.put("totalConsultados", totalCliente);
        return response;
    }

    @GetMapping("/cargas")
    public ResponseEntity<List<CargaResponse>> getCargas() {
        List<ClientSaid> todosClientes = clienteService.ListAllConsultados();

        Map<String, List<ClientSaid>> cargasMap = todosClientes.stream()
                .collect(Collectors.groupingBy(ClientSaid::getIdentificadorCarga));

        List<CargaResponse> resposta = new ArrayList<>();
        for (Map.Entry<String, List<ClientSaid>> entry : cargasMap.entrySet()) {
            CargaResponse carga = new CargaResponse();
            carga.setIdentificadorCarga(entry.getKey());
            carga.setClientes(entry.getValue());
            resposta.add(carga);
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/db-ValidClient")
    public ResponseEntity<String> Validation(){
        return clienteService.validarETransferirClientes();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return clienteService.uploadFile(file);
    }



}
