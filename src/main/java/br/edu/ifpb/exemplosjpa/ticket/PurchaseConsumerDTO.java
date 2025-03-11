package br.edu.ifpb.exemplosjpa.ticket;

import java.util.List;

public record PurchaseConsumerDTO(List<TicketConsumerDTO> ticketsDTO) {
    public PurchaseConsumerDTO {

        if (ticketsDTO == null) {
            System.out.println("Está vazio");
            ticketsDTO = List.of();
        }
        else {System.out.println("Não está vazio");};

    }
}
