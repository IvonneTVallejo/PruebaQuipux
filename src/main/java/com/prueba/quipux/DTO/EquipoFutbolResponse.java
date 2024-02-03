package com.prueba.quipux.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoFutbolResponse {

    private Integer equipoId;
    private String nombreEquipo;
    private String estadioEquipo;
    private Integer cantidadTitulos;
}
