package com.marco.ticket.service;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.dto.TicketReqDTO;
import com.marco.ticket.model.Ticket;
import com.marco.ticket.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketDTO createTicket(TicketReqDTO ticketReqDTO) {

        Ticket ticket;

        try {

            ticket = new Ticket();
            ticket.setToken(this.generateToken());
            ticket.setValidityDate(ticketReqDTO.getValidityDate());
            ticket.setUserId(ticketReqDTO.getUserId());

            ticket = ticketRepository.save(ticket);

        } catch (Exception e) {
            LOGGER.error("createTicket: FAILED", e.getMessage());
            throw e;
        }

        return this.toTicketDTO(ticket);
    }

    public void getTicket() {
        Optional<Ticket> ticket = ticketRepository.findById(1);
        if (ticket.isPresent()) {
            LOGGER.info("FOUND: {}", ticket.get().getToken());
        }
    }


    private String generateToken () {

        String token = "";
        // Some logic to create a random token with fixed size
        return token;
    }


    private TicketDTO toTicketDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO(
                ticket.getId(),
                ticket.getToken(),
                ticket.getValidityDate(),
                ticket.getUserId(),
                ticket.getCreatedOn()
        );

        return ticketDTO;
    }
}
