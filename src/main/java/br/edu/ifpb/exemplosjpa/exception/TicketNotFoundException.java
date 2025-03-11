package br.edu.ifpb.exemplosjpa.exception;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException() {
        super("Ticket was not found, so that's not valid!");
    }
}
