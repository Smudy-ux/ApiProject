package com.sparta.academy.steps;

import com.sparta.academy.client.ApiSpec;
import com.sparta.academy.models.LoginRequest;
import com.sparta.academy.models.TokenResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthSteps {

    private Response response;

    @When("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {
        response = given()
                .spec(ApiSpec.base())
                .body(new LoginRequest(username, password))
                .when()
                .post("/auth/login");
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int status) {
        assertThat(response.statusCode()).isEqualTo(status);
    }

    @And("the response should contain a token")
    public void theResponseShouldContainAToken() {
        TokenResponse body = response.as(TokenResponse.class);
        assertThat(body.getToken()).isNotBlank();
    }
}
