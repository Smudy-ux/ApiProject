package com.sparta.academy.steps;

import com.sparta.academy.client.ApiSpec;
import com.sparta.academy.context.TestContext;
import com.sparta.academy.models.SpartansItem;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SpartansSteps {
    private final TestContext context;
    private SpartansItem newSpartanRequest;

    public SpartansSteps(TestContext context) {
        this.context = context;
    }

    private RequestSpecification spartanRequest() {
        RequestSpecification request = given().spec(ApiSpec.spartans());
        if (context.getAuthHeader() != null) {
            request.header("Authorization", context.getAuthHeader());
        }
        return request;
    }

    @And("I request all spartans")
    public void iRequestAllSpartans() {
        Response response = spartanRequest()
                .when()
                .get();

        context.setResponse(response);
    }

    @And("I create a spartan with valid details")
    public void iCreateASpartanWithValidDetails() {
        newSpartanRequest = new SpartansItem(
                "Jane",
                "Smith",
                "Oxford University",
                "Computer Science",
                2, // Course ID 2 = TECH 301 (Java Dev)
                true
        );

        Response response = spartanRequest()
                .body(newSpartanRequest)
                .when()
                .post();

        context.setResponse(response);
    }

    @And("I create a spartan with firstName {string}, lastName {string}, university {string}, degree {string}, courseId {int}, and graduated {string}")
    public void iCreateASpartanWithInvalidDetails(String firstName, String lastName, String university, String degree, int courseId, String graduated) {
        SpartansItem invalidSpartan = new SpartansItem(
                firstName,
                lastName,
                university,
                degree,
                courseId,
                Boolean.parseBoolean(graduated)
        );

        Response response = spartanRequest()
                .body(invalidSpartan)
                .when()
                .post();

        context.setResponse(response);
    }

    @And("the response should contain all spartans")
    public void theResponseShouldContainAllSpartans() {
        SpartansItem[] spartans = context.getResponse().as(SpartansItem[].class);

        MatcherAssert.assertThat(spartans.length, greaterThan(0));
        MatcherAssert.assertThat(spartans[0].getId(), notNullValue());
        MatcherAssert.assertThat(spartans[0].getFirstName(), not(emptyOrNullString()));
        MatcherAssert.assertThat(spartans[0].getLastName(), not(emptyOrNullString()));
        MatcherAssert.assertThat(spartans[0].getStream(), not(emptyOrNullString()));
        MatcherAssert.assertThat(spartans[0].getCourse(), not(emptyOrNullString()));
    }

    @And("the response should contain the created spartan details")
    public void theResponseShouldContainTheCreatedSpartanDetails() {
        SpartansItem createdSpartan = context.getResponse().as(SpartansItem.class);

        MatcherAssert.assertThat(createdSpartan.getId(), notNullValue());
        MatcherAssert.assertThat(createdSpartan.getFirstName(), equalTo(newSpartanRequest.getFirstName()));
        MatcherAssert.assertThat(createdSpartan.getLastName(), equalTo(newSpartanRequest.getLastName()));
        MatcherAssert.assertThat(createdSpartan.getUniversity(), equalTo(newSpartanRequest.getUniversity()));
        MatcherAssert.assertThat(createdSpartan.getDegree(), equalTo(newSpartanRequest.getDegree()));
        MatcherAssert.assertThat(createdSpartan.isGraduated(), is(newSpartanRequest.isGraduated()));
        MatcherAssert.assertThat(createdSpartan.getCourse(), equalTo("TECH 301"));
        MatcherAssert.assertThat(createdSpartan.getStream(), equalTo("Java Dev"));
    }
}