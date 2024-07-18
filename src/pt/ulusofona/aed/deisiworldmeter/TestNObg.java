package pt.ulusofona.aed.deisiworldmeter;


import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestNObg {
    @Test
    public void testGET_MOST_POPULOUS_GENDER_BY_COUNTRY() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));

        Result result = Main.execute("GET_MOST_POPULOUS_GENDER_BY_COUNTRY  Andorra");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("Pais invalido:  Andorra", result.result);

        result = Main.execute("GET_MOST_POPULOUS_GENDER_BY_COUNTRY Andorra");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("Dados indisponiveis", result.result);

    }

    @Test
    public void testGET_DUPLICATE_CITIES() {
        assertTrue(Main.parseFiles(new File("test-files/test-obg")));
        Result result = Main.execute("GET_DUPLICATE_CITIES 10000000");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertEquals("", result.result);

        result = Main.execute("GET_DUPLICATE_CITIES 100");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertEquals("ffaa (Afeganistão,02)\nffaa (Andorra,02)\n", result.result);

    }

    @Test
    public void testGET_COUNTRIES_GENDER_GAP() {
        assertTrue(Main.parseFiles(new File("test-files/test-obg")));
        Result result = Main.execute("GET_COUNTRIES_GENDER_GAP 0");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertEquals("Sem resultados", result.result);

    }

    @Test
    public void testGET_TOP_POPULATION_INCREASE() {
        assertTrue(Main.parseFiles(new File("test-files/test-obg")));
        Result result = Main.execute("GET_TOP_POPULATION_INCREASE 1950 1953");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertEquals("Afeganistão:1950-1953:50,10%\nAfeganistão:1950-1951:48,02%\nAfeganistão:1952-1953:44,33%\nAfeganistão:1950-1952:10,36%\nAfeganistão:1951-1953:4,00%\n", result.result);

    }


    @Test
    public void testGET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES() {
        assertTrue(Main.parseFiles(new File("test-files/test-obg")));
        Result result = Main.execute("GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES 1000");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertEquals("ffaa: Afeganistão,Andorra\n", result.result);

    }

    @Test
    public void testGET_CITIES_AT_DISTANCE() {
        assertTrue(Main.parseFiles(new File("test-files", "test-obg")));

        Result result = Main.execute("GET_CITIES_AT_DISTANCE 100000 Andorra");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("", result.result);
    }

}
