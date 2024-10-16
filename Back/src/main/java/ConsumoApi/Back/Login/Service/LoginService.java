package ConsumoApi.Back.Login.Service;

import ConsumoApi.Back.Login.Model.LoginUser;
import ConsumoApi.Back.Login.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository userRepository;
    //Todo: implementar melhor segurança
    public boolean validateLogin(String username, String password) {
        LoginUser loginUser = userRepository.findByUsername(username);
        return loginUser != null && loginUser.getPassword().equals(password);
    }
}
