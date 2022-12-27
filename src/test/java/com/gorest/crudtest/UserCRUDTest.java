package com.gorest.crudtest;
// **** Created By Harshit Patel ****

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestUtils {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
        response = given()
                .when()
                .get("")
                .then().statusCode(200); //method type of this is validatable response
    }

    @Test
    public void verifyUserCreatedSuccessfully(){

        UserPojo userPojo = new UserPojo();

        userPojo.setName("Raja Ram");
        userPojo.setEmail("Raja.ram1232" + getRandomValue() + "@gmail.com");
        userPojo.setGender("male" );
        userPojo.setStatus("active");

        Response response = given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 462a06c01a6794ba55bbf40f373f2bffc6b43ddaa3abc196316f3c80813c6ceb")// add the token here from postman
                .when()
                .body(userPojo)
                .post();//change here
        response.prettyPrint();
        response.then().log().all().statusCode(201);

    }
    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();

        userPojo.setName("Raja Ram");
        userPojo.setEmail("Ram.RajaABCD" + getRandomValue() + "@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");

        Response response = given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 462a06c01a6794ba55bbf40f373f2bffc6b43ddaa3abc196316f3c80813c6ceb")
                .body(userPojo)
                .pathParam("id", 11738) //passing parameter to .get()
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void VerifyUserDeleteSuccessfully() {

        UserPojo userPojo = new UserPojo();

        Response response = given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 462a06c01a6794ba55bbf40f373f2bffc6b43ddaa3abc196316f3c80813c6ceb")
                .body(userPojo)
                .pathParam("id", 11738) //passing parameter to .get()
                .when()
                .delete("/{id}");
        response.then().statusCode(204);
        response.prettyPrint();
    }



}
