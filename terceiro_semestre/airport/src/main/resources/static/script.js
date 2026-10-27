const API = "http://localhost:8080/companhias";

const $ = (id) => document.getElementById(id);

function mostrar(status, dados) {
  $("status").textContent = `Status: ${status}`;
  $("saida").textContent = dados == null ? "" : JSON.stringify(dados, null, 2);
}

async function requisicao(metodo, url, corpo) {
  const opcoes = { method: metodo, headers: {} };
  if (corpo !== undefined) {
    opcoes.headers["Content-Type"] = "application/json";
    opcoes.body = JSON.stringify(corpo);
  }

  try {
    const resp = await fetch(url, opcoes);
    const texto = await resp.text();
    let dados = texto;
    try {
      dados = texto ? JSON.parse(texto) : null;
    } catch (e) {
      // resposta não é JSON, mostra o texto puro
    }
    mostrar(`${resp.status} ${resp.statusText}`, dados);
  } catch (erro) {
    mostrar("erro de rede", erro.message);
  }
}

function corpoDoFormulario() {
  return {
    nome: $("nome").value,
    quantidadePassageiros: Number($("quantidade").value)
  };
}

function idDigitado() {
  return $("id").value.trim();
}

$("btnGet").onclick = () => requisicao("GET", API);
$("btnGetId").onclick = () => requisicao("GET", `${API}/${idDigitado()}`);
$("btnPut").onclick = () => requisicao("PUT", `${API}/${idDigitado()}`, corpoDoFormulario());
$("btnDelete").onclick = () => requisicao("DELETE", `${API}/${idDigitado()}`);

$("form").onsubmit = (e) => {
  e.preventDefault();
  requisicao("POST", API, corpoDoFormulario());
};
