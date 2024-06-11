package com.klug.schoolservice.repository;

import com.klug.schoolservice.model.Disciplina;
import com.klug.schoolservice.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByDisciplinaAndValorGreaterThanEqual(Disciplina disciplina, Double valor);

    List<Nota> findByDisciplinaAndValorLessThan(Disciplina disciplina, Double valor);
}
