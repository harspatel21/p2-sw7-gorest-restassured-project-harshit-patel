package com.gorest.testsuite;
// **** Created By Harshit Patel ****

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        // RestAssured.port = 3030;
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the all Ids
    @Test
    public void test01() {
        List<Integer> allIds = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Ids  : " + allIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test02() {
        List<String> allNames = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Names  : " + allNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test03() {
        String fifthObjectName = response.extract().path("name[4]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of 5th Object  : " + fifthObjectName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test04() {
        List<String> values = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Names of all object whose status = inactive  : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test05() {
        List<String> values = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender of all the object whose status = active  : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test06() {
        List<String> values = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Names of the object whose gender = female  : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test07() {
        List<String> values = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Emails of the object where status = inactive  : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test08() {
        List<String> values = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Ids of the object where gender = male  : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test09() {
        List<String> allStatus = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All status  : " + allStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Sen. Abhaya Butt
    @Test
    public void test010() {
        List<String> values = response.extract().path("findAll{it.name == 'Sen. Abhaya Butt'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("email of the object where name = Sen. Abhaya Butt  : " + values);
        System.out.println("------------------End of Test---------------------------");
    }


    //11. Get gender of id = 5305
    @Test
    public void test011() {
        List<Integer> values = response.extract().path("findAll{it.id == 5305}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender of id = 5305  : " + values);
        System.out.println("------------------End of Test---------------------------");
    }
}
