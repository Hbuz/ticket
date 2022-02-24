package com.marco.ticket.controller;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.dto.TicketReqDTO;
import com.marco.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController("TicketController")
@RequestMapping(path = "/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketReqDTO ticketReqDTO) {
        TicketDTO response = ticketService.createTicket(ticketReqDTO);
        return ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable("id") Integer id) {
        TicketDTO ticketDTO = ticketService.getTicket(id);
        return ok(ticketDTO);
    }

    @GetMapping()
    public ResponseEntity<List<TicketDTO>> getTickets() {
        List<TicketDTO> ticketDTO = ticketService.getTickets();
        return ok(ticketDTO);
    }
}
