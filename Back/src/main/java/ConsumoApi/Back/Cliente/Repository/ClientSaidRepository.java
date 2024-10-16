package ConsumoApi.Back.Cliente.Repository;
import ConsumoApi.Back.Cliente.Model.ClientSaid;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientSaidRepository extends JpaRepository<ClientSaid, Long> {

    List<ClientSaid> findByIdentificadorCarga(String identificadorCarga);

}