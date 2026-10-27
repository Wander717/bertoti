// Descreve cada entidade: caminho da API e campos do formulário (também usados nas colunas da tabela)
const entidades = {
  companhias: {
    url: "/companhias",
    campos: [
      { campo: "nome", rotulo: "Nome", tipo: "text" },
      { campo: "quantidadePassageiros", rotulo: "Quantidade de passageiros", tipo: "number" }
    ]
  },
  avioes: {
    url: "/avioes",
    campos: [
      { campo: "nome", rotulo: "Nome", tipo: "text" },
      { campo: "prefixo", rotulo: "Prefixo", tipo: "text" },
      { campo: "companhia", rotulo: "Companhia", tipo: "select", origem: "companhias" },
      { campo: "peso", rotulo: "Peso (kg)", tipo: "number" },
      { campo: "anoFabricacao", rotulo: "Ano de fabricação", tipo: "number" },
      { campo: "capacidadePassageiros", rotulo: "Capacidade de passageiros", tipo: "number" }
    ]
  },
  passageiros: {
    url: "/passageiros",
    campos: [
      { campo: "nome", rotulo: "Nome", tipo: "text" },
      { campo: "cpf", rotulo: "CPF", tipo: "text" },
      { campo: "passagem", rotulo: "Código da passagem", tipo: "text" },
      { campo: "peso", rotulo: "Peso (kg)", tipo: "number" },
      { campo: "companhia", rotulo: "Companhia", tipo: "select", origem: "companhias" }
    ]
  }
};

// Última lista recebida da API para cada entidade
const dados = {};

const $ = (id) => document.getElementById(id);

function el(tag, texto, propriedades = {}) {
  const e = document.createElement(tag);
  if (texto !== undefined) e.textContent = texto;
  Object.assign(e, propriedades);
  return e;
}

async function api(metodo, caminho, corpo) {
  const opcoes = { method: metodo, headers: {} };
  if (corpo !== undefined) {
    opcoes.headers["Content-Type"] = "application/json";
    opcoes.body = JSON.stringify(corpo);
  }
  try {
    const resp = await fetch(caminho, opcoes);
    return { ok: resp.ok, status: resp.status, texto: await resp.text() };
  } catch (erro) {
    return { ok: false, status: 0, texto: erro.message };
  }
}

function descreverErro(status, texto) {
  if (status === 0) return "Erro de rede: o servidor não respondeu.";
  if (status === 400) return "Dados inválidos. Verifique os campos e a companhia selecionada.";
  if (status === 404) return "Registro não encontrado. A lista será atualizada.";
  if (status === 409) return texto;
  return `Erro ${status}: ${texto}`;
}

function mensagem(nome, texto, erro = false) {
  const p = $(`${nome}-msg`);
  p.textContent = texto;
  p.className = erro ? "msg erro" : "msg";
}

function montarSecao(nome) {
  const form = $(`form-${nome}`);
  form.appendChild(el("input", undefined, { type: "hidden", id: `${nome}-id` }));

  for (const c of entidades[nome].campos) {
    const input = c.tipo === "select"
      ? el("select", undefined, { id: `${nome}-${c.campo}`, required: true })
      : el("input", undefined, { type: c.tipo, id: `${nome}-${c.campo}`, required: true, step: "any" });
    form.appendChild(el("label", c.rotulo)).appendChild(input);
  }

  const botoes = el("div", undefined, { className: "botoes" });
  botoes.append(
    el("button", "Salvar", { type: "submit", id: `${nome}-salvar` }),
    el("button", "Limpar", { type: "button", onclick: () => limpar(nome) })
  );
  form.appendChild(botoes);
  form.onsubmit = (e) => {
    e.preventDefault();
    salvar(nome);
  };
}

function preencherSelects(nome) {
  for (const c of entidades[nome].campos.filter((c) => c.tipo === "select")) {
    const select = $(`${nome}-${c.campo}`);
    const atual = select.value;
    const opcoes = (dados[c.origem] || []).map((item) => el("option", item.nome, { value: item.id }));
    select.replaceChildren(el("option", "Selecione...", { value: "" }), ...opcoes);
    select.value = atual;
  }
}

