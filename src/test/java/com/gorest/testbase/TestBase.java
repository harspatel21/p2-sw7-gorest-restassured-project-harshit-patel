package com.gorest.testbase;
// **** Created By Harshit Patel ****

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
     //   RestAssured.port = 8080;
        RestAssured.basePath = "/users?page=1&per_page=20";
    }
}
