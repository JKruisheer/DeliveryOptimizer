package org.acme.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.NormalScope;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Default;
import org.acme.entities.InventoryOrder;
import org.acme.entities.InventoryOrderLine;
import org.acme.input.InventoryInput;
import org.acme.input.InventoryLineInput;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InventoryInputParser {
    public InventoryOrder parseInventoryInput(InventoryInput input){
        InventoryOrder order = new InventoryOrder();
        order.setOrderName(input.orderName());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setCustomerName(input.customerName());
        order.setDeliveryLocationName(input.deliveryLocationName());
        List<InventoryOrderLine> lines = parseInventoryOrderLines(input.inventoryLines());
        lines.forEach(order::addInventoryLine);
        return order;
    }

    private List<InventoryOrderLine> parseInventoryOrderLines(List<InventoryLineInput> lineInput){
        List<InventoryOrderLine> lines = new ArrayList<>();
        if(lineInput == null || lineInput.size() == 0){
            return lines;
        }
        for(InventoryLineInput input : lineInput){
            InventoryOrderLine newLine = new InventoryOrderLine();
            newLine.quantity = input.quantity();
            lines.add(newLine);
        }
        return lines;
    }


}
