package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.InventoryOrder;
import org.acme.input.InventoryInput;
import org.acme.utils.InventoryInputParser;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hibernate.engine.spi.Status;

import java.util.List;
import java.util.Optional;

@Path("/api/v1/inventory")
public class InventoryResource {

    @Inject
    InventoryInputParser parser;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllInventoryItems(){
//        RequestResponse resp = new RequestResponse();
//        resp.setContent(InventoryOrder.listAll());
        List<InventoryOrder> orders = InventoryOrder.listAll();

        return Response.ok(orders).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{id}")
    public Response getSpecificInventoryItem(@PathParam("id") Long id){
        Optional<InventoryOrder> order = InventoryOrder.findByIdOptional(id);
        return order.isPresent() ? Response.ok(order.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/add")
    public Response createInventoryOrder(@RequestBody InventoryInput input){
        parser.parseInventoryInput(input).persist();
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @Path("/remove/{id}")
    public Response deleteInventoryOrder(@PathParam("id") Long id){
        boolean itemDeleted = InventoryOrder.deleteById(id);
        return itemDeleted ? Response.status(204).build() : Response.status(404).build();
    }
}
