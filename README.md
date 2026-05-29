# 🗑️ Sistema de Rotas de Coleta de Lixo — Recife/PE

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos**, modelando computacionalmente a rede de coleta de lixo urbano da cidade do Recife, com cadastro de locais, conexões e cálculo do menor caminho entre pontos.

---

## 👥 Integrantes do Grupo 1

| Nome |
|------|
| Cauã Fernandes Alves Martin |
| Camila Dayane Paes do Nascimento |
| Cláudio Vinícius Coelho Barros |
| Júlio César de Lucena Marques |
| Letícia Monteiro Tavares |
| Mateus Machado Ramos |

---

## 📋 Descrição do Problema

A Prefeitura do Recife enfrenta desafios na eficiência das rotas de coleta de lixo urbano. O percurso entre pontos de coleta, depósitos e estações de transferência nem sempre é otimizado, gerando custos desnecessários e atrasos no serviço.

Este sistema permite:
- Cadastrar diferentes tipos de locais
- Registrar conexões entre locais com suas distâncias (km)
- Calcular o **menor caminho** entre dois pontos via algoritmo de **Dijkstra**
- Exibir a distância total da rede cadastrada
- Listar todos os locais e conexões registrados

---

## 🏗️ Organização das Classes
Local  (superclasse abstrata)
├── PontoDeColeta
├── Deposito
└── EstacaoDeTransferencia
Conexao         → aresta do grafo (liga dois Locais)
GrafoRotas      → gerencia locais/conexões + algoritmo Dijkstra
Main            → ponto de entrada + menu interativo

| Arquivo | Papel | Descrição |
|---|---|---|
| `Local.java` | Superclasse abstrata | Define `id`, `nome`, `endereco`. Método abstrato `getTipo()` e `exibirInfo()` |
| `PontoDeColeta.java` | Subclasse de Local | Adiciona `capacidadeKg` e status ativo/inativo |
| `Deposito.java` | Subclasse de Local | Adiciona `areaM2` e nome do responsável |
| `EstacaoDeTransferencia.java` | Subclasse de Local | Adiciona `numCaminhoes` e `turnoFuncionamento` |
| `Conexao.java` | Aresta do grafo | Liga dois `Local` com `distanciaKm` e descrição da via |
| `GrafoRotas.java` | Gerenciador + Dijkstra | Cadastra locais/conexões e calcula o menor caminho |
| `Main.java` | Ponto de entrada | Menu interativo e dados de exemplo do Recife |

---

## ✅ Requisitos de POO Atendidos

**Herança**
As três subclasses estendem a superclasse abstrata `Local`, reaproveitando `id`, `nome` e `endereco` via `super()` no construtor.

**Encapsulamento**
Todos os atributos são `private`, com acesso exclusivo por getters e setters públicos.

**Polimorfismo**
O método `getTipo()` é `abstract` em `Local` e implementado diferentemente em cada subclasse. O método `exibirInfo()` é sobrescrito com `@Override` em cada subclasse, exibindo informações específicas do tipo de local.

---

## ▶️ Como Executar

### Pré-requisitos
- JDK 11 ou superior instalado
- Variável de ambiente `JAVA_HOME` configurada

### Passo a passo

**1. Clone o repositório**
```bash
git clone https://github.com/claudiodev175/ColetaDeLixo
cd ColetaDeLixo
```

**2. Entre na pasta com os arquivos**
```bash
cd src
```

**3. Compile**
```bash
javac *.java
```

**4. Execute**
```bash
java Main
```

> 💡 Se houver problema com caracteres especiais no Windows, execute `chcp 65001` antes de compilar.

---

## 🗺️ Menu do Sistema

1 - Cadastrar local
2 - Cadastrar conexão
3 - Calcular menor caminho
4 - Exibir distância total
5 - Listar locais cadastrados
6 - Listar conexões cadastradas
0 - Sair

O sistema já carrega automaticamente **8 locais** e **8 conexões** baseados em bairros reais do Recife como exemplo.

---

## 🧠 Algoritmo

O cálculo do menor caminho utiliza o **Algoritmo de Dijkstra**, que encontra o caminho de menor custo (distância) entre dois vértices em um grafo com pesos positivos. O grafo é **não-dirigido**, ou seja, as conexões funcionam nos dois sentidos.
