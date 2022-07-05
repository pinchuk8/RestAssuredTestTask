package tests;

import baseEntities.BaseApiTest;
import io.restassured.mapper.ObjectMapperType;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;
import utils.Randomization;

import static io.restassured.RestAssured.given;

public class SwaggerTests extends BaseApiTest {
    @Test
    public void createAndGetUserTest() {
        User userCreated = User.builder()
                .id(123)
                .username(Randomization.getRandomString(5) + "_name")
                .email(Randomization.getRandomString(5) + "@gmail.com")
                .password(Randomization.getRandomString(5) + "_password")
                .userStatus(0)
                .build();
        given()
                .when()
                .body(userCreated, ObjectMapperType.GSON)
                .post(Endpoints.CREATE_USER)
                .then()
                .statusCode(HttpStatus.SC_OK);
        User userFromGetRequest = given()
                .pathParam("username", userCreated.getUsername())
                .when()
                .get(Endpoints.GET_USER_BY_NAME)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(User.class);
        Assert.assertEquals(userCreated, userFromGetRequest, "Users from POST and GET requests are equal");
    }
}

