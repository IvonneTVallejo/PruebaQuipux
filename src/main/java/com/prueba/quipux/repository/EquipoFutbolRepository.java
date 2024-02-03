package com.prueba.quipux.repository;

import com.prueba.quipux.entitiy.EquipoFutbolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EquipoFutbolRepository extends JpaRepository<EquipoFutbolEntity, Integer> {
    Optional<EquipoFutbolEntity> findAllByEquipoId(Integer equipoId);
}
