package com.ceiduns.app.servicio.impl;

import com.ceiduns.app.dominio.Alumno;
import com.ceiduns.app.repositorio.AlumnoRepository;
import com.ceiduns.app.servicio.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno agregar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> listarTodos() {
        return alumnoRepository.findAll();
    }

    @Override
    public Optional<Alumno> buscar(Long id) {
        return alumnoRepository.findById(id);
    }

    @Override
    public Alumno actualizar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }
}
