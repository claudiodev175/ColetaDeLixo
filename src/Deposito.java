// Subclasse 2: Deposito - herda de Local
public class Deposito extends Local {

    private double areaM2;
    private String responsavel;

    public Deposito(String id, String nome, String endereco, double areaM2, String responsavel) {
        super(id, nome, endereco);
        this.areaM2 = areaM2;
        this.responsavel = responsavel;
    }

    public double getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(double areaM2) {
        this.areaM2 = areaM2;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    // Implementação do método abstrato (polimorfismo)
    @Override
    public String getTipo() {
        return "DEPOSITO";
    }

    // Sobrescrita do método exibirInfo (polimorfismo)
    @Override
    public String exibirInfo() {
        return super.exibirInfo()
                + " | Area: " + areaM2 + " m2"
                + " | Responsavel: " + responsavel;
    }
}
