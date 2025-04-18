package it.epicode.ProgettoSettimanaleJava_S6_L5.dipendenti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DipendenteResponse {
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
}
