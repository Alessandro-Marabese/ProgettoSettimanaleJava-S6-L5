package it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti;

import it.epicode.ProgettoSettimanaleJava_S6_L5.common.CommonResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping("/{id}")
    public DipendenteResponse findById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    @GetMapping
    public Page<DipendenteResponse> findFull(@ParameterObject  Pageable pageable) {
        return dipendenteService.findFull(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse save(@RequestBody @Valid DipendenteRequest request) {
        return dipendenteService.save(request);
    }

    @PutMapping("/{id}")
    public DipendenteResponse update(@PathVariable Long id, @RequestBody DipendenteRequest request) {
        return dipendenteService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dipendenteService.delete(id);
    }


}
