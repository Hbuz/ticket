package com.marco.ticket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "validity_date", nullable = false, updatable = false)
    private LocalDateTime validityDate;

    @Column(name = "user_id", nullable = true)
    private Integer userId;

    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;
}
