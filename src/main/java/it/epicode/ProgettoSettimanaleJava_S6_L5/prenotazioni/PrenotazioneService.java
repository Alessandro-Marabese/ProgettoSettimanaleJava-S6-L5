package it.epicode.ProgettoSettimanaleJava_S6_L5.prenotazioni;

import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.Dipendente;
import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.DipendenteRepository;
import it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi.Viaggio;
import it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi.ViaggioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public void delete(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        prenotazioneRepository.delete(prenotazione);
    }

    public PrenotazioneResponse findById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        PrenotazioneResponse response = new PrenotazioneResponse();
        BeanUtils.copyProperties(prenotazione, response);
        return response;
    }

    public Page<PrenotazioneResponse> findFull(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable).map(this::fromEntity);
    }

    public PrenotazioneResponse update(Long id, PrenotazioneRequest request) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        BeanUtils.copyProperties(request, prenotazione);
        prenotazioneRepository.save(prenotazione);
        return fromEntity(prenotazione);
    }

    public PrenotazioneResponse fromEntity(Prenotazione prenotazione) {
        PrenotazioneResponse response = new PrenotazioneResponse();
        BeanUtils.copyProperties(prenotazione, response);
        return response;
    }

    public PrenotazioneResponse save(@Valid PrenotazioneRequest request, Dipendente dipendente) {
        Prenotazione prenotazioneEsistente = prenotazioneRepository.findByDipendenteAndDataRichiesta(dipendente, request.getDataRichiesta());
        if(prenotazioneEsistente != null) {
            throw new RuntimeException("Il dipendente ha già un'altra prenotazione per quella data.");
        }

        dipendente = dipendenteRepository.findById(request.getDipendenteId())
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato."));

        Viaggio viaggio = viaggioRepository.findById(request.getViaggioId())
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato."));

        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(request, prenotazione);
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazioneRepository.save(prenotazione);
        return fromEntity(prenotazione);
    }
}
