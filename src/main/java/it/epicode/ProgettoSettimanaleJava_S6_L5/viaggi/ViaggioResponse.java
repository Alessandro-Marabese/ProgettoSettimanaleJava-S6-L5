package it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViaggioResponse {
    private Long id;
    private String destinazione;
    private String dataPartenza;
    private StatoViaggio statoViaggio;
    private Long dipendenteId;
}
