package org.acme.input;

import java.util.List;

public record InventoryInput(String orderName,
                             String customerName,
                             String deliveryLocationName,
                             List<InventoryLineInput> inventoryLines) {
}
