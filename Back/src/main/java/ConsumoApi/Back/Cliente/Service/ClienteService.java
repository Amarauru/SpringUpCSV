package ConsumoApi.Back.Cliente.Service;

import ConsumoApi.Back.Cliente.Model.ClientEnt;
import ConsumoApi.Back.Cliente.Model.ClientEntDTO;
import ConsumoApi.Back.Cliente.Model.ClientSaid;
import ConsumoApi.Back.Cliente.Repository.ClientSaidRepository;
import ConsumoApi.Back.Cliente.Repository.ClienteRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClientSaidRepository clienteSaidaRepository;

    public List<ClientEnt> ListAll() {
        return clienteRepository.findAll();
    }

    public List<ClientSaid> ListAllConsultados() {
        return clienteSaidaRepository.findAll();
    }

    public long Count() {
        return clienteRepository.count();
    }

    public ClientEnt save(ClientEnt clientEnt) {
        return clienteRepository.save(clientEnt);
    }

    public ClientSaid SaveSaid(ClientSaid clientSaid){
        return clienteSaidaRepository.save(clientSaid);
    }

    public long CountConsultados(){
        return clienteSaidaRepository.count();
    }

    public List<ClientSaid> SeachIdCarga(String identificadorCarga) {
        return clienteSaidaRepository.findByIdentificadorCarga(identificadorCarga);
    }
    //Todo: organizar lógica e nomes de objetos para facilitar compreensão
    public ClientEnt convertDtoToEntity(ClientEntDTO clientEntDTO) {
        ClientEnt clientEnt = new ClientEnt();
        clientEnt.setNomeCliente(clientEntDTO.getNomeCliente());
        clientEnt.setEmailCliente(clientEntDTO.getEmailCliente());
        clientEnt.setCpfCliente(clientEntDTO.getCpfCliente());
        clientEnt.setDataCriacao(clientEntDTO.getDataCriacao());
        clientEnt.setIdentificadorCarga(clientEntDTO.getIdentificadorCarga());
        return clientEnt;
    }
    //Todo: aguardando informações da API de homologação do banco para validação correta
    public boolean isValidCpf(String cpfCliente) {
        String cpf = cpfCliente.replaceAll("[^0-9]", ""); //retirando caracteres especiais e espaços
        return cpf.length() == 11; //verifica cpf com 11 digitos
    }

    // metodo p upload
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Nenhum arquivo enviado", HttpStatus.BAD_REQUEST);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVReader csvReader = new CSVReader(reader)) {

            // pulando cabeçalho
            String[] header = csvReader.readNext();
            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine.length < 3) {
                    System.err.println("Linha com dados insuficientes: " + String.join(",", nextLine));
                    continue; //pular linha se tiver menos de 3 colunas
                }

                ClientEntDTO clientEntDTO = new ClientEntDTO();

                clientEntDTO.setNomeCliente(nextLine[0].trim());
                clientEntDTO.setEmailCliente(nextLine[1].trim());
                clientEntDTO.setCpfCliente(nextLine[2].trim());
                clientEntDTO.setDataCriacao(LocalDateTime.now());
                clientEntDTO.setIdentificadorCarga(file.getOriginalFilename());

                ClientEnt clientEnt = convertDtoToEntity(clientEntDTO);
                save(clientEnt);
            }

            return ResponseEntity.ok("Arquivo processado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.");
        }
    }


    public ResponseEntity<String> validarETransferirClientes() {
        List<ClientEntDTO> clientesDTO = ListAllClientesDTO();
        StringBuilder logInvalidos = new StringBuilder(); // Para registrar CPF inválidos

        for (ClientEntDTO clientDTO : clientesDTO) {
            if (!isValidCpf(clientDTO.getCpfCliente())) {
                logInvalidos.append("CPF inválido para o cliente: " + clientDTO.getNomeCliente() + ", "+ clientDTO.getCpfCliente() + "\n");
            }
                ClientSaid clientSaid = new ClientSaid();

                //criando uma instância de clientEnt com o ID
                ClientEnt clientEnt = new ClientEnt();
                clientEnt.setId(clientDTO.getId());

                clientSaid.setIdOriginal(clientEnt.getId());
                clientSaid.setNomeCliente(clientDTO.getNomeCliente());
                clientSaid.setEmailCliente(clientDTO.getEmailCliente());
                clientSaid.setCpfCliente(clientDTO.getCpfCliente());
                clientSaid.setDataCriacao(LocalDateTime.now());
                clientSaid.setIdentificadorCarga(clientDTO.getIdentificadorCarga());

                saveToOutputTable(clientSaid);
        }

        if (logInvalidos.length() > 0) {
            //tratando registros inválidos
            System.err.println(logInvalidos.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(logInvalidos.toString());
        }

        return ResponseEntity.ok("Validação concluída!");
    }

    //Retornar os clientes em dto
    private List<ClientEntDTO> ListAllClientesDTO() {
        List<ClientEnt> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertToDto) // Converte para DTO
                .collect(Collectors.toList());
    }

    private ClientEntDTO convertToDto(ClientEnt clientEnt) {
        ClientEntDTO clientEntDTO = new ClientEntDTO();
        clientEntDTO.setId(clientEnt.getId());
        clientEntDTO.setNomeCliente(clientEnt.getNomeCliente());
        clientEntDTO.setEmailCliente(clientEnt.getEmailCliente());
        clientEntDTO.setCpfCliente(clientEnt.getCpfCliente());
        clientEntDTO.setDataCriacao(clientEnt.getDataCriacao());
        clientEntDTO.setIdentificadorCarga(clientEnt.getIdentificadorCarga());
        return clientEntDTO;
    }

    // metodo p salvar na tabela de saida
    private ClientSaid saveToOutputTable(ClientSaid clientSaid) {
        return clienteSaidaRepository.save(clientSaid);
    }
}
