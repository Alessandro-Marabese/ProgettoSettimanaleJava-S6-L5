package it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi;

import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.Dipendente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, nullable = false)
    private String destinazione;

    @Column(length = 100, nullable = false)
    private String dataPartenza;

    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

    @OneToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;


}
