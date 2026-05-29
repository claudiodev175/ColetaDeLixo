// Classe que representa a conexão (aresta) entre dois locais
public class Conexao {

    private Local origem;
    private Local destino;
    private double distanciaKm;
    private String descricao;

    public Conexao(Local origem, Local destino, double distanciaKm, String descricao) {
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.descricao = descricao;
    }

    public Local getOrigem() {
        return origem;
    }

    public Local getDestino() {
        return destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Conexao: " + origem.getNome()
                + " --> " + destino.getNome()
                + " | Distancia: " + distanciaKm + " km"
                + " | Via: " + descricao;
    }
}
