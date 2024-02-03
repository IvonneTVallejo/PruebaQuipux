package com.prueba.quipux.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorResponse {
    private Integer jugadorId;
    private String nombreJugador;
    private String estadoJugador;
    private String posicionJugador;
    private Integer equipoId;
}
