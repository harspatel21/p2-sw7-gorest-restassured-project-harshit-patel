package com.gorest.testsuite;
// **** Created By Harshit Patel ****

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        // RestAssured.port = 3030;
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001() {
        List<Integer> titals = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all Tital : " + titals);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        List<Integer> records = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total Number of Records are  : " + records.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String bodyOfFifteen = response.extract().path("body[13]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record : " + bodyOfFifteen);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all Users IDs are : " + userId);
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<Integer> titles = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all titles are : " + titles);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Extract the title of all records whose user_id = 5559
    @Test
    public void test006() {
        List<HashMap<?,?>> title = response.extract().path("findAll{it.user_id == 5559}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 5559 : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 2730
    @Test
    public void test007() {
        List<String> body = response.extract().path("findAll{it.id == 2730}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id = 2730 : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
