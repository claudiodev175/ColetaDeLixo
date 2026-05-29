// Superclasse abstrata que representa qualquer local no sistema
public abstract class Local {

    // Encapsulamento: atributos privados
    private String id;
    private String nome;
    private String endereco;

    // Construtor da superclasse
    public Local(String id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters e Setters (encapsulamento)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Método abstrato - polimorfismo obrigatório nas subclasses
    public abstract String getTipo();

    // Método polimórfico com comportamento padrão (pode ser sobrescrito)
    public String exibirInfo() {
        return "[" + getTipo() + "] ID: " + id + " | Nome: " + nome + " | Endereco: " + endereco;
    }

    @Override
    public String toString() {
        return exibirInfo();
    }
}
