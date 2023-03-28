
package org.primefaces.manhattan.util;

/**
 *
 * @author masprog
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class ApacheHttpClient {

  
    // Metodo responsável por fazer as requisições GET no API Bugzzila
    public static StringBuilder httpGETRequest(String url) throws IOException {
        StringBuilder builder;

        System.out.println("url: " + url);
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            builder = stringBuilder(response);
        }
        return builder;
    }

    //Post request
    public static StringBuilder postRESTAPI(String url, String json) {
        int codigo = 0;
        StringBuilder builder = null;
        System.out.println("url: " + url);
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url);
            postRequest.setHeader("Content-Type", "application/json;charset=UTF-8");

            StringEntity input = new StringEntity(json, "UTF-8");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);
            codigo = response.getStatusLine().getStatusCode();

            builder = stringBuilder(response);

            httpClient.getConnectionManager().shutdown();

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
        return builder;
    }

    public static StringBuilder putRESTAPI(String url, String json) {
        int codigo = 0;
        StringBuilder builder = null;
        System.out.println("url: " + url);
        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(url);

            httpPut.setHeader("Content-Type", "application/json;charset=UTF-8");

            StringEntity input = new StringEntity(json, "UTF-8");
            httpPut.setEntity(input);

            HttpResponse response = httpClient.execute(httpPut);
            codigo = response.getStatusLine().getStatusCode();

            // if (codigo >= 200 && codigo < 300) {
            builder = stringBuilder(response);

            httpClient.getConnectionManager().shutdown();

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
        return builder;
    }

    private static StringBuilder stringBuilder(HttpResponse response) throws IOException {
        StringBuilder builder = new StringBuilder();

        BufferedReader bufReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

        String line;

        while ((line = bufReader.readLine()) != null) {
            builder.append(line);
            builder.append(System.lineSeparator());
        }
        return builder;
    }
}
