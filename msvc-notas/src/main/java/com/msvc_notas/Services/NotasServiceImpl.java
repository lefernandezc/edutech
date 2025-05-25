package com.msvc_notas.Services;

import com.msvc_notas.Exceptions.NotasException;
import com.msvc_notas.Models.Entities.Notas;
import com.msvc_notas.Repositories.NotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotasServiceImpl implements NotasService {

    @Autowired
    private NotasRepository notasRepository;

    @Override
    public List<Notas> findALL() {
        return this.notasRepository.findAll();
    }

    @Override
    public Notas findById(Long id) {
        return this.notasRepository.findById(id).orElseThrow(
                () ->new NotasException("Notas no encontradas")
        );
    }

    @Override
    public Notas save(Notas notas) {
        return this.notasRepository.save(notas);
    }
}
