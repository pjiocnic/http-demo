package pluralsight;

/*
 * src by by Benjamin Muschko: https://learning.oreilly.com/scenarios/java-11-network/9781492081234/
 * 
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SyncHttp {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = null;

        try {
            String urlEndpoint = "https://postman-echo.com/get";
            URI uri = URI.create(urlEndpoint + "?foo1=bar1&foo2=bar2");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Headers: " + response.headers()
                .allValues("content-type"));
        System.out.println("Body: " + response.body());
    }
}