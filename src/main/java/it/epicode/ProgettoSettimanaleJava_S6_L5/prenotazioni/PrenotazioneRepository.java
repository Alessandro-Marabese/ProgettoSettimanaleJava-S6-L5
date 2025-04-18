package it.epicode.ProgettoSettimanaleJava_S6_L5.prenotazioni;

import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Prenotazione findByDipendenteAndDataRichiesta(Dipendente dipendente, String dataRichiesta);
}
