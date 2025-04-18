package it.epicode.ProgettoSettimanaleJava_S6_L5.viaggi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViaggioRequest {
    @NotBlank(message = "Destinazione is mandatory")
    private String destinazione;
    @NotBlank(message = "Data di partenza is mandatory")
    private String dataPartenza;
    @NotNull(message = "StatoViaggio is mandatory")
    private StatoViaggio statoViaggio;
}
