package pt.ulusofona.aed.deisiworldmeter;


import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


public class TestObg {
    @Test
    public void testCountCities() {
        assertTrue(Main.parseFiles(new File("test-files/test-obg")));
        Result result = Main.execute("COUNT_CITIES 10000");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertEquals("5", result.result);

        result = Main.execute("COUNT_CITIES 100");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertEquals("6", result.result);

    }

    @Test
    public void testgetCitiesByCountry() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));
        Result result = Main.execute("GET_CITIES_BY_COUNTRY 2 Andorra");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[]{"andorra la vella", "canillo"}, resultParts);
        result = Main.execute("GET_CITIES_BY_COUNTRY 3 Afeganistão");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[]{"acin", "alekowzi", "ffaa"}, resultParts);

    }

    @Test
    public void testSumPopulations() {

        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));

        Result result = Main.execute("SUM_POPULATIONS  Andorra");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("Pais invalido:  Andorra", result.result);

        result = Main.execute("SUM_POPULATIONS Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("Pais invalido: Portugal", result.result);
    }

    @Test

    public void testgetHistory() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));
        Result result = Main.execute("GET_HISTORY 1954 1957 Andorra");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[]{"1954:1176k:1270k", "1955:1201k:129k", "1956:1225k:131k", "1957:125k:134k"}, resultParts);
    }

    @Test
    public void testgetMissingHistory() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));
        Result result = Main.execute("GET_MISSING_HISTORY 1947 1957");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        String esperado = "";
        assertArrayEquals(new String[]{"af:Afeganistão", "ad:Andorra"}, resultParts);
    }

    @Test
    public void testTOP_CITIES_BY_COUNTRY() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));
        Result result = Main.execute("GET_TOP_CITIES_BY_COUNTRY 5 Andorra");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");

        assertArrayEquals(new String[]{"ffaa:30K", "andorra la vella:20K"}, resultParts);
    }

    @Test
    public void testGET_MOST_POPULOUS() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));
        Result result = Main.execute("GET_MOST_POPULOUS 5");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        String esperado = "";
        assertArrayEquals(new String[]{"Andorra:ffaa:30000", "Afeganistão:ffaa:30000"}, resultParts);
    }

    @Test
    public void testINSERT_CITY() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));
        Result result = Main.execute("INSERT_CITY ad batata 01 100000");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");

        assertArrayEquals(new String[]{"Inserido com sucesso"}, resultParts);
    }

    @Test
    public void testREMOVE_COUNTRY() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));
        Result result = Main.execute("REMOVE_COUNTRY Andorra");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");

        assertArrayEquals(new String[]{"Removido com sucesso"}, resultParts);
    }
}