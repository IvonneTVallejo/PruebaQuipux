package com.prueba.quipux.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="equipos")
public class EquipoFutbolEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipoId;
    @Column(name = "nombre_equipo")
    private String nombreEquipo;
    @Column(name = "estadio_equipo")
    private String estadioEquipo;
    @Column(name = "cantidad_titulos")
    private Integer cantidadTitulos;
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "equipo")
    @JsonIgnoreProperties("equipo")
    private List<JugadorEntity> jugadores;

}
