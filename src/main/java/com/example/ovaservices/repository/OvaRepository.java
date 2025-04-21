package com.example.ovaservices.repository;

import com.example.ovaservices.model.Ova;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OvaRepository extends JpaRepository<Ova, Long> {
    List<Ova> findByIdCurso(Long idCurso);
}