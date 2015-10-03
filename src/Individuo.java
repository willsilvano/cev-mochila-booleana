
import java.util.ArrayList;
import java.util.List;

public class Individuo {

    private List<Integer> cromossomo = new ArrayList<>();
    private Double valor;
    private Double volume;
    private Double peso;
    private Double fitness;

    public Individuo() {
    }

    public Individuo(List<Integer> cromossomo) {
        this.cromossomo = cromossomo;
    }
    
    public List<Integer> getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List<Integer> cromossomo) {
        this.cromossomo = cromossomo;
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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
    
    public String toString() {
        String saida = "";
        saida += "Cromossomo = "+cromossomo.toString()+"\n";
        saida += "Peso = "+peso+"\n";
        saida += "Volume = "+volume+"\n";
        saida += "Valor = "+valor+"\n";
        saida += "Fitness = "+fitness+"\n";
        return saida;
    }
}
