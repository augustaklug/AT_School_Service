package com.klug.schoolservice.repository;

import com.klug.schoolservice.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
