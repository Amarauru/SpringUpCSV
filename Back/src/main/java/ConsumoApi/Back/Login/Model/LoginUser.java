package ConsumoApi.Back.Login.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class LoginUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}