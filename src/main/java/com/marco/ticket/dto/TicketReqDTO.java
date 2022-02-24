package com.marco.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TicketReqDTO {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("validity_date")
    private LocalDateTime validityDate;
}
