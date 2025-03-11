package br.edu.ifpb.exemplosjpa.amqp;

import br.edu.ifpb.exemplosjpa.ticket.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseEventConsumer {

    private TicketOfficeService ticketOfficeService;

    public PurchaseEventConsumer(TicketOfficeService ticketOfficeService) {
        this.ticketOfficeService = ticketOfficeService;
    }

    @RabbitListener(queues = {"purchase-queue"})
    public void processNewTicket(@Payload String json){
        try{ObjectMapper mapper = new ObjectMapper();
            PurchaseConsumerDTO dtos = mapper.readValue(json, PurchaseConsumerDTO.class);
            for (TicketConsumerDTO dto : dtos.ticketsDTO()) {
            Ticket ticket = new Ticket();
            ticket.setStatus(true);
            ticket.setCpf(dto.cpf());
            ticket.setReferencedId(dto.id());
            ticketOfficeService.create(ticket);
        };
        }catch (Exception e){
            System.out.println(e.getMessage());
        }




    }

//    @RabbitListener(queues = {"purchase-queue"}, containerFactory = "rabbitListenerContainerFactory")
//    public void processNewTicket (@Payload Message<PurchaseConsumerDTO> dtos) {
//        System.out.println(dtos + "dto puro");
//        System.out.println(dtos.getPayload() + "payload");
//        System.out.println(dtos.getPayload().ticketsDTO() + "Tickets");
//        for (TicketConsumerDTO dto : dtos.getPayload().ticketsDTO()) {
//            Ticket ticket = new Ticket();
//            ticket.setStatus(true);
//            ticket.setCpf(dto.cpf());
//            ticket.setReferencedId(dto.id());
//            ticketOfficeService.create(ticket);
//        };
//    }
}
