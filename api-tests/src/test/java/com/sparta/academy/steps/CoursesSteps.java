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
    private int requestedCourseId;

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

    @When("I request course {int}")
    public void iRequestCourse(int id) {
        this.requestedCourseId = id;

        Response response = given()
                .spec(ApiSpec.courses())
                .pathParam("id", id)
                .when()
                .get("/{id}");

        context.setResponse(response);
    }

    @When("I request course {string}")
    public void iRequestCourseWithInvalidId(String id) {
        Response response = given()
                .spec(ApiSpec.courses())
                .pathParam("id", id)
                .when()
                .get("/{id}");

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

    @And("the response should contain course details")
    public void theResponseShouldContainCourseDetails() {
        CourseItem course = context.getResponse().as(CourseItem.class);

        MatcherAssert.assertThat(course.getId(), is(requestedCourseId));
        MatcherAssert.assertThat(course.getName(), equalTo("TECH 300"));
        MatcherAssert.assertThat(course.getStream(), equalTo("C# Dev"));
        MatcherAssert.assertThat(course.getTrainer(), equalTo("Phil Windridge"));
        MatcherAssert.assertThat(course.getStartDate(), equalTo("2023-03-01T00:00:00"));
        MatcherAssert.assertThat(course.getEndDate(), equalTo("2023-05-01T00:00:00"));
        MatcherAssert.assertThat(course.getSpartans(), hasItems("Sparty McFly", "Ada Lovelace"));
        MatcherAssert.assertThat(course.getLinks().get(0).getHref(), equalTo("/api/Courses/" + requestedCourseId));
    }
}