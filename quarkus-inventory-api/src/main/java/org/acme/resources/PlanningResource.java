package org.acme.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/v1/planning")
public class PlanningResource {

    @GET
    public String planResource(){
        return "Hello";
    }
}
