package it.epicode.ProgettoSettimanaleJava_S6_L5.prenotazioni;

import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.Dipendente;
import it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi.Viaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String dataRichiesta;

    @Column(length = 500)
    private String note;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
}
