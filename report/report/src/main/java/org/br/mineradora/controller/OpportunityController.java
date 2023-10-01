package org.br.mineradora.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.service.OpportunityService;

import java.util.Date;

@Path("api/opportunity")
public class OpportunityController {

    private final OpportunityService opportunityService;

    @Inject
    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }


    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateReport() {
        try {
            return Response.ok(opportunityService.generateCSVOpportunityReport(), MediaType.APPLICATION_OCTET_STREAM)
                   .header("content-disposition",
                           "attachment; filename = " + new Date() + "oportunidades-venda.csv")
                    .build();

        } catch (ServerErrorException errorException) {
            return Response.serverError().build();
        }
    }

}
