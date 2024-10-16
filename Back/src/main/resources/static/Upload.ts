
function SideBarCtrl(): void {
  const sidebar = document.getElementById("sidebar");
  const toggleBtn = document.getElementById("toggleBtn");

  if (toggleBtn && sidebar) {
      toggleBtn.addEventListener("click", () => {
          sidebar.classList.toggle("retracted");
      });
  } else {
      console.error("Erro: Sidebar ou botão de alternância não encontrados.");
  }
}

//enviando csv
function handleFileUpload(): void {
  const fileInput = document.getElementById("fileInput") as HTMLInputElement;
  const uploadBtn = document.getElementById("uploadBtn") as HTMLButtonElement;

  uploadBtn.addEventListener("click", async () => {
      const file = fileInput?.files?.[0];

      if (file) {
          const formData = new FormData();
          formData.append("file", file);

          try {
              console.log("Enviando arquivo...");
              const response = await fetch("/dashboard/upload", {
                  method: "POST",
                  body: formData,
              });

              if (response.ok) {
                  console.log("Arquivo enviado com sucesso!");
                  alert("Arquivo Enviado com Sucesso!")
              } else {
                  console.error("Erro ao enviar o arquivo: ", response.statusText);
              }
          } catch (error) {
              console.error("Erro ao realizar o upload:", error);
          }
      } else {
          console.error("Nenhum arquivo selecionado.");
      }
  });
}

window.onload = function () {
  SideBarCtrl();
  handleFileUpload();
};
