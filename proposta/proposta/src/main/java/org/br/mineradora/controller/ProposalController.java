package org.br.mineradora.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/proposal")
public class ProposalController {

    private final ProposalService proposalService;
    private final Logger LOG = LoggerFactory.getLogger(ProposalController.class);

    @Inject
    public ProposalController(ProposalService proposalService){
        this.proposalService = proposalService;
    }

    @GET
    @Path("/{id}")
    public ProposalDetailsDTO findDetailsProposal(@PathParam("id") long id){
        return proposalService.findFullProposal(id);
    }

    @POST
    public Response createProposal(ProposalDetailsDTO proposalDetails){
        LOG.info("-- Recebendo proposta de compra --");

        try{
            proposalService.createNewProposal(proposalDetails);
            return Response.ok().build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeProposal(@PathParam("id") long id){
        try{
            proposalService.removeProposal(id);
            return Response.ok().build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }
}
