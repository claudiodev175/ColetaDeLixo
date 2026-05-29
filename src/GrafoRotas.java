import java.util.*;

// Classe responsável por gerenciar locais, conexões e calcular rotas
public class GrafoRotas {

    // Mapa de locais cadastrados: ID -> Local
    private Map<String, Local> locais;

    // Lista de todas as conexões
    private List<Conexao> conexoes;

    public GrafoRotas() {
        this.locais = new LinkedHashMap<>();
        this.conexoes = new ArrayList<>();
    }

    // ── CADASTRAR LOCAL ──────────────────────────────────────────────
    public void cadastrarLocal(Local local) {
        if (locais.containsKey(local.getId())) {
            System.out.println("⚠  Local com ID '" + local.getId() + "' já cadastrado.");
            return;
        }
        locais.put(local.getId(), local);
        System.out.println("✔  Local cadastrado: " + local.exibirInfo());
    }

    // ── CADASTRAR CONEXÃO ────────────────────────────────────────────
    public void cadastrarConexao(String idOrigem, String idDestino,
                                  double distanciaKm, String descricao) {
        Local origem = locais.get(idOrigem);
        Local destino = locais.get(idDestino);

        if (origem == null || destino == null) {
            System.out.println("⚠  Um ou ambos os locais não foram encontrados.");
            return;
        }

        Conexao c = new Conexao(origem, destino, distanciaKm, descricao);
        conexoes.add(c);
        System.out.println("✔  Conexão cadastrada: " + c);
    }

    // ── LISTAR LOCAIS ────────────────────────────────────────────────
    public void listarLocais() {
        System.out.println("\n========== LOCAIS CADASTRADOS ==========");
        if (locais.isEmpty()) {
            System.out.println("Nenhum local cadastrado.");
            return;
        }
        // Polimorfismo em ação: cada tipo de Local exibe suas informações de forma diferente
        for (Local l : locais.values()) {
            System.out.println(l.exibirInfo());
        }
        System.out.println("Total: " + locais.size() + " local(is).");
    }

    // ── LISTAR CONEXÕES ──────────────────────────────────────────────
    public void listarConexoes() {
        System.out.println("\n========== CONEXOES CADASTRADAS ==========");
        if (conexoes.isEmpty()) {
            System.out.println("Nenhuma conexão cadastrada.");
            return;
        }
        for (Conexao c : conexoes) {
            System.out.println(c);
        }
        System.out.println("Total: " + conexoes.size() + " conexao(oes).");
    }

    // ── CALCULAR MENOR CAMINHO (Dijkstra) ────────────────────────────
    public void calcularMenorCaminho(String idOrigem, String idDestino) {
        System.out.println("\n========== CALCULO DE MENOR CAMINHO ==========");

        if (!locais.containsKey(idOrigem) || !locais.containsKey(idDestino)) {
            System.out.println("⚠  ID(s) invalido(s). Verifique os locais cadastrados.");
            return;
        }

        // Distâncias mínimas conhecidas
        Map<String, Double> distMin = new HashMap<>();
        // Predecessores no caminho ótimo
        Map<String, String> anterior = new HashMap<>();
        // Fila de prioridade: (distância, id)
        PriorityQueue<double[]> fila = new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));

        // Inicialização
        for (String id : locais.keySet()) {
            distMin.put(id, Double.MAX_VALUE);
        }
        distMin.put(idOrigem, 0.0);
        fila.offer(new double[]{0.0, idOrigem.hashCode()});
        Map<Integer, String> hashParaId = new HashMap<>();
        for (String id : locais.keySet()) {
            hashParaId.put(id.hashCode(), id);
        }

        // Usamos uma fila simples com string para evitar colisões de hash
        PriorityQueue<String[]> filaNova = new PriorityQueue<>(
                Comparator.comparingDouble(a -> Double.parseDouble(a[1]))
        );
        filaNova.offer(new String[]{idOrigem, "0.0"});

        Set<String> visitados = new HashSet<>();

        while (!filaNova.isEmpty()) {
            String[] atual = filaNova.poll();
            String idAtual = atual[0];

            if (visitados.contains(idAtual)) continue;
            visitados.add(idAtual);

            if (idAtual.equals(idDestino)) break;

            // Percorre vizinhos
            for (Conexao c : conexoes) {
                String vizinhoId = null;
                if (c.getOrigem().getId().equals(idAtual)) {
                    vizinhoId = c.getDestino().getId();
                } else if (c.getDestino().getId().equals(idAtual)) {
                    // Grafo não-dirigido: permite voltar
                    vizinhoId = c.getOrigem().getId();
                }

                if (vizinhoId != null && !visitados.contains(vizinhoId)) {
                    double novaDist = distMin.get(idAtual) + c.getDistanciaKm();
                    if (novaDist < distMin.get(vizinhoId)) {
                        distMin.put(vizinhoId, novaDist);
                        anterior.put(vizinhoId, idAtual);
                        filaNova.offer(new String[]{vizinhoId, String.valueOf(novaDist)});
                    }
                }
            }
        }

        // Reconstrói o caminho
        if (distMin.get(idDestino) == Double.MAX_VALUE) {
            System.out.println("⚠  Não existe caminho entre "
                    + locais.get(idOrigem).getNome()
                    + " e " + locais.get(idDestino).getNome());
            return;
        }

        List<String> caminho = new ArrayList<>();
        String passo = idDestino;
        while (passo != null) {
            caminho.add(0, passo);
            passo = anterior.get(passo);
        }

        System.out.println("Origem : " + locais.get(idOrigem).getNome());
        System.out.println("Destino: " + locais.get(idDestino).getNome());
        System.out.print("Rota   : ");
        for (int i = 0; i < caminho.size(); i++) {
            System.out.print(locais.get(caminho.get(i)).getNome());
            if (i < caminho.size() - 1) System.out.print(" → ");
        }
        System.out.printf("%n");
        System.out.printf("Distancia total: %.2f km%n", distMin.get(idDestino));
    }

    // ── EXIBIR DISTÂNCIA TOTAL DE TODAS AS CONEXÕES ──────────────────
    public void exibirDistanciaTotal() {
        System.out.println("\n========== DISTÂNCIA TOTAL DA REDE ==========");
        double total = 0;
        for (Conexao c : conexoes) {
            total += c.getDistanciaKm();
        }
        System.out.printf("Soma de todas as conexoes: %.2f km%n", total);
    }
}
