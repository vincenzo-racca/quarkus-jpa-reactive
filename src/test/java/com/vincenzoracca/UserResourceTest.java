package com.vincenzoracca;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vincenzoracca.quarkus.jpa.reactive.model.User;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testHelloEndpoint() throws JsonProcessingException {
        String response = objectMapper.writeValueAsString(
                List.of(
                        new User(1L, "Enzo", "Racca"),
                        new User(2L, "Beppe", "Antoni")
                )
        );
        given()
          .when().get("/users")
          .then()
             .statusCode(200)
             .body(is(response));
    }

}