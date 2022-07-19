import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    private static final String ANSI_RED =  "\u001b[31m";
    private static final String ANSI_GREEN =  "\u001b[32m";
    private static final String ANSI_HEART =  "\uD83D\uDC99";

    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //Pegar os dados que são interessantes para a aplicação (titulo, poster e avaliação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(ANSI_GREEN + (filme.get("fullTitle")));
            System.out.println(filme.get("image"));
            System.out.println(ANSI_RED + (filme.get("imDbRating")) + ANSI_HEART);
            System.out.println();
        }

    }

}
