package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.input.InventoryInput;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class InventoryResourceTest {

    @Test
    public void testInventoryOrderCreation(){
        InventoryInput input = new InventoryInput("Test1", "Test2", "Test3");
        given()
                .contentType(ContentType.JSON)
                .body(input)
                .when().post("/api/v1/inventory/add")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllInventoryOrders(){
        given()
                .when().get("/api/v1/inventory/all")
                .then()
                .statusCode(200)
                .body("content.size()", Matchers.equalTo(0));
    }
}
