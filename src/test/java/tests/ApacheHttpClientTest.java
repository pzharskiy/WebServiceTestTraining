package tests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApacheHttpClientTest {

    HttpClient client;
    HttpGet request;
    HttpResponse response;
    String URL="http://jsonplaceholder.typicode.com/posts";

    @BeforeClass
    public void preparation() throws IOException {
        client = new DefaultHttpClient();
        request = new HttpGet(URL);
        response = client.execute(request);
    }

    @Test
    public void checkStatusCodeTest() throws IOException {

        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void checkResponseContentTest() throws IOException {
        System.out.println(response.getEntity().getContentType());
    }

}
