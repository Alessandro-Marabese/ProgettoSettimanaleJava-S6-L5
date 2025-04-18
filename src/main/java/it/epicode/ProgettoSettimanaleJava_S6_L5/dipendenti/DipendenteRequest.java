package it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DipendenteRequest {
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Nome is mandatory")
    private String nome;
    @NotBlank(message = "Cognome is mandatory")
    private String cognome;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
}
