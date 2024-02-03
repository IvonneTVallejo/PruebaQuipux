package com.prueba.quipux.utils;

public class Constantes {

    private Constantes() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ERR_01 = "El numero se sale del rango permitido";

    public static final String ERR_02 = "El numero no corresponde a una hora valida";

    public static final String ERR_03 = "Para la creacion del registro no se debe enviar ID";

    public static final String ERR_04 = "El registro ya existe en la base de datos";

    public static final String ERR_05 = "El id del equipo no existe";
}
