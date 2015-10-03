
public class Artigo {
    
    private String nome;
    private Double volume;
    private Double peso;
    private Double valor;

    public Artigo() {
    }

    public Artigo(String nome, Double volume, Double peso, Double valor) {
        this.nome = nome;
        this.volume = volume;
        this.peso = peso;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
