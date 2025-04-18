package it.epicode.ProgettoSettimanaleJava_S6_L5.prenotazioni;

import com.github.javafaker.Faker;
import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.DipendenteRepository;
import it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi.Viaggio;
import it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Order(3)
public class PrenotazioneRunner implements CommandLineRunner {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private ViaggioRepository viaggioRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private Faker faker;

    @Override
    public void run(String... args) throws Exception {
        if(prenotazioneRepository.count() == 0) {
                List<Viaggio> viaggi = viaggioRepository.findAll();
            if (viaggi.isEmpty()) {
                System.out.println("Nessun viaggio trovato. Impossibile creare prenotazioni.");
                return;
            }

                for (int i = 0; i < viaggi.size(); i++) {
                Prenotazione prenotazione = new Prenotazione();
                    Viaggio viaggio = viaggi.get(i);
                    prenotazione.setDataRichiesta(
                            LocalDate.parse(viaggio.getDataPartenza(), DateTimeFormatter.ISO_LOCAL_DATE)
                                    .minusDays(3)
                                    .format(DateTimeFormatter.ISO_LOCAL_DATE));
                prenotazione.setViaggio(viaggio);
                prenotazione.setDipendente(viaggio.getDipendente());
                prenotazione.setNote(faker.lorem().sentence());
                prenotazioneRepository.save(prenotazione);
                }
            }
    }
}
