package org.acme.utils;

import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.entities.InventoryOrder;
import org.acme.input.InventoryInput;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class InventoryInputParserTest {

    @Inject
    InventoryInputParser parser;

    @Test
    public void testParseInventoryInputWithoutLines(){
        InventoryInput input = new InventoryInput("testOrderName", "testCustomerName", "DeliverLocation", List.of());
        InventoryOrder order = parser.parseInventoryInput(input);
        assertEquals("testOrderName", order.getOrderName());
        assertEquals("testCustomerName", order.getCustomerName());
        assertEquals("DeliverLocation", order.getDeliveryLocationName());
        assertEquals(0, order.getLines().size());
    }
}