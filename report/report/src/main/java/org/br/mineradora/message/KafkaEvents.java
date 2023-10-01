package org.br.mineradora.message;

import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.service.OpportunityService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaEvents {

    private final OpportunityService opportunityService;
    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    @Inject
    public KafkaEvents(OpportunityService opportunityService)  {
        this.opportunityService = opportunityService;
    }

    @Incoming("proposal")
    @Transactional
    public void receiveProposal(ProposalDTO proposal) {
        LOG.info("-- Recebendo nova proposta do topico Kafka");
        opportunityService.buildOpportunity(proposal);
    }

    @Incoming("quotation")
    @Blocking
    public void receiveQuotation(QuotationDTO quotation){
        LOG.info("-- Recebendo nova cotacao de moeda do topico Kafka");
        opportunityService.saveQuotation(quotation);
    }
}
