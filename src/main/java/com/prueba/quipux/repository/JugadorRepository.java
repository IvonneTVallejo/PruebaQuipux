package com.prueba.quipux.repository;

import com.prueba.quipux.entitiy.JugadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorEntity, Integer> {

    Optional<JugadorEntity> findAllByJugadorId(Integer jugadorId);

    Integer countByEquipoId(Integer equipoId);

    List<JugadorEntity> findByEquipoId(Integer equipoId);


}
