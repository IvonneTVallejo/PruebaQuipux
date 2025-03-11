package com.prueba.quipux.service;

import com.prueba.quipux.DTO.*;
import com.prueba.quipux.entitiy.EquipoFutbolEntity;
import com.prueba.quipux.entitiy.JugadorEntity;
import com.prueba.quipux.repository.EquipoFutbolRepository;
import com.prueba.quipux.repository.JugadorRepository;
import com.prueba.quipux.utils.Excepcion;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static com.prueba.quipux.utils.Constantes.ERR_03;
import static com.prueba.quipux.utils.Constantes.ERR_04;

@Service
public class EquipoFutbolService {

    @Autowired
    EquipoFutbolRepository equipoFutbolRepository;

    @Autowired
    JugadorRepository jugadorRepository;

    public EquipoFutbolResponse crearEquipo(EquipoFutbolEntity request) {
        Optional<EquipoFutbolEntity> entity = equipoFutbolRepository.findAllByEquipoId(request.getEquipoId());

        if (request.getEquipoId() != null) {
            throw new Excepcion(ERR_03);
        } else if (entity.isPresent()) {
            throw new Excepcion(ERR_04);
        }
        return transformEquipoResponse(equipoFutbolRepository.save(transformEquipoEntity(request)));
    }
    private EquipoFutbolResponse transformEquipoResponse(EquipoFutbolEntity entity) {
        EquipoFutbolResponse response = new EquipoFutbolResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private EquipoFutbolEntity transformEquipoEntity(EquipoFutbolEntity request) {
        EquipoFutbolEntity entity = new EquipoFutbolEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    public List<EquipoBasicoResponse> getEquipoBasico() {
        Iterable<EquipoFutbolEntity> equiposIterable = equipoFutbolRepository.findAll();
        List<EquipoBasicoResponse> equiposListaPrueba = StreamSupport.stream(equiposIterable.spliterator(), false)
                .map(equipoEntity -> {
                    EquipoBasicoResponse response = transformBasicoResponse(equipoEntity);
                    Integer cantidadJugadoresPrueba = jugadorRepository.countByEquipoId(equipoEntity.getEquipoId());
                    response.setCantidadJugadores(cantidadJugadoresPrueba);
                    return response;
                })
                .collect(Collectors.toList());
        return equiposListaPrueba;
    }

    private EquipoBasicoResponse transformBasicoResponse(EquipoFutbolEntity entity) {
        EquipoBasicoResponse response = new EquipoBasicoResponse();
        response.setNombreEquipo(entity.getNombreEquipo());
        response.setCantidadTitulos(entity.getCantidadTitulos());
        response.setEstadioEquipo(entity.getEstadioEquipo());
        return response;
    }

    public List<EquipoDetalleResponse> getEquipoDetalle() {
        List<EquipoFutbolEntity> equipos = equipoFutbolRepository.findAll();
        List<EquipoDetalleResponse> equipoDetalleList = equipos.stream()
                .map(equipo -> {
                    EquipoDetalleResponse equipoDetalle = new EquipoDetalleResponse();
                    equipoDetalle.setNombreEquipo(equipo.getNombreEquipo());

                    List<JugadorEntity> jugadores = jugadorRepository.findByEquipoId(equipo.getEquipoId());

                    List<JugadorDetalleResponse> titulares = jugadores.stream()
                            .filter(jugador -> "Titular".equals(jugador.getEstadoJugador()))
                            .map(this::mapToJugadorDetalleResponse)
                            .collect(Collectors.toList());
                    equipoDetalle.setTitulares(titulares);

                    List<JugadorDetalleResponse> suplentes = jugadores.stream()
                            .filter(jugador -> "Suplente".equals(jugador.getEstadoJugador()))
                            .map(this::mapToJugadorDetalleResponse)
                            .collect(Collectors.toList());
                    equipoDetalle.setSuplentes(suplentes);

                    return equipoDetalle;
                })
                .collect(Collectors.toList());

        return equipoDetalleList;
    }

    private JugadorDetalleResponse mapToJugadorDetalleResponse(JugadorEntity jugador) {
        JugadorDetalleResponse jugadorDetalleResponse = new JugadorDetalleResponse();
        jugadorDetalleResponse.setNombreJugador(jugador.getNombreJugador());
        jugadorDetalleResponse.setPosicionJugador(jugador.getPosicionJugador());
        return jugadorDetalleResponse;
    }
}
