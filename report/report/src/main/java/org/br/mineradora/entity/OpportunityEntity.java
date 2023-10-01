package org.br.mineradora.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="opportunity")
@Data
@NoArgsConstructor
public class OpportunityEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @Column(name = "proposal_id")
    private Long proposalId;

    private String customer;

    @Column(name = "price_tonne")
    private BigDecimal priceTonne;

    @Column(name = "last_dollar_quotation")
    private BigDecimal lastDollarQuotation;
}
