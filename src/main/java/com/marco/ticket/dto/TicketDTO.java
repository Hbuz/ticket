package com.marco.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TicketDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("token")
    private String token;
    @JsonProperty("validity_date")
    private LocalDateTime validityDate;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("created_on")
    private LocalDateTime createdOn;
}
