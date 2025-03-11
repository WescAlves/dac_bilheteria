package br.edu.ifpb.exemplosjpa.exception;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException() {
        super("This ticket is no longer valid, maybe it has already been used!");
    }
}
