package com.ceiduns.app.servicio.impl;

import com.ceiduns.app.dominio.Aula;
import com.ceiduns.app.repositorio.AulaRepository;
import com.ceiduns.app.servicio.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaServiceImpl implements AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public Aula agregar(Aula aula) {
        return aulaRepository.save(aula);
    }

    @Override
    public List<Aula> listarTodos() {
        return aulaRepository.findAll();
    }

    @Override
    public Optional<Aula> buscar(Long id) {
        return aulaRepository.findById(id);
    }

    @Override
    public Aula actualizar(Aula aula) {
        return aulaRepository.save(aula);
    }

    @Override
    public void eliminar(Long id) {
        aulaRepository.deleteById(id);
    }
}
