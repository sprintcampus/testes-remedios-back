package com.example.ApiRemedios.Remedio.Repository;

import com.example.ApiRemedios.Remedio.Entities.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemediosRepository extends JpaRepository<Remedio, Long> {

    List<Remedio> findAllByAtivoTrue();
}
