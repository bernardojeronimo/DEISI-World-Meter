package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

public class TestParseFiles {
    @Test
    public void testPaisesParaString1() {
        Paises paises = new Paises();
        // 818,eg,egy,Egito
        paises.id = 818;
        paises.alfa2 = "eg";
        paises.alfa3 = "egy";
        paises.nome = "Egito";
        paises.nlinhas = 151;

        String resultado = paises.toString();
        String esperado = "Egito" + " | " + 818 + " | " + "EG" + " | " + "EGY" + " | " + 151;

        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    public void testPaisesParaString2() {
        Paises paises = new Paises();
        // 4,af,afg,Afeganistão
        paises.id = 4;
        paises.alfa2 = "af";
        paises.alfa3 = "afg";
        paises.nome = "Afeganistão";

        String resultado = paises.toString();
        String esperado = "Afeganistão" + " | " + 4 + " | " + "AF" + " | " + "AFG";

        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    public void testCidadesParaString() {
        Cidades cidades = new Cidades();
        // af,acin,18,15098.0,34.082481,70.668152
        cidades.alfa2 = "af";
        cidades.cidade = "acin";
        cidades.regiao = "18";
        cidades.populacao = (int) 15098.0;
        cidades.latitude = 34.082481;
        cidades.longitude = 70.668152;

        String resultado = cidades.toString();
        String esperado = "acin" + " | " + "AF" + " | " + 18 + " | " + 15098 + " | " + "(" + 34.082481 + "," + 70.668152 + ")";
        ;

        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    public void testLeitura3ficheiros() {

        boolean testfiles = Main.parseFiles(new File("test-files"));
        String[] esperadoPais = new String[2];
        esperadoPais[0] = "Afeganistão | 4 | AF | AFG";
        esperadoPais[1] = "Andorra | 20 | AD | AND";

        String[] esperadoCidade = new String[4];
        esperadoCidade[0] = "andorra la vella | AD | 07 | 20430 | (42.5,1.5166667)";
        esperadoCidade[1] = "canillo | AD | 02 | 3292 | (42.5666667,1.6)";
        esperadoCidade[2] = "acin | AF | 18 | 15098 | (34.082481,70.668152)";
        esperadoCidade[3] = "alekowzi | AF | 23 | 6 | (32.037015999999994,65.786875)";

        Assertions.assertEquals(Arrays.toString(esperadoPais), Main.getObjects(TipoEntidade.PAIS).toString());
        Assertions.assertEquals(Arrays.toString(esperadoCidade), Main.getObjects(TipoEntidade.CIDADE).toString());

    }

    @Test
    public void testLeitura3ficheirosInputInvalido() {

        boolean testfiles = Main.parseFiles(new File("test-files"));
        String[] esperado = new String[3];
        esperado[0] = "paises.csv | 2 | 3 | 4";
        esperado[1] = "cidades.csv | 4 | 4 | 6";
        esperado[2] = "populacao.csv | 8 | 4 | 10";

        Assertions.assertEquals(Arrays.toString(esperado), Main.getObjects(TipoEntidade.INPUT_INVALIDO).toString());

    }

}
