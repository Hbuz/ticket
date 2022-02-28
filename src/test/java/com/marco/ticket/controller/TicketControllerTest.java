package com.marco.ticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.dto.TicketReqDTO;
import com.marco.ticket.exception.NotFoundException;
import com.marco.ticket.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void givenTicket_whenGetTicket_thenReturnJson() throws Exception {

        TicketDTO ticket = new TicketDTO(1, "token", LocalDateTime.now(), 123, LocalDateTime.now());
        given(ticketService.getTicket(1)).willReturn(ticket);

        mvc.perform(MockMvcRequestBuilders
                .get("/tickets/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user_id", is(ticket.getUserId())));
    }

    @Test
    public void givenATicket_whenGetTicket_andValueNotFound_thenReturnError() throws Exception {

        given(ticketService.getTicket(321)).willThrow(new NotFoundException());

        mvc.perform(MockMvcRequestBuilders
                .get("/tickets/{id}", 321)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenGetTickets_andNoValuesFound_thenReturnEmpty() throws Exception {

        given(ticketService.getTickets()).willReturn(new ArrayList<>());

        mvc.perform(MockMvcRequestBuilders
                .get("/tickets")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andReturn();
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws Exception {
        TicketReqDTO ticketReqDTO = new TicketReqDTO(123, LocalDateTime.now());

        mvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketReqDTO)))
                .andExpect(status().isOk())
                .andReturn();
    }
}
