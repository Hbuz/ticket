package com.marco.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TicketDTO {

    private Integer id;

    private String token;

    private LocalDateTime validityDate;

    private Integer userId;

    private LocalDateTime createdOn;
}
