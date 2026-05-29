// Subclasse 3: EstacaoDeTransferencia - herda de Local
public class EstacaoDeTransferencia extends Local {

    private int numCaminhoes;
    private String turnoFuncionamento;

    public EstacaoDeTransferencia(String id, String nome, String endereco,
                                   int numCaminhoes, String turnoFuncionamento) {
        super(id, nome, endereco);
        this.numCaminhoes = numCaminhoes;
        this.turnoFuncionamento = turnoFuncionamento;
    }

    public int getNumCaminhoes() {
        return numCaminhoes;
    }

    public void setNumCaminhoes(int numCaminhoes) {
        this.numCaminhoes = numCaminhoes;
    }

    public String getTurnoFuncionamento() {
        return turnoFuncionamento;
    }

    public void setTurnoFuncionamento(String turnoFuncionamento) {
        this.turnoFuncionamento = turnoFuncionamento;
    }

    // Implementação do método abstrato (polimorfismo)
    @Override
    public String getTipo() {
        return "ESTACAO DE TRANSFERENCIA";
    }

    // Sobrescrita do método exibirInfo (polimorfismo)
    @Override
    public String exibirInfo() {
        return super.exibirInfo()
                + " | Caminhões: " + numCaminhoes
                + " | Turno: " + turnoFuncionamento;
    }
}
