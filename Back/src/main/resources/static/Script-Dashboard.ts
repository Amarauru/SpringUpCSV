interface DashboardData {
  totalClientes: number;
  totalConsultas: number;
}

interface User {
  username: string;
}

async function loadTotalClientes(): Promise<void> {
  try {
    const response = await fetch('/dashboard/Count');
    const data = await response.json();


    const totalClientes = data.totalClientes;

    const totalClientesElement = document.getElementById('totalClientes');
    if (totalClientesElement) {
      totalClientesElement.textContent = totalClientes.toString();
    }
  } catch (error) {
    console.error('Erro ao carregar total de clientes:', error);
  }
}


// Função para buscar o total de consultas
async function loadTotalConsultas(): Promise<void> {
  try {
    const response = await fetch('/dashboard/clientes-consultados');
    const data = await response.json();

    const totalConsultas = data.totalConsultados
    
    const totalConsultasElement = document.getElementById('totalConsultas');
    if (totalConsultasElement) {
      totalConsultasElement.textContent = totalConsultas.toString();
    }
  } catch (error) {
    console.error('Erro ao carregar total de consultas:', error);
  }
}

// Função para carregar informações do usuário logado
function loadUserInfo(): void {
  let user: User = { username: "Usuario" };
  let avatarInitial = user.username.charAt(0).toUpperCase();

  let usernameElement = document.getElementById('username');
  let userAvatarElement = document.getElementById('userAvatar');

  if (usernameElement && userAvatarElement) {
    usernameElement.textContent = user.username;
    userAvatarElement.textContent = avatarInitial;
  }
}

// Função de controle da sidebar
//Todo: fazer que a sidebar seja indedpendente das telas, de forma que não precise gera-la novamente
function SideBarControll(): void {

  const sidebar = document.getElementById("sidebar");
  const toggleBtn = document.getElementById("toggleBtn");

  if (sidebar && toggleBtn) {
    toggleBtn.addEventListener("click", () => {
      sidebar.classList.toggle("retracted");
    });
  } else {
    console.error("Erro: não foi possível encontrar os elementos da sidebar ou do botão de alternância.");
  }
}

//chamando todas as funções quando a tela carregar
window.onload = function () {
  loadTotalClientes();
  loadTotalConsultas();
  loadUserInfo();
  SideBarControll();
};
