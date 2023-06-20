package org.acme.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.transaction.*;
import org.acme.entities.InventoryOrder;
import org.acme.input.InventoryInput;
import org.acme.input.InventoryLineInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
public class InventoryResourceTest {

    @BeforeEach
    @Transactional
    public void insertBasicRecord(){
        InventoryOrder order = new InventoryOrder();
        order.setOrderName("Dummy order");
        order.persist();
    }

    @Test
    public void testInventoryOrderCreationWithoutLines() {
        InventoryInput input = new InventoryInput("Test1", "Test2", "Test3", List.of());
        given()
                .contentType(ContentType.JSON)
                .body(input)
                .when().post("/api/v1/inventory/add")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInventoryOrderCreationWithLines() {
        InventoryLineInput line = new InventoryLineInput(5);
        InventoryLineInput line2 = new InventoryLineInput(10);
        InventoryInput input = new InventoryInput("Test1", "Test2",
                "Test3", List.of(line, line2));
        given()
                .contentType(ContentType.JSON)
                .body(input)
                .when().post("/api/v1/inventory/add")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllInventoryOrders() {
        given()
                .when().get("/api/v1/inventory/all")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    public void testDeleteNonExistingInventoryOrder() {
        String nonExistingId = "999999999";
        given()
                .when()
                .pathParam("id", nonExistingId)
                .delete("/api/v1/inventory/remove/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteInventoryOrder() {
        given()
                .when()
                .pathParam("id", 1)
                .delete("/api/v1/inventory/remove/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void testGetExistingInventoryOrder(){
        given()
                .when()
                .pathParam("id", 1).get("/api/v1/inventory/find/{id}")
                .then()
                .statusCode(200)
                .body("orderName", equalTo("Dummy order"));
    }

    @Test
    public void testGetNonExistingInventoryOrder(){
        given().when()
                .pathParam("id", 811).get("/api/v1/inventory/find/{id}")
                .then()
                .statusCode(404);
    }


}
