package tests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApacheHttpClientTest {

    @Test
    public void checkStatusCodeTest() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://jsonplaceholder.typicode.com/posts");
        HttpResponse response = client.execute(request);
        System.out.println(response.getEntity().getContentType());
    }

    @Test
    public void checkResponseContentTest() {

    }

}
