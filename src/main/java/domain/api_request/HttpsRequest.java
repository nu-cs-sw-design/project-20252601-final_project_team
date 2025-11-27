package domain.api_request;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class HttpsRequest {

    private final String baseUrl;
    private final HttpClient client;

    public HttpsRequest(String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public ResponseEntity get(String path) throws Exception {
        String url = buildUrl(path);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return send(request);
    }

    public ResponseEntity post(String path, String jsonBody) throws Exception {
        String url = buildUrl(path);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return send(request);
    }

    private ResponseEntity send(HttpRequest request) throws Exception {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Map<String, String> headers = new HashMap<>();
        response.headers().map().forEach((k, v) -> headers.put(k, String.join(",", v)));

        return new ResponseEntity(response.statusCode(), headers, response.body());
    }

    /** 拼接完整 URL */
    private String buildUrl(String path) {
        if (baseUrl.endsWith("/") || path.startsWith("/")) {
            return baseUrl + path;
        } else {
            return baseUrl + "/" + path;
        }
    }
}
