package org.acme.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.acme.entities.InventoryOrder;
import org.acme.entities.InventoryOrderLine;


@Path("/api/v1/planning")
public class PlanningResource {

    @GET
    public String planResource(){
        return "Hello";
    }
}
