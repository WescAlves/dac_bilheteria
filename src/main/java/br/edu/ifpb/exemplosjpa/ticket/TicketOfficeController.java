package br.edu.ifpb.exemplosjpa.ticket;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ticket-office")
public class TicketOfficeController {

    private TicketOfficeService ticketOfficeService;

    public TicketOfficeController(TicketOfficeService ticketOfficeService) {
        this.ticketOfficeService = ticketOfficeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> validateTicket(@PathVariable UUID id){
        Ticket ticket = ticketOfficeService.findById(id);
        return ResponseEntity.ok(ticketOfficeService.validateTicket(ticket.toTicketDTO()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Ticket> confirmEntrance(@PathVariable UUID id){
        Ticket ticket = ticketOfficeService.findById(id);
        return ResponseEntity.ok(ticketOfficeService.confirmTicket(ticket.toTicketDTO()));
    }

}
