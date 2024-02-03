package com.prueba.quipux.service;

import com.prueba.quipux.DTO.HoraTextoResponse;
import com.prueba.quipux.entitiy.HoraTextoEntity;
import com.prueba.quipux.utils.Excepcion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static com.prueba.quipux.utils.Constantes.ERR_02;

@Service
public class HoraTextoService {

    private static final String[] unidades = {
            "", "un", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez",
            "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"
    };

    private static final String[] decenas = {
            "", "", "veinte", "treinta", "cuarenta", "cincuenta"
    };

    public HoraTextoResponse convertirHora(HoraTextoEntity horaReq) {

        String horaEnPalabras = "";

        if (horaReq.getHora() < 1 || horaReq.getHora() > 12) {
            throw new Excepcion(ERR_02);
        }else if (horaReq.getMinutos() <0 || horaReq.getMinutos() >= 60 ) {
            throw new Excepcion(ERR_02);
        }else {
            if (horaReq.getMinutos() == 00) {
                horaEnPalabras = unidades[horaReq.getHora()] + " en punto";
            } else if (horaReq.getMinutos() == 15) {
                horaEnPalabras = unidades[horaReq.getHora()] + " y " + unidades[horaReq.getMinutos()] + " minutos";
            } else if (horaReq.getMinutos() == 30) {
                horaEnPalabras = unidades[horaReq.getHora()] + " y media";
            } else if (horaReq.getMinutos() < 20) {
                horaEnPalabras = unidades[horaReq.getHora()] + " y " + unidades[horaReq.getMinutos()] + " minutos";
            } else if (horaReq.getMinutos() == 20 || horaReq.getMinutos() == 40 || horaReq.getMinutos() == 50 ) {
                horaEnPalabras = unidades[horaReq.getHora()] + " y " + decenas[horaReq.getMinutos() / 10] + " minutos";
            } else {
                horaEnPalabras = unidades[horaReq.getHora()] + " y " + decenas[horaReq.getMinutos() / 10] + " y " + unidades[horaReq.getMinutos() % 10] + " minutos";
            }
        }
        HoraTextoResponse response = new HoraTextoResponse();
        response.setTextoHora(horaEnPalabras);
        return ResponseEntity.ok(response).getBody();
    }
}
