package com.marco.ticket.service;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.dto.TicketReqDTO;
import com.marco.ticket.model.Ticket;
import com.marco.ticket.repository.TicketRepository;
import com.marco.ticket.util.ObjectSerializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    TicketService ticketService;

    @Spy
    ObjectSerializer objectSerializer;

    private LocalDateTime validityDate;
    private Ticket ticket1;
    private TicketReqDTO ticketReqDTO;

    @Before
    public void setUp() {
        validityDate = LocalDateTime.of(2022, 2, 25, 0, 0);

        ticket1 = new Ticket();
        ticket1.setToken("token");
        ticket1.setValidityDate(validityDate);
        ticket1.setUserId(123);
        ticket1.setCreatedOn(LocalDateTime.now());

        ticketReqDTO = new TicketReqDTO(123, validityDate);
    }

    @Test
    public void whenCreateTicket_thenReturnTicket() {

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket1);
        TicketDTO created = ticketService.createTicket(ticketReqDTO);

        assertThat(created.getValidityDate()).isSameAs(ticketReqDTO.getValidityDate());
    }
}
