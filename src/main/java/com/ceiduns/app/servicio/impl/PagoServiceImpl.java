package com.ceiduns.app.servicio.impl;

import com.ceiduns.app.dominio.Pago;
import com.ceiduns.app.repositorio.PagoRepository;
import com.ceiduns.app.servicio.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public Pago agregar(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> buscar(Long id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago actualizar(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }
}
