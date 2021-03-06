package com.marco.ticket.repository;

import com.marco.ticket.model.Ticket;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@WebAppConfiguration
@Transactional
public class TicketRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    private Ticket ticket1;
    private LocalDateTime validityDate;

    @BeforeEach
    public void setUp() {
        validityDate = LocalDateTime.of(2022, 2, 25, 0, 0);

        ticket1 = new Ticket();
        ticket1.setToken("token");
        ticket1.setValidityDate(validityDate);
        ticket1.setUserId(123);
        ticket1.setCreatedOn(LocalDateTime.now());
    }

    @Test
    public void whenFindById_thenReturnTicket() {

        entityManager.persist(ticket1);
        entityManager.flush();

        Optional<Ticket> found = ticketRepository.findById(ticket1.getId());

        Assert.assertNotNull(found.get());
        Assert.assertEquals(found.get().getToken(), ticket1.getToken());
    }
}
