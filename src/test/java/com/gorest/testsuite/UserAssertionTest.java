package com.gorest.testsuite;
// **** Created By Harshit Patel ****

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
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

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("total.size().toString()",equalTo("20"));
    }

    //2. Verify the if the name of id = 5317 is equal to ”Raj Patil”
    @Test
    public void test002() {
        response.body("[4].name",equalTo("Raj Patil"));
    }

    //3. Check the single ‘Name’ in the Array list (Kashyap Prajapat)
    @Test
    public void test003() {
        response.body("name", hasItem("Kashyap Prajapat"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Chandak Deshpande, Laal Kaul, Indra Jha MD)
    @Test
    public void test004() {
        response.body("name",hasItems("Chandak Deshpande", "Laal Kaul", "Indra Jha MD"));
    }

    //5. Verify the email of userid = 5321 is equal “kaul_dhana@nienow.name”
    @Test
    public void test005() {
        response.body("email[6]",equalTo("ganesh_kaniyar@white.org"));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response.body("status[6]",equalTo("active"));
    }

    //7. Verify the Gender = female of user name is “Aashritha Bhattathiri“
    @Test
    public void test007() {
        response.body("gender[2]",equalTo("female"));
        response.body("name[2]",equalTo("Aashritha Bhattathiri"));
    }
}
