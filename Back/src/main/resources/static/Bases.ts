interface Tabela {
    nome: string;
    data: string; 
}

//carregar as cargas
async function carregarTabelas(): Promise<void> {
    try {
        const response = await fetch('/dashboard/db-Tables');
        const tabelas: Tabela[] = await response.json(); //trabalhar o retorno pra ser um objeto com as informações

        const tableBody = document.getElementById('tableBody');

        if (tableBody) {
            tabelas.forEach((tabela) => {
                const row = criarLinhaTabela(tabela);
                tableBody.appendChild(row);
            });
        } else {
            console.error('Elemento tableBody não encontrado');
        }
    } catch (error) {
        console.error('Erro ao carregar as tabelas:', error);
    }
}

function criarLinhaTabela(tabela: Tabela): HTMLTableRowElement {
    const row = document.createElement('tr');

    const nomeTd = document.createElement('td');
    nomeTd.textContent = tabela.nome;
    row.appendChild(nomeTd);

    const dataTd = document.createElement('td');
    dataTd.textContent = tabela.data;
    row.appendChild(dataTd);

    const acoesTd = document.createElement('td');
    const divAcoes = document.createElement('div');
    divAcoes.classList.add('table-icons');
    
    const baixarIcon = document.createElement('i');
    baixarIcon.classList.add('ph', 'ph-download-simple');
    divAcoes.appendChild(baixarIcon);

    const deletarIcon = document.createElement('i');
    deletarIcon.classList.add('ph', 'ph-trash');
    divAcoes.appendChild(deletarIcon);

    acoesTd.appendChild(divAcoes);
    row.appendChild(acoesTd);

    return row;
}


function controlarSidebar(): void {
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


window.onload = function (): void {
    carregarTabelas();
    controlarSidebar();
};
