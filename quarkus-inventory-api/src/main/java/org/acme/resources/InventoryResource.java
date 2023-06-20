package org.acme.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.InventoryOrder;
import org.acme.entities.InventoryOrderLine;
import org.acme.input.InventoryInput;
import org.acme.response.RequestResponse;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.sql.Timestamp;
import java.util.List;

@Path("/api/v1/inventory")
public class InventoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public RequestResponse getAllInventoryItems(){
        RequestResponse resp = new RequestResponse();
        resp.setContent(InventoryOrder.listAll());
        return resp;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/add")
    public Response createInventoryOrder(@RequestBody InventoryInput input){
        InventoryOrder order = new InventoryOrder();
        order.setOrderName(input.orderName());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setCustomerName(input.customerName());
        order.setDeliveryLocationName(input.deliveryLocationName());
        order.persist();
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @Path("/remove/{id}")
    public Response deleteInventoryOrder(@PathParam("id") Long id){
        boolean itemDeleted = InventoryOrder.deleteById(id);
        return Response.ok("Item deleted: " + itemDeleted).build();
    }
}
