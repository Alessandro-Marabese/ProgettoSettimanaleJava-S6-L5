package it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti;

import it.epicode.ProgettoSettimanaleJava_S6_L5.common.CommonResponse;
import it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi.Viaggio;
import it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi.ViaggioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@Validated
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public CommonResponse save(@Valid DipendenteRequest request) {
        Dipendente dipendente = new Dipendente();
        BeanUtils.copyProperties(request, dipendente);
        dipendenteRepository.save(dipendente);
        return new CommonResponse(dipendente.getId());
    }

    public void delete(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
        dipendenteRepository.delete(dipendente);
    }

    public DipendenteResponse findById(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
        DipendenteResponse response = new DipendenteResponse();
        BeanUtils.copyProperties(dipendente, response);
        return response;
    }

    public Page<DipendenteResponse> findFull(Pageable pageable) {
        return dipendenteRepository.findAll(pageable).map(this::fromEntity);
    }

    public DipendenteResponse update(Long id, @RequestBody DipendenteRequest request) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
        BeanUtils.copyProperties(request, dipendente);
        dipendenteRepository.save(dipendente);
        return fromEntity(dipendente);
    }

    public DipendenteResponse fromEntity(Dipendente dipendente) {
        DipendenteResponse response = new DipendenteResponse();
        BeanUtils.copyProperties(dipendente, response);
        return response;
    }


}
