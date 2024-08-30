package com.api;

import com.core.models.dto.ContactDTO;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.mockserver.integration.ClientAndServer;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.core.providers.TestDataGenerator.*;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockTests {
    ClientAndServer mockServer;
    @BeforeTest
    public void bt(){
        mockServer = startClientAndServer(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    public void addContact_negativeStub(){
        mockServer
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/v1/contacts")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"message\": \"Hello, MockServer!\"}")
                );


        String responseBody = RestAssured.
                given()
                .when()
                .get("/v1/contacts")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().asString();
        Assert.assertTrue(responseBody.contains("Hello, MockServer!"));

    }

    @AfterTest
    public void at(){
        mockServer.stop();

    }

}
