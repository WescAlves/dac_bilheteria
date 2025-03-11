package br.edu.ifpb.exemplosjpa.ticket;

import br.edu.ifpb.exemplosjpa.exception.InvalidTicketException;
import br.edu.ifpb.exemplosjpa.exception.TicketNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketOfficeService {

    private TicketRepository ticketRepository;

    public TicketOfficeService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public boolean validateTicket(TicketDTO dto){
        Ticket ticket = ticketRepository.findById(dto.ticket().getId()).orElse(null);
        if(ticket != null){
            return ticket.isAssignedTo(dto.cpf());
        }
        return false;
    }

    public Ticket confirmTicket(TicketDTO dto){
        if(validateTicket(dto)){
            Ticket ticket = ticketRepository.findById(dto.ticket().getId()).orElseThrow(TicketNotFoundException::new);
            ticket.setStatus(false);
            ticketRepository.save(ticket);
            return ticket;
        }
        else{ throw new InvalidTicketException();}
    }

    public Ticket create(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Ticket findById(UUID id){
        return ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
    }
}
