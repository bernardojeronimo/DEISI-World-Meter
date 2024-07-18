package pt.ulusofona.aed.deisiworldmeter;

public class Paises {
    //id,alfa2,alfa3,nome
    short id;
    String alfa2;
    String alfa3;
    String nome;

    short nlinhas;

    public Paises(short id, String alfa2, String alfa3, String nome) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
    }

    public Paises(short id, String alfa2, String alfa3, String nome, short nlinhas) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
        this.nlinhas = nlinhas;
    }

    public Paises() {
    }

    @Override
    public String toString() {
        if (id < 700) {
            return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase();
        }
        return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase() + " | " + nlinhas;
    }
}