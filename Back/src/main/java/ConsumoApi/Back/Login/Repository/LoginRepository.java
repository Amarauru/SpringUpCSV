package ConsumoApi.Back.Login.Repository;

import ConsumoApi.Back.Login.Model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginUser, Long> {
    LoginUser findByUsername(String username);

}
