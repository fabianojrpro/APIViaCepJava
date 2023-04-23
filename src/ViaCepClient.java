import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCepClient {

    private static final String URL = "https://viacep.com.br/ws/%s/json/";

    public static Endereco consultarCep(String cep) throws Exception {
        String url = String.format(URL, cep);
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            throw new Exception("Erro ao consultar CEP: " + con.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        String responseJson = sb.toString();
        Endereco endereco = new Endereco();
        endereco.cep = extractValueFromJson(responseJson, "cep");
        endereco.logradouro = extractValueFromJson(responseJson, "logradouro");
        endereco.bairro = extractValueFromJson(responseJson, "bairro");
        endereco.localidade = extractValueFromJson(responseJson, "localidade");
        endereco.uf = extractValueFromJson(responseJson, "uf");
        endereco.ibge = extractValueFromJson(responseJson, "ibge");
        endereco.gia = extractValueFromJson(responseJson, "gia");
        endereco.ddd = extractValueFromJson(responseJson, "ddd");
        endereco.siafi = extractValueFromJson(responseJson, "siafi");

        return endereco;
    }

    private static String extractValueFromJson(String json, String field) {
        String[] parts = json.split("\"" + field + "\":");
        if (parts.length == 1) {
            return "";
        }
        String value = parts[1].split(",")[0];
        value = value.replaceAll("\"", "");
        if (field.equals("siafi")) {
            value = value.substring(0, value.length() - 1);
        }
        return value;
    }

}
