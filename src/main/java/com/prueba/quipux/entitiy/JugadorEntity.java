package com.prueba.quipux.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="jugadores")
public class JugadorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jugadorId;
    @Column(name = "nombre_jugador")
    private String nombreJugador;
    @Column(name = "estado_jugador")
    private String estadoJugador;
    @Column(name = "posicion_jugador")
    private String posicionJugador;
    @Column(name = "equipo_id")
    private Integer equipoId;

    @ManyToOne
    @JoinColumn(name = "equipo_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("jugadores")
    private EquipoFutbolEntity equipo;
}
