package com.marco.ticket.util;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class ObjectSerializer {

    public TicketDTO toTicketDTO(Ticket ticket) {
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
