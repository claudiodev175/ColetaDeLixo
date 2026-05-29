// Subclasse 1: PontoDeColeta - herda de Local
public class PontoDeColeta extends Local {

    // Atributo específico desta subclasse
    private double capacidadeKg;
    private boolean ativo;

    public PontoDeColeta(String id, String nome, String endereco, double capacidadeKg) {
        super(id, nome, endereco); // Chama o construtor da superclasse
        this.capacidadeKg = capacidadeKg;
        this.ativo = true;
    }

    public double getCapacidadeKg() {
        return capacidadeKg;
    }

    public void setCapacidadeKg(double capacidadeKg) {
        this.capacidadeKg = capacidadeKg;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Implementação do método abstrato (polimorfismo)
    @Override
    public String getTipo() {
        return "PONTO DE COLETA";
    }

    // Sobrescrita do método exibirInfo (polimorfismo)
    @Override
    public String exibirInfo() {
        return super.exibirInfo()
                + " | Capacidade: " + capacidadeKg + " kg"
                + " | Status: " + (ativo ? "Ativo" : "Inativo");
    }
}
