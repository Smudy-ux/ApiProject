package com.sparta.academy.context;

import io.restassured.response.Response;

public class TestContext {
    private Response response;
    private String authHeader;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
    }
}