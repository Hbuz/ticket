package com.marco.ticket.controller;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.dto.TicketReqDTO;
import com.marco.ticket.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController("TicketController")
@RequestMapping(path = "/ticket")
public class TicketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketReqDTO ticketReqDTO) throws Exception {
        TicketDTO response = ticketService.createTicket(ticketReqDTO);
        return ok(response);
    }

    @GetMapping()
    public ResponseEntity<Object> getTicket() throws Exception {
        ticketService.getTicket();
        return ok().build();
    }
}
