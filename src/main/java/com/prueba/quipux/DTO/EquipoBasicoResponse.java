package com.prueba.quipux.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoBasicoResponse {

    private String nombreEquipo;
    private Integer cantidadTitulos;
    private Integer cantidadJugadores;
    private String estadioEquipo;
}
