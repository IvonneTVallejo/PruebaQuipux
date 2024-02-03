package com.prueba.quipux.controller;

import com.prueba.quipux.DTO.*;
import com.prueba.quipux.entitiy.EquipoFutbolEntity;
import com.prueba.quipux.entitiy.HoraTextoEntity;
import com.prueba.quipux.entitiy.JugadorEntity;
import com.prueba.quipux.entitiy.NumeroEntity;
import com.prueba.quipux.service.EquipoFutbolService;
import com.prueba.quipux.service.HoraTextoService;
import com.prueba.quipux.service.JugadorService;
import com.prueba.quipux.service.NumeroService;
import com.prueba.quipux.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/quipux")
@CrossOrigin("*")
public class PruebaQuipuxController implements Serializable {

    @Autowired
    NumeroService numeroService;

    @Autowired
    HoraTextoService horaTextoService;

    @Autowired
    JugadorService jugadorService;

    @Autowired
    EquipoFutbolService equipoFutbolService;

    @PostMapping(value = "/entero", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<NumeroResponse>> validarNumero(@RequestBody NumeroEntity entity){
        return new ResponseEntity<>(new Response<>(numeroService.validarNumero(entity)), HttpStatus.OK);
    }

    @PostMapping(value = "/hora", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<HoraTextoResponse>> convertirHora(@RequestBody HoraTextoEntity entity){
        return new ResponseEntity<>(new Response<>(horaTextoService.convertirHora(entity)), HttpStatus.OK);
    }

    @PostMapping(value = "/jugador", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<JugadorResponse>> crearJugador(@RequestBody JugadorEntity entity){
        return new ResponseEntity<>(new Response<>(jugadorService.crearJugador(entity)), HttpStatus.OK);
    }

    @PostMapping(value = "/equipo", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<EquipoFutbolResponse>> crearEquipo(@RequestBody EquipoFutbolEntity entity){
        return new ResponseEntity<>(new Response<>(equipoFutbolService.crearEquipo(entity)), HttpStatus.OK);
    }

    @GetMapping(value = "/reportebasico", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<EquipoBasicoResponse> getEquipoBasico() {
        return this.equipoFutbolService.getEquipoBasico();
    }

    @GetMapping(value = "/reportedetalle", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<EquipoDetalleResponse> getEquipoDetalle() {
        return this.equipoFutbolService.getEquipoDetalle();
    }


}
