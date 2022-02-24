package com.marco.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class TicketReqDTO {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("validity_date")
    private LocalDateTime validityDate;
}
