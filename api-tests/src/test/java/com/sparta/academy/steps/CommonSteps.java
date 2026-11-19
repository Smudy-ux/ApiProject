package com.sparta.academy.steps;

import com.sparta.academy.client.ApiSpec;
import com.sparta.academy.context.TestContext;
import com.sparta.academy.models.LoginRequest;
import com.sparta.academy.models.TokenResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps {
    private final TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @When("I have a valid token")
    public void iHaveAValidToken() {
        Response loginResponse = given()
                .spec(ApiSpec.base())
                .body(new LoginRequest("sparta", "global"))
                .when()
                .post("/auth/login");

        assertThat(loginResponse.statusCode()).isEqualTo(200);

        TokenResponse tokenResponse = loginResponse.as(TokenResponse.class);
        context.setAuthHeader("Bearer " + tokenResponse.getToken());
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int status) {
        assertThat(context.getResponse().statusCode()).isEqualTo(status);
    }

    @When("I don't have a valid token")
    public void iDontHaveAValidToken() {
        context.setAuthHeader("Bearer invalid-token");
    }
}