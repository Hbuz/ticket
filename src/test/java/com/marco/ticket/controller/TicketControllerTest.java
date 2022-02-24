package com.marco.ticket.controller;

import com.marco.ticket.dto.TicketDTO;
import com.marco.ticket.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TicketService ticketService;

    @Test
    public void givenTicket_whenGetTicket_thenReturnJson() throws Exception {

        TicketDTO ticket = new TicketDTO(1, "token", LocalDateTime.now(), 123, LocalDateTime.now());
        given(ticketService.getTicket(1)).willReturn(ticket);

        mvc.perform(MockMvcRequestBuilders
                .get("/ticket/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user_id", is(ticket.getUserId())));
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws Exception {
//        TicketReqDTO ticketReqDTO = new TicketReqDTO(123, LocalDateTime.now());

        mvc.perform(post("/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"validity_date\":\"2022-02-25T00:00\",\"userId\":1}"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
