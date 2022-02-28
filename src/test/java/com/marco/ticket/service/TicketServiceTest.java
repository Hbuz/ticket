package com.marco.ticket.service;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.dto.TicketReqDTO;
import com.marco.ticket.exception.NotFoundException;
import com.marco.ticket.exception.ValueNotValidException;
import com.marco.ticket.model.Ticket;
import com.marco.ticket.repository.TicketRepository;
import com.marco.ticket.util.ObjectSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
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

    @BeforeEach
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
    public void whenCreateTicket_andParamIsValid_thenReturnTicket() {

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket1);
        TicketDTO created = ticketService.createTicket(ticketReqDTO);

        assertThat(created.getValidityDate()).isSameAs(ticketReqDTO.getValidityDate());
    }

    @Test
    public void whenCreateTicket_andParamIsNotValid_thenTriggerException() {

        TicketReqDTO ticketReqDTO = new TicketReqDTO(123, null);

        ValueNotValidException thrown = assertThrows(ValueNotValidException.class,
                () -> ticketService.createTicket(ticketReqDTO));

        assertTrue(thrown.getMessage().contains("The value is not valid"));
    }

    @Test
    public void whenCreateTicket_andExceptionIsTriggered_thenTriggerException() {

        when(ticketRepository.save(any(Ticket.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> ticketService.createTicket(ticketReqDTO));
    }

    @Test
    public void whenGetTicket_andParamIsValid_thenReturnTicket() {

        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.of(ticket1));
        TicketDTO ticketDTO = ticketService.getTicket(123);

        assertEquals(validityDate, ticketDTO.getValidityDate());
        assertEquals("token", ticketDTO.getToken());
        assertEquals(123, ticketDTO.getUserId());
    }

    @Test
    public void whenGetTicket_andNotFound_thenTriggerException() {

        when(ticketRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> ticketService.getTicket(111));

        assertTrue(thrown.getMessage().contains("No ticket found"));
    }

    @Test
    public void whenGetTickets_andNotFound_thenReturnEmpty() {

        when(ticketRepository.findAll()).thenReturn(new ArrayList<>());

        List<TicketDTO> ticketDTOList = ticketService.getTickets();

        assertEquals(0, ticketDTOList.size());
    }
}
