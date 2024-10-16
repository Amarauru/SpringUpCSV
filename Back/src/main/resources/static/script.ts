document.getElementById('login-form')?.addEventListener('submit', function(event: Event) {
    event.preventDefault();

    const usernameInput = document.getElementById('username') as HTMLInputElement;
    const passwordInput = document.getElementById('password') as HTMLInputElement;

    const username: string = usernameInput.value;
    const password: string = passwordInput.value;

    login(username, password);
});


async function login(username: string, password: string): Promise<void> {
    try {
        await fetch("http://localhost:8080/api/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ username,password }),
        }).then((response) => {
          if (response.ok) {
            alert("Login bem-sucedido!");
            window.location.href = "/dashboard.html";
          } else {
            alert("Erro ao fazer login. Verifique suas credenciais.");
          }
        });
      } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Ocorreu um erro ao tentar fazer login.");
      }
}
