package it.epicode.ProgettoSettimanaleJava_S6_L5.prenotazioni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequest {
    private Long dipendenteId;
    private Long viaggioId;
    private String dataRichiesta;
    private String note;
}
