package com.cadastro_ninjas.ninjas.repository;

import com.cadastro_ninjas.ninjas.models.classes.NinjasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NinjasRepository extends JpaRepository<NinjasModel, Long> {
    Optional<NinjasModel> findById(long id);
}
