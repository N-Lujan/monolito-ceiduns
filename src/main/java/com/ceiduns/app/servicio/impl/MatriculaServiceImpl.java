package com.ceiduns.app.servicio.impl;

import com.ceiduns.app.dominio.Matricula;
import com.ceiduns.app.repositorio.MatriculaRepository;
import com.ceiduns.app.servicio.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;

    @Override
    public Matricula agregar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public List<Matricula> listarTodos() {
        return matriculaRepository.findAll();
    }

    @Override
    public Optional<Matricula> buscar(Long id) {
        return matriculaRepository.findById(id);
    }

    @Override
    public Matricula actualizar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public void eliminar(Long id) {
        matriculaRepository.deleteById(id);
    }
}
