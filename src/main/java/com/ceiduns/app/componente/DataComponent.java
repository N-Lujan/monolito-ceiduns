package com.ceiduns.app.componente;

import com.ceiduns.app.componente.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataComponent {
    @Bean
    public List<TipoPago> formasPago(){
        ArrayList<TipoPago> tipos = new ArrayList<>();
        tipos.add(new TipoPago(1,"Agente Multired"));
        tipos.add(new TipoPago(2,"MULTIRED virtual"));
        tipos.add(new TipoPago(3,"Agenca Bancaria"));
        return tipos;
    }

    @Bean
    public List<TipoAlumno> tiposAlumno(){
        ArrayList<TipoAlumno> tipos = new ArrayList<>();
        tipos.add(new TipoAlumno(1,"UNS - Becado"));
        tipos.add(new TipoAlumno(2,"UNS - No Becado"));
        tipos.add(new TipoAlumno(3,"Particular o Egresado"));
        tipos.add(new TipoAlumno(4,"Hijo de Trabajador"));
        return tipos;
    }

    @Bean
    public List<Turno> listaTurnos(){
        ArrayList<Turno> turnos = new ArrayList<>();
        turnos.add(new Turno(1, "Lun - Mie - Vie"));
        turnos.add(new Turno(2, "Lun - Mie"));
        turnos.add(new Turno(3, "Mar - Jue - Sáb"));
        turnos.add(new Turno(4, "Mar - Jue"));
        turnos.add(new Turno(5, "Sáb - Dom"));
        return turnos;
    }

    @Bean
    public List<Idioma> idiomaList(){
        ArrayList<Idioma> idiomas = new ArrayList<>();
        idiomas.add(new Idioma(1,"Inglés"));
        idiomas.add(new Idioma(2,"Quechua"));
        idiomas.add(new Idioma(3,"Portugués"));
        idiomas.add(new Idioma(4,"Francés"));
        idiomas.add(new Idioma(5,"Italiano"));
        return idiomas;
    }

    @Bean
    public List<Ciclo> cicloList(){
        ArrayList<Ciclo> ciclos = new ArrayList<>();
        ciclos.add(new Ciclo(1,"I"));
        ciclos.add(new Ciclo(2,"II"));
        ciclos.add(new Ciclo(3,"II"));
        ciclos.add(new Ciclo(4,"IV"));
        ciclos.add(new Ciclo(5,"V"));
        ciclos.add(new Ciclo(6,"VI"));
        ciclos.add(new Ciclo(7,"VII"));
        ciclos.add(new Ciclo(8,"VIII"));
        ciclos.add(new Ciclo(9,"IX"));
        ciclos.add(new Ciclo(10,"X"));
        ciclos.add(new Ciclo(11,"XI"));
        ciclos.add(new Ciclo(12,"XII"));
        return ciclos;
    }

    @Bean
    public List<Nivel> nivelList(){
        ArrayList<Nivel> niveles = new ArrayList<>();
        niveles.add(new Nivel(1,"Básico"));
        niveles.add(new Nivel(2,"Intermedio"));
        niveles.add(new Nivel(3,"Avanzado"));
        return niveles;
    }
}
