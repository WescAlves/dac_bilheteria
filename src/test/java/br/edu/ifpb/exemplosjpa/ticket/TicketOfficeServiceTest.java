package br.edu.ifpb.exemplosjpa.ticket;

import br.edu.ifpb.exemplosjpa.exception.InvalidTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketOfficeServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketOfficeService ticketOfficeService;

    private Ticket ticket;
    private TicketDTO ticketDTO;

    @BeforeEach
    void setUp() {
        UUID ticketId = UUID.randomUUID();
        ticket = new Ticket(ticketId, 123L,"12345678900", true);  // Simulação de um ticket válido
        ticketDTO = new TicketDTO(ticket, "12345678900");  // DTO com CPF correto
    }

    @Test
    void validateTicket_ShouldReturnTrue_WhenTicketIsValid() {
        when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.of(ticket));

        boolean result = ticketOfficeService.validateTicket(ticketDTO);

        assertTrue(result);
        verify(ticketRepository, times(1)).findById(ticket.getId());
    }

    @Test
    void confirmTicket_ShouldThrowInvalidTicketException_WhenTicketIsInvalid() {
        when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.empty());

        assertThrows(InvalidTicketException.class, () -> ticketOfficeService.confirmTicket(ticketDTO));

        verify(ticketRepository, times(1)).findById(ticket.getId());
    }
}
