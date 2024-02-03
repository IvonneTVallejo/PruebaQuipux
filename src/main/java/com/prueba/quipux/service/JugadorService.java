package com.prueba.quipux.service;

import com.prueba.quipux.DTO.JugadorResponse;
import com.prueba.quipux.entitiy.EquipoFutbolEntity;
import com.prueba.quipux.entitiy.JugadorEntity;
import com.prueba.quipux.repository.EquipoFutbolRepository;
import com.prueba.quipux.repository.JugadorRepository;
import com.prueba.quipux.utils.Excepcion;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.prueba.quipux.utils.Constantes.*;

@Service
public class JugadorService {

    @Autowired
    JugadorRepository jugadorRepository;

    @Autowired
    EquipoFutbolRepository equipoFutbolRepository;

    public JugadorResponse crearJugador(JugadorEntity request) {
        Optional<JugadorEntity> entity = jugadorRepository.findAllByJugadorId(request.getJugadorId());

        if (request.getJugadorId() != null) {
            throw new Excepcion(ERR_03);
        } else if (entity.isPresent()) {
            throw new Excepcion(ERR_04);
        }

        Integer equipoId = request.getEquipoId();
        Optional<EquipoFutbolEntity> equipoEntityOptional = equipoFutbolRepository.findAllByEquipoId(equipoId);
        if (equipoEntityOptional.isEmpty()) {
            throw new Excepcion(ERR_05);
        }

        return transformJugadorResponse(jugadorRepository.save(transformJugadorEntity(request)));
      }

    private JugadorResponse transformJugadorResponse(JugadorEntity entity) {
        JugadorResponse response = new JugadorResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private JugadorEntity transformJugadorEntity(JugadorEntity request) {
        JugadorEntity entity = new JugadorEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

}
