package com.simplesdental.jobsbackend.repositories;

import com.simplesdental.jobsbackend.models.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {

    @Query("select a from Contato a where nome = ?1 or contato = ?1")
    List<Contato> findByParameterAndFields(@Param("parameter")String parameter);

}
