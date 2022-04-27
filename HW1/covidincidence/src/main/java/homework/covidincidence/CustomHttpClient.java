package homework.covidincidence;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CustomHttpClient {

    static final String HOST = "covid-193.p.rapidapi.com";

    static final String KEY = "e43a5d1d6fmsh454b6ef0f1e4428p104c4fjsnd533eb647f22";

    public HttpResponse<String> getHttpResponse(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
        .uri(uri)
        .header("X-RapidAPI-Host", HOST)
        .header("X-RapidAPI-Key", KEY)
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
