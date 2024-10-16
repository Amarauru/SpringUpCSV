package ConsumoApi.Back.Login.Controller;

import ConsumoApi.Back.Login.Model.LoginRequest;
import ConsumoApi.Back.Login.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        boolean isValid = loginService.validateLogin(loginRequest.getUsername(), loginRequest.getPassword());

        if (isValid) {
            //caminho de teste só para chegar no ts
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }
}
