package com.cadastro_ninjas.ninjas.repository;

import com.cadastro_ninjas.ninjas.models.NinjasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjasRepository extends JpaRepository<NinjasModel, Long> {
    NinjasModel findById(long id);
}
