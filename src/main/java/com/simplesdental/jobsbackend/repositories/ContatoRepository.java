package com.simplesdental.jobsbackend.repositories;

import com.simplesdental.jobsbackend.models.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {

    @Query(value = "SELECT :fields FROM Contato WHERE idContato LIKE :parameter OR nome LIKE :parameter OR contato LIKE :parameter OR idProfissional LIKE :parameter", nativeQuery = true)
    List<Contato> findByParameterAndFields(@Param("parameter")String parameter, @Param("fields") String fields);

}
