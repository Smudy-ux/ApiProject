package com.sparta.academy.steps;

import com.sparta.academy.client.ApiSpec;
import com.sparta.academy.context.TestContext;
import com.sparta.academy.models.CourseItem;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CoursesSteps {
    private final TestContext context;

    public CoursesSteps(TestContext context) {
        this.context = context;
    }

    @When("I request all courses")
    public void iRequestAllCourses() {
        Response response = given()
                .spec(ApiSpec.courses())
                .when()
                .get();

        context.setResponse(response);
    }

    @And("the response should contain all courses")
    public void theResponseShouldContainAllCourses() {
        CourseItem[] courses = context.getResponse().as(CourseItem[].class);

        MatcherAssert.assertThat(courses.length, is(5));
        MatcherAssert.assertThat(courses[0].getId(), is(1));
        MatcherAssert.assertThat(courses[0].getName(), equalTo("TECH 300"));
        MatcherAssert.assertThat(courses[0].getStream(), equalTo("C# Dev"));
        MatcherAssert.assertThat(courses[0].getTrainer(), equalTo("Phil Windridge"));
        MatcherAssert.assertThat(courses[0].getSpartans(), hasItems("Sparty McFly", "Ada Lovelace"));
        MatcherAssert.assertThat(courses[0].getLinks().get(0).getHref(), equalTo("/api/Courses/1"));
    }
}