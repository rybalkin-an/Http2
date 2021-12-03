package http2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.annotations.Test;

public class Http2 {
    final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .build();

    private Response run(String url) throws IOException {
        Headers headers = new Headers.Builder().add("Accept", "*/*").build();
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response;
        }
    }

    @Test
    public void httpTest() throws IOException {
        String http2 = "https://ik.imagekit.io/demo/img/http_test/h_32.jpg?ik-version=1638451938452";
        String http1 = "https://az.imagekit.io/demo/img/http_test/h_70.jpg?ik-version=1638451938578";

        Http2 example = new Http2();
        Response response = example.run(http2);
        System.out.println(response.networkResponse());
        response = example.run(http1);
        System.out.println(response.networkResponse());
    }
}