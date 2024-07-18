package pt.ulusofona.aed.deisiworldmeter;

public class Cidades {

    //alfa2,cidade,regiao,populacao,latitude,longitude

    String alfa2;
    String cidade;
    String regiao;
    int populacao;
    double latitude;
    double longitude;

    public Cidades(String alfa2, String cidade, String regiao, int populacao, double latitude, double longitude) {
        this.alfa2 = alfa2;
        this.cidade = cidade;
        this.regiao = regiao;
        this.populacao = populacao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Cidades() {
    }

    @Override
    public String toString() {
        return cidade + " | " + alfa2.toUpperCase() + " | " + regiao + " | " + populacao + " | " + "(" + latitude + "," + longitude + ")";
    }
}
