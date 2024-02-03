package com.prueba.quipux.service;

import com.prueba.quipux.DTO.NumeroResponse;
import com.prueba.quipux.entitiy.NumeroEntity;
import com.prueba.quipux.utils.Excepcion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static com.prueba.quipux.utils.Constantes.ERR_01;

@Service
public class NumeroService {

    public NumeroResponse validarNumero(NumeroEntity numeroReq) {

        String validacion = null;
        if (numeroReq.getValor() <= 1 || numeroReq.getValor() >= 100) {
            throw new Excepcion(ERR_01);
        }else{
            if (numeroReq.getValor() % 2 != 0){
                validacion = "Quipux";
            } else {
                if ((numeroReq.getValor() >= 2 && numeroReq.getValor() <= 5) || numeroReq.getValor() > 20){
                    validacion = "No Quipux";
                }
                if (numeroReq.getValor() >= 6 && numeroReq.getValor() <= 20) {
                    validacion = "Quipux";
                }
            }
        }

        NumeroResponse response = new NumeroResponse();
        response.setValidacion(validacion);
        return ResponseEntity.ok(response).getBody();

    }

}
