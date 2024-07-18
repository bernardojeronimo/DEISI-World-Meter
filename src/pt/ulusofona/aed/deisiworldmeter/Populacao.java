package pt.ulusofona.aed.deisiworldmeter;

public class Populacao {
    //id,ano,populacao masculina,populacao feminina,densidade

    short id;
    short ano;
    int populacaoM;
    int populacaoF;
    double densidade;

    public Populacao(short id, short ano, int populacaoM, int populacaoF, double densidade) {
        this.id = id;
        this.ano = ano;
        this.populacaoM = populacaoM;
        this.populacaoF = populacaoF;
        this.densidade = densidade;
    }

    public Populacao() {
    }
}
