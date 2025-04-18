package it.epicode.ProgettoSettimanaleJava_S6_L5.prenotazioni;

import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.Dipendente;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("/{id}")
    public PrenotazioneResponse findById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @GetMapping
    public Page<PrenotazioneResponse> findFull(@ParameterObject Pageable pageable) {
        return prenotazioneService.findFull(pageable);
    }

    @PutMapping("/{id}")
    public PrenotazioneResponse update(@PathVariable Long id, @RequestBody PrenotazioneRequest request) {
        return prenotazioneService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prenotazioneService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponse save(@RequestBody @Valid PrenotazioneRequest request, Dipendente dipendente) {
        return prenotazioneService.save(request, dipendente);
    }
}
