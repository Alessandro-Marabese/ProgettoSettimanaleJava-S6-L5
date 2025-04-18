package it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi;

import com.github.javafaker.Faker;
import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.Dipendente;
import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Order(2)
public class ViaggioRunner implements CommandLineRunner {
    @Autowired
    private ViaggioRepository viaggioRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private Faker faker;

    @Override
    public void run(String... args) throws Exception {
        if(viaggioRepository.count() == 0) {
            List<Dipendente> dipendenti = dipendenteRepository.findAll();
            for(int i = 0; i < 10; i++) {
                Viaggio viaggio = new Viaggio();
                viaggio.setDestinazione(faker.address().cityName());
                viaggio.setDataPartenza(
                        faker.date().birthday()
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .format(DateTimeFormatter.ISO_LOCAL_DATE));
                viaggio.setStatoViaggio(StatoViaggio.IN_PROGRAMMA);
                viaggio.setDipendente(dipendenti.get(i));
                viaggioRepository.save(viaggio);
            }
        }
    }
}
