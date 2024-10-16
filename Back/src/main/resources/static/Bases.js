var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
// Função para carregar as tabelas criadas no banco de dados
function carregarTabelas() {
    return __awaiter(this, void 0, void 0, function () {
        var response, tabelas, tableList_1, error_1;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 3, , 4]);
                    return [4 /*yield*/, fetch('/dashboard/db-Tables')];
                case 1:
                    response = _a.sent();
                    return [4 /*yield*/, response.json()];
                case 2:
                    tabelas = _a.sent();
                    tableList_1 = document.getElementById('tableList');
                    if (tableList_1) {
                        tabelas.forEach(function (tabela) {
                            var tabelaElement = criarElementoTabela(tabela);
                            tableList_1.appendChild(tabelaElement);
                        });
                    }
                    else {
                        console.error('Elemento tableList não encontrado');
                    }
                    return [3 /*break*/, 4];
                case 3:
                    error_1 = _a.sent();
                    console.error('Erro ao carregar as tabelas:', error_1);
                    return [3 /*break*/, 4];
                case 4: return [2 /*return*/];
            }
        });
    });
}
// Função para criar o elemento HTML para cada tabela
function criarElementoTabela(nomeTabela) {
    var divTabela = document.createElement('div');
    divTabela.classList.add('table-card'); // Classe ajustada para styling
    var nomeElemento = document.createElement('p');
    nomeElemento.textContent = nomeTabela;
    divTabela.appendChild(nomeElemento);
    // Botões de baixar CSV e deletar tabela
    var botaoBaixar = criarBotaoBaixar(nomeTabela);
    var botaoDeletar = criarBotaoDeletar(nomeTabela);
    divTabela.appendChild(botaoBaixar);
    divTabela.appendChild(botaoDeletar);
    return divTabela;
}
function criarBotaoBaixar(nomeTabela) {
    var _this = this;
    var botaoBaixar = document.createElement('button');
    botaoBaixar.textContent = 'Baixar CSV';
    botaoBaixar.addEventListener('click', function () { return __awaiter(_this, void 0, void 0, function () {
        var response, blob, url, a, error_2;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 3, , 4]);
                    return [4 /*yield*/, fetch("/api/tabelas/".concat(nomeTabela, "/baixar"))];
                case 1:
                    response = _a.sent();
                    return [4 /*yield*/, response.blob()];
                case 2:
                    blob = _a.sent();
                    url = window.URL.createObjectURL(blob);
                    a = document.createElement('a');
                    a.href = url;
                    a.download = "".concat(nomeTabela, ".csv");
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                    return [3 /*break*/, 4];
                case 3:
                    error_2 = _a.sent();
                    console.error('Erro ao baixar a tabela:', error_2);
                    return [3 /*break*/, 4];
                case 4: return [2 /*return*/];
            }
        });
    }); });
    return botaoBaixar;
}
// botão de deletar
function criarBotaoDeletar(nomeTabela) {
    var _this = this;
    var botaoDeletar = document.createElement('button');
    botaoDeletar.textContent = 'Deletar Base';
    botaoDeletar.classList.add('delete'); // Classe para estilo
    botaoDeletar.addEventListener('click', function () { return __awaiter(_this, void 0, void 0, function () {
        var response, tabelaElement, error_3;
        var _a;
        return __generator(this, function (_b) {
            switch (_b.label) {
                case 0:
                    _b.trys.push([0, 2, , 3]);
                    return [4 /*yield*/, fetch("/api/tabelas/".concat(nomeTabela), { method: 'DELETE' })];
                case 1:
                    response = _b.sent();
                    if (response.ok) {
                        tabelaElement = document.querySelector(".table-card p:contains(".concat(nomeTabela, ")"));
                        if (tabelaElement) {
                            (_a = tabelaElement.parentElement) === null || _a === void 0 ? void 0 : _a.remove(); // Remove o elemento pai
                        }
                    }
                    return [3 /*break*/, 3];
                case 2:
                    error_3 = _b.sent();
                    console.error('Erro ao deletar a tabela:', error_3);
                    return [3 /*break*/, 3];
                case 3: return [2 /*return*/];
            }
        });
    }); });
    return botaoDeletar;
}
// Função de controle da sidebar
function controlarSidebar() {
    var sidebar = document.getElementById("sidebar");
    var toggleBtn = document.getElementById("toggleBtn");
    if (sidebar && toggleBtn) {
        toggleBtn.addEventListener("click", function () {
            sidebar.classList.toggle("retracted");
        });
    }
    else {
        console.error("Erro: não foi possível encontrar os elementos da sidebar ou do botão de alternância.");
    }
}
// Chamar as funções de inicialização quando a página carregar
window.onload = function () {
    carregarTabelas();
    controlarSidebar();
};
