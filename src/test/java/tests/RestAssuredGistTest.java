package tests;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RestAssuredGistTest {

    private String idCreatedGist;

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void testPut() throws IOException {
        String json = new
                String(Files.readAllBytes(Paths.get("D:\\Projects\\testingrest\\src\\test\\java\\resources\\gist.json")));
        Response response = RestAssured.given()
//               .auth()
//               .preemptive()
//               .basic("userName", "password")
                .header("Content-Type", "application/json")
                //.contentType(JSON)
                .header("Authorization", "Basic cmVhMnR1dEBnbWFpbC5jb206a2lja21lVUIyMg==")
                .body(json)
                .when()
                .post("/gists")
                .andReturn();
        printResponse(response);

/*
        JsonParser jsonParser = new JsonParser();
        String jsonText = response.getBody().asString();
        JsonObject jsonObject = jsonParser.parse(jsonText).getAsJsonObject();
        */

        JsonObject jsonObject = response.getBody().as(JsonObject.class);
        idCreatedGist = jsonObject.get("id").getAsString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test(dependsOnMethods = "testPut")
    public void testDelete() {
        Response response = RestAssured.given()
                .header("Authorization", "Basic cmVhMnR1dEBnbWFpbC5jb206a2lja21lVUIyMg==")
                .header("Content-Type", "application/json")
                .when()
                .delete("/gists/" + idCreatedGist)
                .andReturn();
        printResponse(response);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    private void printResponse(Response response) {
        System.out.println(response.getStatusLine() + "\n" + response.asString());
    }

}





