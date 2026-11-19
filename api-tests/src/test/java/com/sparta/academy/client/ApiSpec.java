package com.sparta.academy.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class ApiSpec {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");
    private static final String COURSES_PATH = System.getProperty("coursesPath", "/api/Courses");
    private static final String SPARTANS_PATH = System.getProperty("spartansPath", "/api/spartans");

    private ApiSpec() {
    }

    public static RequestSpecification base() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification courses() {
        return new RequestSpecBuilder()
                .addRequestSpecification(base())
                .setBasePath(COURSES_PATH)
                .build();
    }

    public static RequestSpecification spartans() {
        return new RequestSpecBuilder()
                .addRequestSpecification(base())
                .setBasePath(SPARTANS_PATH)
                .build();
    }
}