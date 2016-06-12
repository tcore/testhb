package com.tcore.web;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.tcore.TesthbApplication;
import com.tcore.dto.CreateUserDto;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TesthbApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserControllerTest {

    @Value("${local.server.port}")
    private int port;

    private CreateUserDto createUserDto = new CreateUserDto();

    private String path = "users/{userId}";

    @Before
    public void setUp() {
        RestAssured.port = port;

        createUserDto.setLastName("John");
        createUserDto.setFirstName("Bern");
        createUserDto.setEmail("email@test.com");
        createUserDto.setPassword("password");
    }

    public void testFind() {
        Integer id = createUser(createUserDto);

        when()
                .get(path, id)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("firstName", is(createUserDto.getFirstName()))
                .body("lastName", is(createUserDto.getLastName()))
                .body("email", is(createUserDto.getEmail()))
        ;
    }

    @Test
    public void testUserCreate() {
        createUser(createUserDto);
    }

    @Test
    public void testDelete() {
        Integer id = createUser(createUserDto);

        when().get(path, id).then().statusCode(HttpStatus.SC_OK);
        when().delete(path, id).then().statusCode(HttpStatus.SC_OK);
        when().get(path, id).then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testDeleteNotFound() {
        when().delete(path, Long.MAX_VALUE).then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testUserNotFound() {
        when().get(path, Long.MAX_VALUE).then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private Integer createUser(CreateUserDto userDto) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", userDto.getFirstName());
        jsonAsMap.put("lastName", userDto.getLastName());
        jsonAsMap.put("email", userDto.getEmail());
        jsonAsMap.put("password", userDto.getPassword());

        return given()
                .contentType(ContentType.JSON)
                .body(jsonAsMap).
        when()
                .post("users").
        then()
                .statusCode(HttpStatus.SC_OK)
                .body("firstName", is(userDto.getFirstName()))
                .body("lastName", is(userDto.getLastName()))
                .body("email", is(userDto.getEmail()))
        .extract().path("id")
        ;
    }
}
