import java.util.Scanner;

// Classe principal com menu interativo
public class Main {

    private static GrafoRotas grafo = new GrafoRotas();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("  SISTEMA DE ROTAS DE COLETA DE LIXO - RECIFE  ");
        System.out.println("================================================");

        carregarDadosExemplo();

        boolean rodando = true;
        while (rodando) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1 -> cadastrarLocalInterativo();
                case 2 -> cadastrarConexaoInterativa();
                case 3 -> calcularMenorCaminhoInterativo();
                case 4 -> grafo.exibirDistanciaTotal();
                case 5 -> grafo.listarLocais();
                case 6 -> grafo.listarConexoes();
                case 0 -> {
                    System.out.println("\nEncerrando o sistema. Ate logo!");
                    rodando = false;
                }
                default -> System.out.println("!! Opcao invalida.");
            }
        }
        scanner.close();
    }

    // MENU
    private static void exibirMenu() {
        System.out.println("\n---------------------------------");
        System.out.println(" 1 - Cadastrar local");
        System.out.println(" 2 - Cadastrar conexao");
        System.out.println(" 3 - Calcular menor caminho");
        System.out.println(" 4 - Exibir distancia total");
        System.out.println(" 5 - Listar locais cadastrados");
        System.out.println(" 6 - Listar conexoes cadastradas");
        System.out.println(" 0 - Sair");
        System.out.println("---------------------------------");
    }

    // CADASTRAR LOCAL INTERATIVO
    private static void cadastrarLocalInterativo() {
        System.out.println("\nTipo de local:");
        System.out.println("  1 - Ponto de Coleta");
        System.out.println("  2 - Deposito");
        System.out.println("  3 - Estacao de Transferencia");
        int tipo = lerInteiro("Tipo: ");

        System.out.print("ID unico: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine().trim();

        switch (tipo) {
            case 1 -> {
                double cap = lerDouble("Capacidade (kg): ");
                grafo.cadastrarLocal(new PontoDeColeta(id, nome, endereco, cap));
            }
            case 2 -> {
                double area = lerDouble("Area (m2): ");
                System.out.print("Responsavel: ");
                String resp = scanner.nextLine().trim();
                grafo.cadastrarLocal(new Deposito(id, nome, endereco, area, resp));
            }
            case 3 -> {
                int caminhoes = lerInteiro("No de caminhoes: ");
                System.out.print("Turno de funcionamento: ");
                String turno = scanner.nextLine().trim();
                grafo.cadastrarLocal(new EstacaoDeTransferencia(id, nome, endereco, caminhoes, turno));
            }
            default -> System.out.println("!! Tipo invalido.");
        }
    }

    // CADASTRAR CONEXAO INTERATIVA
    private static void cadastrarConexaoInterativa() {
        grafo.listarLocais();
        System.out.print("\nID do local de ORIGEM: ");
        String orig = scanner.nextLine().trim();
        System.out.print("ID do local de DESTINO: ");
        String dest = scanner.nextLine().trim();
        double dist = lerDouble("Distancia (km): ");
        System.out.print("Descricao da via (ex: Av. Caxanga): ");
        String desc = scanner.nextLine().trim();
        grafo.cadastrarConexao(orig, dest, dist, desc);
    }

    // CALCULAR MENOR CAMINHO INTERATIVO
    private static void calcularMenorCaminhoInterativo() {
        grafo.listarLocais();
        System.out.print("\nID do local de ORIGEM: ");
        String orig = scanner.nextLine().trim();
        System.out.print("ID do local de DESTINO: ");
        String dest = scanner.nextLine().trim();
        grafo.calcularMenorCaminho(orig, dest);
    }

    // AUXILIARES
    private static int lerInteiro(String prompt) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(prompt);
                valor = Integer.parseInt(scanner.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("!! Digite um numero inteiro valido.");
            }
        }
        return valor;
    }

    private static double lerDouble(String prompt) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(prompt);
                valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("!! Digite um numero decimal valido (use . ou ,).");
            }
        }
        return valor;
    }

    // DADOS DE EXEMPLO (bairros do Recife)
    private static void carregarDadosExemplo() {
        System.out.println("\n[Carregando dados de exemplo do Recife...]\n");

        // Pontos de coleta
        grafo.cadastrarLocal(new PontoDeColeta("PC01", "Boa Viagem",
                "Av. Boa Viagem, 500 - Boa Viagem", 800));
        grafo.cadastrarLocal(new PontoDeColeta("PC02", "Casa Amarela",
                "R. Castro Alves, 12 - Casa Amarela", 650));
        grafo.cadastrarLocal(new PontoDeColeta("PC03", "Afogados",
                "R. Padre Roma, 30 - Afogados", 700));
        grafo.cadastrarLocal(new PontoDeColeta("PC04", "Boa Vista",
                "R. do Hospicio, 5 - Boa Vista", 500));

        // Depositos
        grafo.cadastrarLocal(new Deposito("DEP01", "Deposito Central Ibura",
                "Av. General San Martin, 600 - Ibura", 2000, "Joao Silva"));
        grafo.cadastrarLocal(new Deposito("DEP02", "Deposito Norte",
                "Estrada do Arraial, 1200 - Casa Amarela", 1500, "Maria Souza"));

        // Estacoes de transferencia
        grafo.cadastrarLocal(new EstacaoDeTransferencia("ET01", "Estacao Tejipió",
                "Av. Caxanga, 800 - Tejipió", 8, "Manha/Tarde"));
        grafo.cadastrarLocal(new EstacaoDeTransferencia("ET02", "Estacao Dois Irmaos",
                "Estr. dos Dois Irmaos, 50 - Dois Irmaos", 5, "Integral"));

        // Conexoes
        grafo.cadastrarConexao("PC01", "ET01", 6.5, "Av. Herculano Bandeira");
        grafo.cadastrarConexao("PC02", "ET02", 3.2, "Estrada dos Dois Irmaos");
        grafo.cadastrarConexao("PC03", "ET01", 4.0, "Av. Caxanga");
        grafo.cadastrarConexao("PC04", "ET01", 5.1, "Av. Agamenon Magalhaes");
        grafo.cadastrarConexao("ET01", "DEP01", 8.3, "BR-101 Sul");
        grafo.cadastrarConexao("ET02", "DEP02", 2.8, "Estrada do Arraial");
        grafo.cadastrarConexao("PC02", "PC04", 4.5, "Av. Norte");
        grafo.cadastrarConexao("DEP01", "DEP02", 12.0, "BR-408");

        System.out.println("\n[Dados de exemplo carregados com sucesso!]");
    }
}
