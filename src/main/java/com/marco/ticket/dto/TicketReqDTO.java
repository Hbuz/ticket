package com.marco.ticket.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TicketReqDTO {

    private Integer userId;

    private LocalDateTime validityDate;

    private String accessToken;
}