function formatar(campo, valor) {
  if (campo.tipo === "select") return valor ? valor.nome : "—";
  return valor ?? "—";
}

function desenharLista(nome) {
  const lista = $(`${nome}-lista`);
  const itens = dados[nome] || [];
  if (!itens.length) {
    lista.replaceChildren(el("p", "Nenhum registro cadastrado."));
    return;
  }

  const cabecalho = el("tr");
  for (const c of entidades[nome].campos) cabecalho.appendChild(el("th", c.rotulo));
  cabecalho.appendChild(el("th", "Ações"));
  const thead = el("thead");
  thead.appendChild(cabecalho);

  const tbody = el("tbody");
  for (const item of itens) {
    const linha = el("tr");
    for (const c of entidades[nome].campos) linha.appendChild(el("td", formatar(c, item[c.campo])));

    const acoes = el("td");
    acoes.append(
      el("button", "Editar", { type: "button", onclick: () => editar(nome, item) }),
      el("button", "Excluir", { type: "button", className: "perigo", onclick: () => excluir(nome, item) })
    );
    linha.appendChild(acoes);
    tbody.appendChild(linha);
  }

  const tabela = el("table");
  tabela.append(thead, tbody);
  lista.replaceChildren(tabela);
}

function limpar(nome) {
  $(`form-${nome}`).reset();
  $(`${nome}-id`).value = "";
  $(`${nome}-salvar`).textContent = "Salvar";
}

function editar(nome, item) {
  limpar(nome);
  $(`${nome}-id`).value = item.id;
  for (const c of entidades[nome].campos) {
    const valor = c.tipo === "select" ? (item[c.campo] ? item[c.campo].id : "") : item[c.campo];
    $(`${nome}-${c.campo}`).value = valor;
  }
  $(`${nome}-salvar`).textContent = "Atualizar";
  mensagem(nome, "");
}

async function salvar(nome) {
  const ent = entidades[nome];
  const id = $(`${nome}-id`).value;

  const corpo = {};
  for (const c of ent.campos) {
    const valor = $(`${nome}-${c.campo}`).value;
    if (c.tipo === "select") corpo[c.campo] = { id: valor };
    else if (c.tipo === "number") corpo[c.campo] = Number(valor);
    else corpo[c.campo] = valor.trim();
  }

  const resp = id
    ? await api("PUT", `${ent.url}/${id}`, corpo)
    : await api("POST", ent.url, corpo);

  if (!resp.ok) return mensagem(nome, descreverErro(resp.status, resp.texto), true);
  limpar(nome);
  mensagem(nome, id ? "Registro atualizado." : "Registro criado.");
  await carregarTudo();
}

async function excluir(nome, item) {
  const aviso = nome === "companhias"
    ? "\n\nOs aviões e passageiros desta companhia também serão apagados."
    : "";
  if (!confirm(`Apagar "${item.nome}"?${aviso}`)) return;

  const resp = await api("DELETE", `${entidades[nome].url}/${item.id}`);
  if (!resp.ok) return mensagem(nome, descreverErro(resp.status, resp.texto), true);
  if ($(`${nome}-id`).value === item.id) limpar(nome);
  mensagem(nome, "Registro apagado.");
  await carregarTudo();
}

// Recarrega as três listas, pois apagar uma companhia afeta aviões e passageiros
async function carregarTudo() {
  for (const nome of Object.keys(entidades)) {
    const resp = await api("GET", entidades[nome].url);
    if (!resp.ok) {
      mensagem(nome, descreverErro(resp.status, resp.texto), true);
      continue;
    }
    dados[nome] = JSON.parse(resp.texto);
  }
  for (const nome of Object.keys(entidades)) {
    preencherSelects(nome);
    desenharLista(nome);
  }
}

for (const nome of Object.keys(entidades)) montarSecao(nome);
carregarTudo();
