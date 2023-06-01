package org.acme.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.InventoryOrder;
import org.acme.entities.InventoryOrderLine;

import java.util.Arrays;
import java.util.List;

@Path("/api/v1/inventory")
public class InventoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/post")
    public String getAllInventoryItems(){
        InventoryOrder order = new InventoryOrder();
        order.orderName = "Hello";


        InventoryOrderLine line = new InventoryOrderLine();
        line.quantity = 5;

        InventoryOrderLine line2 = new InventoryOrderLine();
        line2.quantity = 5;

//        line.setInventoryOrder(order);
//        line2.setInventoryOrder(order);
        order.addInventoryLine(line);
        order.addInventoryLine(line2);
        order.persist();

        return "Done!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<InventoryOrder> getAll(){
        return InventoryOrder.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/line")
    public List<InventoryOrderLine> getAllInventoryLines(){
        return InventoryOrderLine.listAll();
    }
}
