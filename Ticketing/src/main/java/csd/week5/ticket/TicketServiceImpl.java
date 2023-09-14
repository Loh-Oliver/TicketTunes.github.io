package csd.week5.ticket;

import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImpl implements TicketService {
   
    private TicketRepository tickets;
    

    public TicketServiceImpl(TicketRepository tickets){
        this.tickets = tickets;
    }

    @Override
    public List<Ticket> listTickets() {
        return tickets.findAll();
    }

    
    @Override
    public Ticket getTicket(Long id){
        return tickets.findById(id).orElse(null);
    }
    
    @Override
    public Ticket addTicket(Ticket book) {
        return tickets.save(book);
    }
    
    @Override
    public Ticket updateTicket(Long id, Ticket newTicketInfo){
        return tickets.findById(id).map(ticket -> {ticket.setTitle(newTicketInfo.getTitle());
            return tickets.save(ticket);
        }).orElse(null);

    }

    /**
     * Remove a book with the given id
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a book will also remove all its associated reviews
     */
    @Override
    public void deleteTicket(Long id){
        tickets.deleteById(id);
    }
}