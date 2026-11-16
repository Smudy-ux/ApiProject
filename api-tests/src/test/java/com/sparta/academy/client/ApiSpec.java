package com.sparta.academy.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class ApiSpec {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    private ApiSpec() {
    }

    public static RequestSpecification base() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
