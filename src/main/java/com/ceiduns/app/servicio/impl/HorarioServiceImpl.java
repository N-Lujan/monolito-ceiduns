package com.ceiduns.app.servicio.impl;

import com.ceiduns.app.dominio.Horario;
import com.ceiduns.app.repositorio.HorarioRepository;
import com.ceiduns.app.servicio.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioServiceImpl implements HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    public Horario agregar(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public List<Horario> listarTodos() {
        return horarioRepository.findAll();
    }

    @Override
    public Optional<Horario> buscar(Long id) {
        return horarioRepository.findById(id);
    }

    @Override
    public Horario actualizar(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public void eliminar(Long id) {
        horarioRepository.deleteById(id);
    }
}
