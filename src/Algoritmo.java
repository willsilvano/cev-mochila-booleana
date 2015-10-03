

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Algoritmo {

    private int numeroIndividuos;
    private int numeroEpocas;
    private int quantidadeSelecaoPais;
    private int porcentagemMutacao;
    
    private static final int QUANTIDADE_ITENS = 25;
    private static final int CAPACIDADE_VOLUME = 125;
    private static final int CAPACIDADE_PESO = 125;

    private List<Individuo> populacaoIndividuos;
    private List<Artigo> artigos;
    private List<Double> listaValoresGrafico;
    
    public Algoritmo() {}
    
    public void executa() {
        
        listaValoresGrafico = new ArrayList<>();

        inicializaArtigos();
        inicializaPopulacao();
        avaliaPopulacao();

        int numEpoca = 1;
        do {

            selecionaPais();
            recombina();
            mutacao();
            avaliaPopulacao();
            
            numEpoca++;

        } while (numEpoca <= numeroEpocas);
    }

    public Individuo selecionaMelhorResultado() {
        List<Individuo> populacaoOrdenada = new ArrayList<>(populacaoIndividuos);

        Collections.sort(populacaoOrdenada, new ComparadorFitness());
        
        return populacaoOrdenada.get(0);
    }
    
    private void inicializaArtigos() {
        artigos = new ArrayList<>();
        artigos.add(new Artigo("A", 6.0, 2.0, 5.0));
        artigos.add(new Artigo("B", 2.0, 9.0, 7.0));
        artigos.add(new Artigo("C", 1.0, 9.0, 8.0));
        artigos.add(new Artigo("D", 8.0, 7.0, 9.0));
        artigos.add(new Artigo("E", 2.0, 2.0, 2.0));
        artigos.add(new Artigo("F", 3.0, 2.0, 6.0));
        artigos.add(new Artigo("G", 5.0, 3.0, 2.0));
        artigos.add(new Artigo("H", 9.0, 4.0, 1.0));
        artigos.add(new Artigo("I", 8.0, 4.0, 8.0));
        artigos.add(new Artigo("J", 8.0, 7.0, 1.0));
        artigos.add(new Artigo("K", 6.0, 7.0, 8.0));
        artigos.add(new Artigo("L", 2.0, 3.0, 10.0));
        artigos.add(new Artigo("M", 7.0, 7.0, 9.0));
        artigos.add(new Artigo("N", 9.0, 7.0, 6.0));
        artigos.add(new Artigo("O", 7.0, 3.0, 2.0));
        artigos.add(new Artigo("P", 10.0, 4.0, 8.0));
        artigos.add(new Artigo("Q", 2.0, 10.0, 6.0));
        artigos.add(new Artigo("R", 9.0, 2.0, 5.0));
        artigos.add(new Artigo("S", 1.0, 5.0, 2.0));
        artigos.add(new Artigo("T", 9.0, 8.0, 7.0));
        artigos.add(new Artigo("U", 3.0, 10.0, 8.0));
        artigos.add(new Artigo("V", 9.0, 2.0, 7.0));
        artigos.add(new Artigo("W", 9.0, 8.0, 9.0));
        artigos.add(new Artigo("X", 2.0, 6.0, 5.0));
        artigos.add(new Artigo("Y", 4.0, 7.0, 6.0));
    }
    
    private void inicializaPopulacao() {
        populacaoIndividuos = new ArrayList<>();

        for (int i = 0; i < numeroIndividuos; i++) {
            populacaoIndividuos.add(novoIndividuo());
        }
    }

    private Individuo novoIndividuo() {
        List<Integer> cromossomo = new ArrayList<>();
        
        for (int i = 0; i < QUANTIDADE_ITENS; i++) {
            cromossomo.add(geraNumeroAleatorio(0, 1));
        }

        return new Individuo(cromossomo);
    }
    
    private int geraNumeroAleatorio(int min, int max) {

        Random rand = new Random();
        
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    

    private void avaliaPopulacao() {
        double totalFitness = 0;
        for (int i = 0; i < populacaoIndividuos.size(); i++) {
            Individuo individuo = populacaoIndividuos.get(i);
            calculaFitness(individuo);
            totalFitness += individuo.getFitness();
        }
        double mediaFitness = totalFitness/populacaoIndividuos.size();
        listaValoresGrafico.add(mediaFitness);
    }
    
    private void calculaFitness(Individuo individuo) {
        double fitness = 0, valor = 0, peso = 0, volume = 0;
        
        for (int i = 0; i < individuo.getCromossomo().size(); i++) {
            peso += (individuo.getCromossomo().get(i) * artigos.get(i).getPeso());
            volume += (individuo.getCromossomo().get(i) * artigos.get(i).getVolume());
            valor += (individuo.getCromossomo().get(i) * artigos.get(i).getValor());
        }
        
        if (peso <= CAPACIDADE_PESO && volume <= CAPACIDADE_VOLUME) {
           fitness = valor;
        }
        
        individuo.setFitness(fitness);
        individuo.setPeso(peso);
        individuo.setValor(valor);
        individuo.setVolume(volume);
    }

    private void selecionaPais() {

        List<Individuo> populacaoOrdenada = new ArrayList<>(populacaoIndividuos);

        Collections.sort(populacaoOrdenada, new ComparadorFitness());
        
        // retorna os primeiros elementos da lista
        populacaoIndividuos = populacaoOrdenada.subList(0, quantidadeSelecaoPais);
    }

    private void recombina() {

        List<Individuo> listaFilhos = new ArrayList<>();

        for (int i = 0; i < populacaoIndividuos.size(); i += 2) {

            Individuo pai1 = populacaoIndividuos.get(i);
            Individuo pai2 = populacaoIndividuos.get(i + 1);

            listaFilhos.addAll(crossOver(pai1, pai2));
        }

        populacaoIndividuos.addAll(listaFilhos);

    }

    private List<Individuo> crossOver(Individuo pai1, Individuo pai2) {
        List<Individuo> listaFilhos = new ArrayList<>();

        int pontoDeCorte = (int) (Math.random() * (QUANTIDADE_ITENS - 1));

        Individuo filho1 = new Individuo();
        Individuo filho2 = new Individuo();

        for (int i = 0; i <= pontoDeCorte; i++) {
            filho1.getCromossomo().add(pai1.getCromossomo().get(i));
            filho2.getCromossomo().add(pai2.getCromossomo().get(i));
        }

        for (int i = pontoDeCorte+1; i < QUANTIDADE_ITENS; i++) {
            filho1.getCromossomo().add(pai2.getCromossomo().get(i));
            filho2.getCromossomo().add(pai1.getCromossomo().get(i));
        }
        
//        System.out.println("Pai1: " + pai1.getCromossomo().toString());
//        System.out.println("Pai2: " + pai2.getCromossomo().toString());
//        System.out.println("Filho1: " + filho1.getCromossomo().toString());
//        System.out.println("Filho2: " + filho2.getCromossomo().toString());
//        System.out.println("PC: "+pontoDeCorte);
//        System.out.println();

        listaFilhos.add(filho1);
        listaFilhos.add(filho2);

        return listaFilhos;
    }

    private void mutacao() {
        int qtdIndividuosMutacao = (int) (populacaoIndividuos.size() * porcentagemMutacao / 100);

        List<Integer> individuosJaMutados = new ArrayList<>();

        int contadorIndividuos = 0;

        do {

            int individuo = (int) (Math.random() * populacaoIndividuos.size());

            if (!individuosJaMutados.contains(individuo)) {
                individuosJaMutados.add(individuo);
                contadorIndividuos++;
                //swap(populacaoIndividuos.get(individuo));
                inverteBit(populacaoIndividuos.get(individuo));
            }

        } while (contadorIndividuos < qtdIndividuosMutacao);

    }

    private void swap(Individuo individuo) {
        int posicao1 = (int) (Math.random() * individuo.getCromossomo().size());
        int posicao2;

        do {

            posicao2 = (int) (Math.random() * individuo.getCromossomo().size());

        } while (posicao1 == posicao2);

        int temp = individuo.getCromossomo().get(posicao1);
        individuo.getCromossomo().set(posicao1, individuo.getCromossomo().get(posicao2));
        individuo.getCromossomo().set(posicao2, temp);
    }
    
    private void inverteBit(Individuo individuo) {
        int posicao = (int) (Math.random() * individuo.getCromossomo().size());
        
        Integer bit = individuo.getCromossomo().get(posicao);
        
        if (bit == 1) {
            individuo.getCromossomo().set(posicao, 0);
        } else {
            individuo.getCromossomo().set(posicao, 1);
        }
    }

    public int getNumeroIndividuos() {
        return numeroIndividuos;
    }

    public void setNumeroIndividuos(int numeroIndividuos) {
        this.numeroIndividuos = numeroIndividuos;
    }

    public int getNumeroEpocas() {
        return numeroEpocas;
    }

    public void setNumeroEpocas(int numeroEpocas) {
        this.numeroEpocas = numeroEpocas;
    }

    public int getQuantidadeSelecaoPais() {
        return quantidadeSelecaoPais;
    }

    public void setQuantidadeSelecaoPais(int quantidadeSelecaoPais) {
        this.quantidadeSelecaoPais = quantidadeSelecaoPais;
    }

    public int getPorcentagemMutacao() {
        return porcentagemMutacao;
    }

    public void setPorcentagemMutacao(int porcentagemMutacao) {
        this.porcentagemMutacao = porcentagemMutacao;
    }

    public List<Individuo> getPopulacaoIndividuos() {
        return populacaoIndividuos;
    }

    public void setPopulacaoIndividuos(List<Individuo> populacaoIndividuos) {
        this.populacaoIndividuos = populacaoIndividuos;
    }

    public List<Double> getListaValoresGrafico() {
        return listaValoresGrafico;
    }

    public void setListaValoresGrafico(List<Double> listaValoresGrafico) {
        this.listaValoresGrafico = listaValoresGrafico;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }
}
