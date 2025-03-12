package br.edu.ifpb.exemplosjpa.ticket;

import br.edu.ifpb.exemplosjpa.exception.InvalidTicketException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TicketOfficeServiceTesteIT {

    @Autowired
    private TicketOfficeService ticketOfficeService;

    @MockBean
    private TicketRepository ticketRepository;

    @Test
    public void testCreateTicket() {
        // Arrange
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setCpf("12345678900");
        ticket.setStatus(true);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        // Act
        Ticket createdTicket = ticketOfficeService.create(ticket);

        // Assert
        assertThat(createdTicket).isNotNull();
        assertThat(createdTicket.getCpf()).isEqualTo("12345678900");
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void testConfirmTicket_InvalidTicket() {
        // Arrange
        UUID ticketId = UUID.randomUUID();
        Ticket ticket = mock(Ticket.class);
        when(ticket.getId()).thenReturn(ticketId);
        when(ticket.isAssignedTo("98765432100")).thenReturn(false);

        TicketDTO ticketDTO = new TicketDTO(ticket, "98765432100");

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        // Act & Assert
        assertThrows(InvalidTicketException.class, () -> ticketOfficeService.confirmTicket(ticketDTO));
        verify(ticketRepository, times(1)).findById(ticketId);
    }
}
