package it.epicode.ProgettoSettimanaleJava_S6_L5.prenotazioni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneResponse {
    private Long id;
    private String dataRichiesta;
    private String note;
    private Long dipendenteId;
    private Long viaggioId;
}
