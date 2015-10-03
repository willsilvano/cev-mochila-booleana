

import java.util.Comparator;

public class ComparadorFitness implements Comparator<Individuo> {

    @Override
    public int compare(Individuo i1, Individuo i2) {
        if (i1.getFitness() > i2.getFitness()) {
            return -1;
        } else if (i1.getFitness() < i2.getFitness()) {
            return 1;
        } else {
            return 0;
        }
    }
}
