package com.simplesdental.jobsbackend.repositories;


import com.simplesdental.jobsbackend.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional,Long> {

    @Query(value = "SELECT :fields FROM Profissional WHERE idProfssional LIKE :parameter OR nome LIKE :parameter OR cargo LIKE :parameter OR nascimento LIKE :parameter OR data_de_criação LIKE :parameter OR contatos LIKE :parameter", nativeQuery = true)
    List<Profissional> findByParameterAndFields(@Param("parameter")String parameter, @Param("fields") String fields);
}
