package it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi;

import it.epicode.ProgettoSettimanaleJava_S6_L5.common.CommonResponse;
import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.Dipendente;
import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.DipendenteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public CommonResponse save(@Valid ViaggioRequest request) {
        Viaggio viaggio = new Viaggio();
        BeanUtils.copyProperties(request, viaggio);
        viaggioRepository.save(viaggio);
        return new CommonResponse(viaggio.getId());
    }

    public void delete(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        viaggioRepository.delete(viaggio);
    }

    public ViaggioResponse findById(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        ViaggioResponse response = new ViaggioResponse();
        BeanUtils.copyProperties(viaggio, response);
        return response;
    }

    public Page<ViaggioResponse> findFull(Pageable pageable) {
        return viaggioRepository.findAll(pageable).map(this::fromEntity);
    }

    public ViaggioResponse update(Long id, ViaggioRequest request) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        BeanUtils.copyProperties(request, viaggio);
        viaggioRepository.save(viaggio);
        return fromEntity(viaggio);
    }

    public ViaggioResponse fromEntity(Viaggio viaggio) {
        ViaggioResponse response = new ViaggioResponse();
        BeanUtils.copyProperties(viaggio, response);
        if (viaggio.getDipendente() != null) {
            response.setDipendenteId(viaggio.getDipendente().getId());
        }
        return response;
    }

    public ViaggioResponse updateStato(Long id, StatoViaggio statoViaggio) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        viaggio.setStatoViaggio(statoViaggio);
        viaggioRepository.save(viaggio);
        return fromEntity(viaggio);
    }

    public ViaggioResponse assegnaOSostituisciDipendenteViaggio(Long idViaggio, Long idDipendente) {
        Dipendente dipendente = dipendenteRepository.findById(idDipendente)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(idViaggio)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));

        Dipendente dipendenteCorrente = viaggio.getDipendente();

        if (dipendenteCorrente != null && dipendenteCorrente.getId().equals(idDipendente)) {
                return fromEntity(viaggio);
            }
            viaggio.setDipendente(dipendente);
            viaggioRepository.save(viaggio);

            return fromEntity(viaggio);
        }
    }
