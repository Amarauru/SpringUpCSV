package ConsumoApi.Back.Cliente.Repository;

import ConsumoApi.Back.Cliente.Model.ClientEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClientEnt, Long> {
}