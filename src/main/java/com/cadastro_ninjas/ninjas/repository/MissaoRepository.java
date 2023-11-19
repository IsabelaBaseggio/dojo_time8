package com.cadastro_ninjas.ninjas.repository;

import com.cadastro_ninjas.ninjas.models.classes.MissaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissaoRepository extends JpaRepository<MissaoModel, Long> {
    Optional<MissaoModel> findById(long id);

    List<MissaoModel> findByClassificacao(String dificuldade);

    List<MissaoModel> findByStatus(Boolean status);

    @Query("SELECT m FROM MissaoModel m WHERE m.id_ninja = :ninjaId")
    List<MissaoModel> findAllByNinjaId(@Param("ninjaId") long id_ninja);
}
