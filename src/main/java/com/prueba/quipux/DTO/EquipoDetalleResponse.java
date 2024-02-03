package com.prueba.quipux.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDetalleResponse {

    private String nombreEquipo;
    private List titulares;
    private List suplentes;
}
