package com.marco.ticket.controller;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.dto.TicketReqDTO;
import com.marco.ticket.exception.NotFoundException;
import com.marco.ticket.exception.ValueNotValidException;
import com.marco.ticket.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController("TicketController")
@RequestMapping(path = "/tickets")
public class TicketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketReqDTO ticketReqDTO) {

        TicketDTO response;
        try {
            response = ticketService.createTicket(ticketReqDTO);

        } catch (ValueNotValidException e) {
            LOGGER.error("Failed createTicket request. Message:{}", e.getMessage());
            return badRequest().build();

        } catch (Exception e) {
            LOGGER.error("Failed createTicket request. Message:{}", e.getMessage());
            return internalServerError().build();
        }
        return ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable("id") Integer id) {

        TicketDTO ticketDTO;
        try {
            ticketDTO = ticketService.getTicket(id);

        } catch (NotFoundException e) {
            LOGGER.error("Failed getTicket request. Message:{}", e.getMessage());
            return notFound().build();

        } catch (Exception e) {
            LOGGER.error("Failed getTicket request. Message:{}", e.getMessage());
            return internalServerError().build();

        }
        return ok(ticketDTO);
    }

    @GetMapping()
    public ResponseEntity<List<TicketDTO>> getTickets() {

        List<TicketDTO> ticketDTO;
        try {
            ticketDTO = ticketService.getTickets();

        } catch (Exception e) {
            LOGGER.error("Failed getTickets request. Message:{}", e.getMessage());
            return internalServerError().build();

        }
        return ok(ticketDTO);
    }
}
