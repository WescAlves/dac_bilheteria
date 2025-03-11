package br.edu.ifpb.exemplosjpa.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long referencedId;

    private String cpf;

    private Boolean status;


    public Boolean isAssignedTo(String cpf){
        return this.cpf.equals(cpf) && this.status;
    }

    public TicketDTO toTicketDTO(){
        return new TicketDTO(this, this.cpf);
    }

}
