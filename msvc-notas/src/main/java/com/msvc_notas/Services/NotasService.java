package com.msvc_notas.Services;

import com.msvc_notas.Models.Entities.Notas;

import java.util.List;

public interface NotasService {

    List<Notas> findALL();
    Notas findById(Long id);
    Notas save (Notas notas);
}
