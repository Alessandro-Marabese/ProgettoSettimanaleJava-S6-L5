package it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi;

import it.epicode.ProgettoSettimanaleJava_S6_L5.common.CommonResponse;
import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.DipendenteResponse;
import it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti.DipendenteService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping("/{id}")
    public ViaggioResponse findById(@PathVariable Long id) {
        return viaggioService.findById(id);
    }

    @GetMapping
    public Page<ViaggioResponse> findFull(@ParameterObject Pageable pageable) {
        return viaggioService.findFull(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse save(@RequestBody @Valid ViaggioRequest request) {
        return viaggioService.save(request);
    }

    @PutMapping("/{id}")
    public ViaggioResponse update(@PathVariable Long id, @RequestBody ViaggioRequest request) {
        return viaggioService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        viaggioService.delete(id);
    }

    @PutMapping("/{id}/stato")
    public ViaggioResponse updateStato(@PathVariable Long id, @RequestBody StatoViaggio request) {
        return viaggioService.updateStato(id, request);
    }

    @PutMapping("/viaggio/{idDipendente}/{idViaggio}")
    public ViaggioResponse assegnaDipendenteViaggio(@PathVariable Long idDipendente, @PathVariable Long idViaggio) {
        return viaggioService.assegnaDipendenteViaggio(idDipendente, idViaggio);
    }
}
