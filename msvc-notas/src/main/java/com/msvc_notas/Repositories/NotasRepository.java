package com.msvc_notas.Repositories;

import com.msvc_notas.Models.Entities.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {
}
