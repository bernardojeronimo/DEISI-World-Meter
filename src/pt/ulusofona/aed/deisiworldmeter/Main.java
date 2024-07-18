package pt.ulusofona.aed.deisiworldmeter;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Paises> paises = new ArrayList<>();
    static ArrayList<Cidades> cidades = new ArrayList<>();
    static ArrayList<Populacao> populacao = new ArrayList<>();
    static ArrayList<InputInvalido> inputInvalido = new ArrayList<>();
    static HashSet<Short> getIdP = new HashSet<>();
    static HashSet<String> getAlfaP = new HashSet<>();


    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean hasCity(String alfa2) {
        for (Cidades cidade : cidades) {
            if (cidade.alfa2.equals(alfa2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCountry(short id) {
        for (Paises pais : paises) {
            if (pais.id == id) {
                return true;
            }
        }
        return false;
    }


    public static boolean dupId(short id) {
        for (Paises pais : paises) {
            if (pais.id == id) {
                return true;
            }
        }
        return false;
    }


    public static ArrayList getObjects(TipoEntidade tipo) {

        return switch (tipo) {
            case PAIS -> paises;
            case CIDADE -> cidades;
            case INPUT_INVALIDO -> inputInvalido;
            default -> null;
        };

    }

    public static boolean parseFiles(File folder) {
        BufferedReader scan1, scan2, scan3;
        File fPaises = new File(folder, "paises.csv"), fCid = new File(folder, "cidades.csv"), fPop = new File(folder, "populacao.csv");

        //reset variaveis
        paises = new ArrayList<>();
        cidades = new ArrayList<>();
        populacao = new ArrayList<>();
        inputInvalido = new ArrayList<>();
        getIdP = new HashSet<>();
        getAlfaP = new HashSet<>();

        try {
            scan1 = new BufferedReader(new FileReader(fPaises));
            scan2 = new BufferedReader(new FileReader(fCid));
            scan3 = new BufferedReader(new FileReader(fPop));
        } catch (FileNotFoundException e) {
            return false;
        }

        //le ficheiro paises
        boolean primeiraLinha = true;
        int ok0 = 0, nOk0 = 0, primeiraNok0 = -1;

        String line = null;
        do {
            try {
                line = scan1.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] partes = line.split(",");
                    if (primeiraNok0 == -1 && (partes.length != 4 || !isNumber(partes[0]) || dupId(Short.parseShort(partes[0])) || partes[1].isEmpty() || partes[2].isEmpty() || partes[3].isEmpty())) {
                        primeiraNok0 = ok0 + 2;
                        nOk0++;
                    } else if (partes.length != 4 || !isNumber(partes[0]) || dupId(Short.parseShort(partes[0])) || partes[1].isEmpty() || partes[2].isEmpty() || partes[3].isEmpty()) {
                        nOk0++;
                    } else {
                        //id,alfa2,alfa3,nome
                        short id = Short.parseShort(partes[0]);
                        String alfa2 = partes[1];
                        String alfa3 = partes[2];
                        String nome = partes[3];
                        Paises pai = new Paises(id, alfa2, alfa3, nome);
                        paises.add(pai);
                        getIdP.add(id);
                        getAlfaP.add(alfa2);
                        ok0++;
                    }
                }
            }
        }
        while (line != null);

        InputInvalido input1 = new InputInvalido("paises.csv", ok0, nOk0, primeiraNok0);

        // le ficheiro cidades
        primeiraLinha = true;
        int ok = 0, nOk = 0, primeiraNok = -1;
        line = null;
        do {
            try {
                line = scan2.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    //alfa2,cidade,regiao,populacao,latitude,longitude

                    String[] partes1 = line.split(",");
                    if (primeiraNok == -1 && (partes1.length != 6 || !getAlfaP.contains(partes1[0]) || partes1[0].isEmpty() || partes1[2].isEmpty() || partes1[3].isEmpty() || !isNumber(partes1[3]) || partes1[4].isEmpty() || !isNumber(partes1[4]) || partes1[5].isEmpty() || !isNumber(partes1[5]))) {
                        primeiraNok = ok + 2;
                        nOk++;
                    } else if (partes1.length != 6 || !getAlfaP.contains(partes1[0]) || partes1[0].isEmpty() || partes1[2].isEmpty() || partes1[3].isEmpty() || !isNumber(partes1[3]) || partes1[4].isEmpty() || !isNumber(partes1[4]) || partes1[5].isEmpty() || !isNumber(partes1[5])) {
                        nOk++;
                    } else {
                        String alfa2 = partes1[0];
                        String cidade = partes1[1];
                        String regiao = partes1[2];
                        int populacao = (int) (Double.parseDouble(partes1[3]));
                        double latitude = Double.parseDouble(partes1[4]);
                        double longitude = Double.parseDouble(partes1[5]);
                        Cidades cid = new Cidades(alfa2, cidade, regiao, populacao, latitude, longitude);
                        cidades.add(cid);
                        ok++;
                    }
                }
            }
        }
        while (line != null);

        InputInvalido input = new InputInvalido("cidades.csv", ok, nOk, primeiraNok);


        //le populacao
        primeiraLinha = true;
        int ok1 = 0, nOk1 = 0, primeiraNok1 = -1;

        line = null;
        do {
            try {
                line = scan3.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] partes2 = line.split(",");
                    if (primeiraNok1 == -1 && (partes2.length != 5 || !isNumber(partes2[0]) || !getIdP.contains(Short.parseShort(partes2[0])) || !isNumber(partes2[1]) || !isNumber(partes2[2]) || !isNumber(partes2[3]) || !isNumber(partes2[4]))) {
                        primeiraNok1 = ok1 + 2;
                        nOk1++;
                    } else if (partes2.length != 5 || !isNumber(partes2[0]) || !getIdP.contains(Short.parseShort(partes2[0])) || !isNumber(partes2[1]) || !isNumber(partes2[2]) || !isNumber(partes2[3]) || !isNumber(partes2[4])) {
                        nOk1++;
                    } else {
                        //id,ano,populacao masculina,populacao feminina,densidade
                        short id = Short.parseShort(partes2[0]);
                        short ano = Short.parseShort(partes2[1]);
                        int populacaoM = Integer.parseInt(partes2[2]);
                        int populacaoF = Integer.parseInt(partes2[3]);
                        double densidade = Double.parseDouble(partes2[4]);
                        Populacao populacao1 = new Populacao(id, ano, populacaoM, populacaoF, densidade);
                        populacao.add(populacao1);
                        ok1++;
                    }
                }
            }
        } while (line != null);


        InputInvalido input2 = new InputInvalido("populacao.csv", ok1, nOk1, primeiraNok1);

        for (int l = 0; l < paises.size(); l++) {
            if ((paises.get(l).id > 700)) {
                short id = paises.get(l).id;
                short count = 0;
                for (int i = 0; i < populacao.size(); i++) {
                    if (populacao.get(i).id == id) {
                        count++;
                    }
                }
                Paises paises = new Paises(Main.paises.get(l).id, Main.paises.get(l).alfa2, Main.paises.get(l).alfa3, Main.paises.get(l).nome, count);
                Main.paises.set(l, paises);
            }
        }

        int fInv = -1;
        Iterator<Paises> it = paises.iterator();
        int lineI = 0;
        while (it.hasNext()) {
            Paises pais = it.next();
            if (!hasCity(pais.alfa2)) {
                if (fInv == -1) {
                    fInv = lineI + 2;
                    if (fInv < primeiraNok0) {
                        primeiraNok0 = fInv;
                    }
                }
                it.remove();
                nOk0++;
            }
            lineI++;
        }
        ok0 = paises.size();

        input1 = new InputInvalido("paises.csv", ok0, nOk0, primeiraNok0);


        fInv = -1;
        Iterator<Populacao> it1 = populacao.iterator();
        lineI = 0;
        while (it1.hasNext()) {
            Populacao pop = it1.next();
            if (!hasCountry(pop.id)) {
                if (fInv == -1) {
                    fInv = lineI + 2;
                    if (fInv < primeiraNok1) {
                        primeiraNok1 = fInv;
                    }
                }
                it1.remove();
                nOk1++;
            }
            lineI++;
        }
        ok1 = populacao.size();

        input2 = new InputInvalido("populacao.csv", ok1, nOk1, primeiraNok1);

        inputInvalido.add(input1);
        inputInvalido.add(input);
        inputInvalido.add(input2);

        return true;
    }

    public static Result execute(String command) {
        Result resultado = new Result(false, "Comando invalido", null);
        String[] partes = command.split(" ");
        switch (partes[0]) {
            case "COUNT_CITIES":
                int count = countCities(Integer.parseInt(partes[1]));
                resultado = new Result(true, null, "" + count);
                break;
            case "GET_CITIES_BY_COUNTRY":
                count = Integer.parseInt(partes[1]);
                String pais = String.join(" ", Arrays.copyOfRange(partes, 2, partes.length));
                String cidades = getCitiesByCountry(count, pais);
                resultado = new Result(true, null, cidades);
                break;
            case "SUM_POPULATIONS":
                int soma = 0;
                String partes3 = String.join(" ", Arrays.copyOfRange(partes, 1, partes.length));
                String[] partes1 = partes3.split(",");
                for (int i = 0; i < partes1.length; i++) {
                    pais = partes1[i];
                    if (!populacaoSum(partes1[i]).equals("invalido")) {
                        soma += Integer.parseInt(populacaoSum(partes1[i]));
                    } else {
                        return resultado = new Result(true, null, "Pais invalido: " + partes1[i]);
                    }
                }
                resultado = new Result(true, null, "" + soma);
                break;
            case "GET_HISTORY":
                int inicio = Integer.parseInt(partes[1]);
                int fim = Integer.parseInt(partes[2]);
                pais = String.join(" ", Arrays.copyOfRange(partes, 3, partes.length));
                cidades = getHistory(inicio, fim, pais);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_MISSING_HISTORY":
                inicio = Integer.parseInt(partes[1]);
                fim = Integer.parseInt(partes[2]);
                cidades = getMissHistory(inicio, fim);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_MOST_POPULOUS":
                int num = Integer.parseInt(partes[1]);
                cidades = most_Pop(num);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_TOP_CITIES_BY_COUNTRY":
                num = Integer.parseInt(partes[1]);
                pais = String.join(" ", Arrays.copyOfRange(partes, 2, partes.length));
                cidades = most_Pop_Country(num, pais);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_DUPLICATE_CITIES":
                num = Integer.parseInt(partes[1]);
                cidades = dupCities(num);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_COUNTRIES_GENDER_GAP":
                num = Integer.parseInt(partes[1]);
                cidades = countryGender(num);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_TOP_POPULATION_INCREASE":
                inicio = Integer.parseInt(partes[1]);
                fim = Integer.parseInt(partes[2]);
                cidades = populationIncrease(inicio, fim);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES":
                num = Integer.parseInt(partes[1]);
                cidades = duplicateCitiesCountry(num);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_CITIES_AT_DISTANCE":
                num = Integer.parseInt(partes[1]);
                pais = String.join(" ", Arrays.copyOfRange(partes, 2, partes.length));
                cidades = distanceCountry(num, pais);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_CITIES_AT_DISTANCE2":
                num = Integer.parseInt(partes[1]);
                pais = String.join(" ", Arrays.copyOfRange(partes, 2, partes.length));
                cidades = distanceCountry2(num, pais);
                resultado = new Result(true, null, cidades);
                break;
            case "INSERT_CITY":
                String alfa = partes[1];
                cidades = partes[2];
                String regiao = partes[3];
                int pop = Integer.parseInt(partes[4]);
                cidades = insertCity(alfa, cidades, regiao, pop);
                resultado = new Result(true, null, cidades);
                break;

            case "REMOVE_COUNTRY":
                pais = partes[1];
                cidades = removeCountry(pais);
                resultado = new Result(true, null, cidades);
                break;
            case "GET_MOST_POPULOUS_GENDER_BY_COUNTRY":
                String result = "";
                partes3 = String.join(" ", Arrays.copyOfRange(partes, 1, partes.length));
                partes1 = partes3.split(",");
                for (int i = 0; i < partes1.length; i++) {
                    pais = partes1[i];
                    if (mostPopulousByGender(partes1[i]).equals("Pais invalido")) {
                        return resultado = new Result(true, null, "Pais invalido: " + partes1[i]);
                    }
                    if (!mostPopulousByGender(partes1[i]).equals("Dados indisponiveis")) {
                        result += mostPopulousByGender(partes1[i]);
                    } else {
                        return resultado = new Result(true, null, "Dados indisponiveis");
                    }
                }
                resultado = new Result(true, null, result);
                break;
            case "HELP":
                String help = """

                        -------------------------
                        Commands available:
                        COUNT_CITIES ‹min_population>
                        GET_CITIES_BY_COUNTRY <num-results> <country-name>
                        SUM_POPULATIONS <countries-list>
                        GET_HISTORY <year-start> ‹year-end> <country_name>
                        GET_MISSING_HISTORY ‹year-start> ‹year-end>
                        GET_MOST_POPULOUS ‹num-results>
                        GET_TOP_CITIES_BY_COUNTRY <num-results> <country-name>
                        GET_DUPLICATE_CITIES ‹min_population>
                        GET_COUNTRIES_GENDER_GAP <min-gender-gap>
                        GET_TOP_POPULATION_INCREASE <year-start> ‹year_end>
                        GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES ‹min-population>
                        GET_CITIES_AT_DISTANCE ‹distance> ‹country-name>
                        GET_CITIES_AT_DISTANCE2 <distance> ‹country-name>
                        INSERT_CITY ‹alfa2> <city-name> ‹region> ‹population›
                        REMOVE_COUNTRY <country-name>
                        GET_MOST_POPULOUS_GENDER_BY_COUNTRY <countries-list>
                        HELP
                        QUIT
                        -------------------------

                        """;
                resultado = new Result(true, null, help);
                break;
            default:
                return resultado;
        }
        return resultado;
    }

    public static int countCities(int minpop) {
        int count = 0;
        for (Cidades cid : cidades) {
            if (cid.populacao >= minpop) {
                count++;
            }
        }
        return count;
    }

    public static String getCitiesByCountry(int numero, String nome) {
        int count = 1;
        StringBuilder resultado = new StringBuilder();
        String alfa = "";

        if (paises == null || cidades == null || cidades.isEmpty() || paises.isEmpty()) {
            return "";
        }

        for (Paises paise : paises) {
            if (Objects.equals(paise.nome, nome)) {
                alfa = paise.alfa2;
            }
        }
        for (int j = 0; j < cidades.size() && count <= numero; j++) {
            if (Objects.equals(cidades.get(j).alfa2, alfa)) {
                resultado.append(cidades.get(j).cidade).append("\n");

                count++;
            }

        }


        return resultado.toString();
    }

    public static String populacaoSum(String pais) {
        Date date = new Date();
        int ano = date.getYear() + 1900;
        String sum = "";
        int id = 0;
        for (Paises paise : paises) {
            if (Objects.equals(pais, paise.nome)) {
                id = paise.id;
            }
        }

        if (id == 0) {
            return "invalido";
        }
        for (int i = 0; i < Main.populacao.size(); i++) {
            int ano2 = Main.populacao.get(i).ano;
            int id2 = Main.populacao.get(i).id;
            if (ano2 == ano && id == id2) {
                return sum = "" + (populacao.get(i).populacaoM + populacao.get(i).populacaoF);
            }
        }
        return sum;
    }

    public static String getHistory(int inicio, int fim, String pais) {
        StringBuilder resultado = new StringBuilder();

        if (paises == null || populacao == null || populacao.isEmpty() || paises.isEmpty()) {
            return "";
        }

        int id = -1;
        for (Paises paise : paises) {
            if (Objects.equals(pais, paise.nome)) {
                id = paise.id;
                break;
            }
        }
        if (id == -1) {
            return "";
        }

        for (Populacao pop : populacao) {
            if (pop.id == id && pop.ano >= inicio && pop.ano <= fim) {
                resultado.append(pop.ano).append(":").append(pop.populacaoM / 1000).append("k:").append(pop.populacaoF / 1000).append("k").append("\n");
            }
        }

        return resultado.toString();
    }

    public static String getMissHistory(int inicio, int fim) {
        StringBuilder resultado = new StringBuilder();
        for (Paises pais : paises) {
            Set<Integer> anos = new HashSet<>();
            for (Populacao pop : populacao) {
                if (pais.id == pop.id && pop.ano >= inicio && pop.ano <= fim) {
                    anos.add((int) pop.ano);
                }
            }
            if (findMissingYear(anos, inicio, fim)) {
                resultado.append(pais.alfa2).append(":").append(pais.nome).append("\n");
            }
        }
        if (resultado.length() == 0) {
            return "Sem resultados";
        }
        return resultado.toString();
    }


    public static boolean findMissingYear(Set<Integer> years, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (!years.contains(i)) {
                return true;
            }
        }
        return false;
    }

    public static String most_Pop(int num) {
        Map<String, String> paisMap = new HashMap<>();
        Map<String, Integer> populacaoPorPais = new HashMap<>();
        Map<String, String> cidadeMaisPopulosaPorPais = new HashMap<>();

        for (Paises pais : paises) {
            paisMap.put(pais.alfa2, pais.nome);
        }


        for (Cidades cidade : cidades) {
            String alfa2 = cidade.alfa2;
            int pop = cidade.populacao;
            String cidadeNome = cidade.cidade;
            if (!cidadeMaisPopulosaPorPais.containsKey(alfa2) || cidadeMaisPopulosaPorPais.get(alfa2).isEmpty()) {
                cidadeMaisPopulosaPorPais.put(alfa2, cidadeNome);
                populacaoPorPais.put(alfa2, pop);
            } else {
                int popPais = populacaoPorPais.get(alfa2);
                if (pop > popPais) {
                    populacaoPorPais.put(alfa2, pop);
                    cidadeMaisPopulosaPorPais.put(alfa2, cidadeNome);
                }
            }
        }

        List<String> sortedKeys = new ArrayList<>(populacaoPorPais.keySet());
        sortedKeys.sort((a, b) -> populacaoPorPais.get(b).compareTo(populacaoPorPais.get(a)));


        StringBuilder result = new StringBuilder();
        for (String alfa2 : sortedKeys) {
            String paisName = paisMap.get(alfa2);
            String cidadeMaisPopulosa = cidadeMaisPopulosaPorPais.get(alfa2);
            int pop = populacaoPorPais.get(alfa2);
            result.append(paisName).append(":").append(cidadeMaisPopulosa).append(":").append(pop).append("\n");
            num--;
            if (num == 0) {
                break;
            }
        }

        return result.toString();
    }

    public static String most_Pop_Country(int num, String name) {
        Map<String, String> countryNameToCode = new HashMap<>();

        for (Paises p : paises) {
            countryNameToCode.put(p.nome, p.alfa2);
        }

        String alfa2 = countryNameToCode.get(name);
        if (alfa2 == null) {
            return "Country not found";
        }

        List<Cidades> cOrd = new ArrayList<>();
        for (Cidades cidade : cidades) {
            if (cidade.populacao >= 10000 && Objects.equals(cidade.alfa2, alfa2)) {
                cOrd.add(cidade);
            }
        }
        cOrd.sort((cid1, cid2) -> {
            int popComparison = Long.compare(cid2.populacao / 1000, cid1.populacao / 1000);
            if (popComparison != 0) {
                return popComparison;
            }
            return cid1.cidade.compareTo(cid2.cidade);
        });
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (Cidades cidade : cOrd) {
            result.append(cidade.cidade).append(":").append(cidade.populacao / 1000).append("K\n");
            count++;
            if (count == num) {
                break;
            }
        }

        return result.toString();
    }

    public static String dupCities(int minPop) {
        Map<String, Cidades> numCities = new HashMap<>();
        StringBuilder result = new StringBuilder();

        for (Cidades cidade : cidades) {
            if (cidade.populacao >= minPop) {
                if (numCities.containsKey(cidade.cidade)) {
                    for (Paises pais : paises) {
                        if (cidade.alfa2.equals(pais.alfa2)) {
                            result.append(cidade.cidade).append(" (").append(pais.nome).append(",").append(cidade.regiao).append(")\n");
                        }
                    }
                } else {
                    numCities.put(cidade.cidade, cidade);
                }
            }
        }

        return result.toString();
    }


    public static String countryGender(int mingender) {

        int popF = 0;
        int popM = 0;
        boolean resultado = false;
        StringBuilder result = new StringBuilder();
        for (Paises p : paises) {
            for (Populacao pop : populacao) {
                if (p.id == pop.id && pop.ano == 2024) {
                    popM = pop.populacaoM;
                    popF = pop.populacaoF;
                    break;
                }
            }
            double gap = Math.abs(popM - popF) /
                    (double) (popM + popF) * 100;
            if (gap >= mingender) {
                result.append(p.nome).append(":").append(String.format("%.2f", gap)).append("\n");
                resultado = true;
            }
        }
        if (!resultado) {
            return "Sem resultados";
        }

        return result.toString();
    }

    public static String populationIncrease(int inicio, int fim) {
        StringBuilder result = new StringBuilder();
        Map<Integer, String> paisPorId = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> popAno = new HashMap<>();
        List<Map.Entry<String, Double>> populationIncrease = new ArrayList<>();

        for (Paises pais : paises) {
            paisPorId.put((int) pais.id, pais.nome);
        }

        for (Populacao pop : populacao) {
            popAno.computeIfAbsent((int) pop.id, p -> new HashMap<>()).put(Integer.parseInt(String.valueOf((pop.ano))), pop.populacaoM + pop.populacaoF);
        }

        for (Map.Entry<Integer, Map<Integer, Integer>> entry : popAno.entrySet()) {

            Map<Integer, Integer> popAnoMap = entry.getValue();
            int idPais = entry.getKey();

            for (int ano1 = inicio; ano1 <= fim; ano1++) {
                for (int ano2 = ano1 + 1; ano2 <= fim; ano2++) {
                    if (popAnoMap.containsKey(ano1) && popAnoMap.containsKey(ano2)) {
                        int popInicial = popAnoMap.get(ano1);
                        int popFinal = popAnoMap.get(ano2);
                        double resultado = ((double) (popFinal - popInicial) / popFinal) * 100;
                        if (resultado > 0) {
                            String chave = paisPorId.get(idPais) + ":" + ano1 + "-" + ano2;
                            populationIncrease.add(new AbstractMap.SimpleEntry<>(chave, resultado));
                        }
                    }

                }

            }
        }

        populationIncrease.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (int i = 0; i < Math.min(5, populationIncrease.size()); i++) {

            Map.Entry<String, Double> resultado = populationIncrease.get(i);
            result.append(resultado.getKey())
                    .append(":")
                    .append(String.format("%.2f", resultado.getValue()))
                    .append("%")
                    .append("\n");
        }
        return result.toString();
    }


    public static String duplicateCitiesCountry(int minPopulacao) {
        Map<String, Map<String, List<Cidades>>> cidadesMapeadas = new HashMap<>();
        Map<String, String> paisAlfa2 = new HashMap<>();
        StringBuilder result = new StringBuilder();


        for (Cidades cidade : cidades) {
            if (cidade.populacao >= minPopulacao) {
                cidadesMapeadas.computeIfAbsent(cidade.cidade, c -> new HashMap<>())
                        .computeIfAbsent(cidade.alfa2, k -> new ArrayList<>()).add(cidade);
            }
        }


        for (Paises pais : paises) {
            paisAlfa2.put(pais.alfa2, pais.nome);
        }


        for (String cidade : cidadesMapeadas.keySet()) {
            Map<String, List<Cidades>> paisesCidades = cidadesMapeadas.get(cidade);
            if (paisesCidades.size() > 1) {
                Set<String> paises = new TreeSet<>();
                for (String alfa2 : paisesCidades.keySet()) {
                    paises.add(paisAlfa2.get(alfa2));
                }
                if (!paises.isEmpty()) {
                    result.append(cidade).append(": ");
                    result.append(String.join(",", paises));
                    result.append("\n");
                }
            }
        }
        if (result.length() == 0) {
            return "Sem resultados";
        }
        return result.toString();
    }

    public static String distanceCountry(int distancia, String pais) {
        String alfapais = "";

        for (Paises paises1 : paises) {
            if (Objects.equals(paises1.nome, pais)) {
                alfapais = paises1.alfa2;
                break;
            }
        }

        Map<String, Cidades> cidadespais = new HashMap<>();
        for (Cidades cid : cidades) {
            if (cid.alfa2.equals(alfapais)) {
                cidadespais.put(cid.cidade, cid);
            }
        }

        ArrayList<String> cidadesDistancia = new ArrayList<>();
        List<Cidades> cidlista = new ArrayList<>(cidadespais.values());
        int size = cidlista.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Cidades city1 = cidlista.get(i);
                Cidades city2 = cidlista.get(j);
                double lat1 = Math.toRadians(city1.latitude);
                double lon1 = Math.toRadians(city1.longitude);
                double lat2 = Math.toRadians(city2.latitude);
                double lon2 = Math.toRadians(city2.longitude);

                double dlon = lon2 - lon1;
                double dlat = lat2 - lat1;

                double a = Math.pow(Math.sin(dlat / 2), 2)
                        + Math.cos(lat1) * Math.cos(lat2)
                        * Math.pow(Math.sin(dlon / 2), 2);

                double c = 2 * Math.asin(Math.sqrt(a));

                double r = 6371;

                double calculaDistancia = c * r;

                if (Math.abs(calculaDistancia - distancia) < 1) {
                    String cityPair = city1.cidade.compareTo(city2.cidade) < 0
                            ? city1.cidade + "->" + city2.cidade
                            : city2.cidade + "->" + city1.cidade;

                    cidadesDistancia.add(cityPair);
                }
            }
        }

        // Ordenar as cidades dentro da distância especificada
        Collections.sort(cidadesDistancia);

        // Construir uma string com as cidades dentro da distância especificada
        StringBuilder citiesStr = new StringBuilder();
        for (String city : cidadesDistancia) {
            citiesStr.append(city).append("\n");
        }

        // Retornar a string com as cidades dentro da distância especificada
        return citiesStr.toString();
    }

    public static String distanceCountry2(int distancia, String pais) {
        String countryAlfa2 = "";
        for (Paises pai : paises) {
            if (pai.nome.equals(pais)) {
                countryAlfa2 = pai.alfa2;
                break;
            }
        }

        Map<String, Cidades> citiesInCountry = new HashMap<>();
        for (Cidades city : cidades) {
            if (city.alfa2.equals(countryAlfa2)) {
                citiesInCountry.put(city.cidade, city);
            }
        }

        Set<String> citiesWithinDistance = new HashSet<>();

        // Encontrar os limites de latitude e longitude
        double minLat = Double.MAX_VALUE;
        double maxLat = Double.MIN_VALUE;
        double minLon = Double.MAX_VALUE;
        double maxLon = Double.MIN_VALUE;
        for (Cidades city : citiesInCountry.values()) {
            minLat = Math.min(minLat, city.latitude);
            maxLat = Math.max(maxLat, city.latitude);
            minLon = Math.min(minLon, city.longitude);
            maxLon = Math.max(maxLon, city.longitude);
        }

        // Iterar apenas dentro do intervalo de latitude e longitude relevantes
        for (Cidades city1 : citiesInCountry.values()) {
            for (Cidades city2 : cidades) {
                if (!city2.alfa2.equals(countryAlfa2) &&
                        city2.latitude >= minLat - 1 && city2.latitude <= maxLat + 1 &&
                        city2.longitude >= minLon - 1 && city2.longitude <= maxLon + 1) {

                    double lat1 = Math.toRadians(city1.latitude);
                    double lon1 = Math.toRadians(city1.longitude);
                    double lat2 = Math.toRadians(city2.latitude);
                    double lon2 = Math.toRadians(city2.longitude);

                    double calculatedDistance = 6371 * Math.acos(
                            Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)
                    );

                    if (Math.abs(calculatedDistance - distancia) < 1) {
                        String cityPair = city1.cidade.compareTo(city2.cidade) < 0
                                ? city1.cidade + "->" + city2.cidade
                                : city2.cidade + "->" + city1.cidade;

                        citiesWithinDistance.add(cityPair);
                    }
                }
            }
        }

        List<String> sortedCities = new ArrayList<>(citiesWithinDistance);
        Collections.sort(sortedCities);

        StringBuilder citiesStr = new StringBuilder();
        for (String city : sortedCities) {
            citiesStr.append(city).append("\n");
        }
        return citiesStr.toString();
    }

    public static String insertCity(String alfa, String cidade, String regiao, int pop) {
        Map<String, String> countryNameToCode = new HashMap<>();

        for (Paises p : paises) {
            countryNameToCode.put(p.alfa2, p.nome);
        }

        String alfa2 = countryNameToCode.get(alfa);
        if (alfa2 == null) {
            return "Pais invalido";
        }
        Cidades cidades1 = new Cidades(alfa, cidade, regiao, pop, 0.0, 0.0);
        Main.cidades.add(cidades1);
        return "Inserido com sucesso";
    }


    public static String removeCountry(String pais) {
        Map<String, String> countryNameAlfa = new HashMap<>();
        Map<String, Integer> countryNameId = new HashMap<>();

        for (Paises p : paises) {
            countryNameAlfa.put(p.nome, p.alfa2);
            countryNameId.put(p.nome, (int) p.id);
        }

        String alfa2 = countryNameAlfa.get(pais);
        Integer id = countryNameId.get(pais);

        if (alfa2 == null || id == null) {
            return "Pais invalido";
        }

        paises.removeIf(p -> Objects.equals(p.alfa2, alfa2) || Objects.equals(p.id, id));
        cidades.removeIf(c -> Objects.equals(c.alfa2, alfa2));
        populacao.removeIf(pop -> Objects.equals(pop.id, id));

        return "Removido com sucesso";
    }

    public static String mostPopulousByGender(String pais) {
        Date date = new Date();
        int ano = date.getYear() + 1900;
        String paisMasculino = "", paisFeminino = "";


        int id = 0;
        for (Paises p : paises) {
            if (Objects.equals(pais, p.nome)) {
                id = p.id;
                break;
            }
        }

        if (id == 0) {
            return "Pais invalido";
        }


        for (Populacao p : populacao) {
            if (p.ano == ano && p.id == id) {

                int populacaoM = p.populacaoM;
                int populacaoF = p.populacaoF;
                if (populacaoM > populacaoF) {
                    return "O " + pais + " tem mais população masculina\n";
                } else {
                    return "O " + pais + " tem mais população feminina\n";
                }
            }
        }

        if (paisMasculino.isEmpty() || paisFeminino.isEmpty()) {
            return "Dados indisponiveis";
        }

        return "Dados indisponiveis";
    }


    public static void main(String[] args) {
        System.out.println("Bem-vindo ao DEISI World Meter");

        long start = System.currentTimeMillis();
        boolean parseOk = parseFiles(new File("."));
        if (!parseOk) {
            System.out.println("Erro na leitura dos ficheiros");
            return;
        }
        long end = System.currentTimeMillis();

        System.out.println("Ficheiros lidos com sucesso em " + (end - start) + " ms");
        System.out.println();

        System.out.println(getObjects(TipoEntidade.INPUT_INVALIDO).get(0));
        System.out.println(getObjects(TipoEntidade.INPUT_INVALIDO).get(1));
        System.out.println(getObjects(TipoEntidade.INPUT_INVALIDO).get(2));
        Result result = execute("HELP");
        System.out.println(result.result);

        Scanner in = new Scanner(System.in);

        String line;
        do {
            System.out.println("> ");
            line = in.nextLine();

            if (line != null && !line.equals("QUIT")) {
                start = System.currentTimeMillis();
                result = execute(line);
                end = System.currentTimeMillis();

                if (!result.success) {
                    System.out.println("Error: " + result.error);
                } else {
                    System.out.println(result.result);
                    System.out.println("{took " + (end - start) + " ms}");
                }
            }
        }
        while (line != null && !line.equals("QUIT"));
    }
}