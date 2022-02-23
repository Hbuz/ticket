package com.marco.ticket.repository;

import com.marco.ticket.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A persistence repository for the Ticket entity.
 *
 * @author Marco Basilico
 */
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}
