package com.cadastro_ninjas.ninjas.repository;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MissaoRepository extends JpaRepository<MissaoModel, Long> {
    Optional<MissaoModel> findById(long id);
}
