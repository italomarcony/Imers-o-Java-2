import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexao HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco) .GET() .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar so os dados que interessam (titulo, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[m\n\u001b[1m\u001b[35m Movie -\u001b[m\u001b[1m\u001b[35m " + filme.get("title"));
            System.out.println("\u001b[m\n\u001b[1m\u001b[35m Poster -\u001b[m\u001b[1m\u001b[35m " + filme.get("image"));
            System.out.print("\u001b[m\n\u001b[1m\u001b[35m Rating -\u001b[m\u001b[1m\u001b[35m " + filme.get("imDbRating"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int starNumber = (int) classificacao;

            for (int n = 1; n <= starNumber; n++) {
                System.out.print("⭐");

                
            }
            System.out.print("\n-------------------------------------------------------------------------------------------------------------");
        }
    }
}
