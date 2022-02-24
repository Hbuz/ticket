package com.marco.ticket.service;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.dto.TicketReqDTO;
import com.marco.ticket.model.Ticket;
import com.marco.ticket.repository.TicketRepository;
import com.marco.ticket.util.ObjectSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    private final TicketRepository ticketRepository;
    private final ObjectSerializer objectSerializer;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ObjectSerializer objectSerializer) {
        this.ticketRepository = ticketRepository;
        this.objectSerializer = objectSerializer;
    }

    public TicketDTO createTicket(TicketReqDTO ticketReqDTO) {

        Ticket ticket;

        try {

            ticket = new Ticket();
            ticket.setToken(this.generateToken());
            ticket.setValidityDate(ticketReqDTO.getValidityDate());
            ticket.setUserId(ticketReqDTO.getUserId());
            ticket.setCreatedOn(LocalDateTime.now());
            ticket = ticketRepository.save(ticket);

        } catch (Exception e) {
            LOGGER.error("createTicket: FAILED", e.getMessage());
            throw e;
        }

        return objectSerializer.toTicketDTO(ticket);
    }

    public TicketDTO getTicket(Integer id) {

        Optional<Ticket> ticketOpt = ticketRepository.findById(id);
        if (ticketOpt.isPresent()) {

            LOGGER.debug("FOUND: {}", ticketOpt.get().getToken());

            return objectSerializer.toTicketDTO(ticketOpt.get());
        }
        return null;
    }

    public List<TicketDTO> getTickets() {
        List<TicketDTO> ticketsDTO = new ArrayList<>();
        ticketRepository.findAll().stream().forEach(ticket -> {
            ticketsDTO.add(objectSerializer.toTicketDTO(ticket));
        });
        return ticketsDTO;
    }

    private String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
