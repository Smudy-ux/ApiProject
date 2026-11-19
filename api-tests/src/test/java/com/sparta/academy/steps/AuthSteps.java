package com.sparta.academy.steps;

import com.sparta.academy.client.ApiSpec;
import com.sparta.academy.context.TestContext;
import com.sparta.academy.models.LoginRequest;
import com.sparta.academy.models.TokenResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthSteps {
    private final TestContext context;

    public AuthSteps(TestContext context) {
        this.context = context;
    }

    @When("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {
        Response response = given()
                .spec(ApiSpec.base())
                .body(new LoginRequest(username, password))
                .when()
                .post("/auth/login");

        context.setResponse(response);
    }

    @And("the response should contain a token")
    public void theResponseShouldContainAToken() {
        TokenResponse body = context.getResponse().as(TokenResponse.class);
        assertThat(body.getToken()).isNotBlank();
    }
}